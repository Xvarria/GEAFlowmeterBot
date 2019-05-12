package com.gea.bot.main;

import static com.gea.bot.model.Constants.APP_KEY;
import static com.gea.bot.model.Constants.INTERVAL_S;
import static com.gea.bot.model.Constants.WS_URL;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.gea.bot.model.Medidor;
import com.gea.bot.model.Registro;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.web.model.request.LecturaMedidorRequest;
import com.gea.web.model.request.LecturaRequest;
import com.gea.web.model.response.LecturaResponse;

public class EmularMedidores implements Runnable {

    private static Entorno entorno = new Entorno();
    private final static int N_INTENTOS  = 3;
    private final static int N_INTERVALO_REINTNETO = 500;
    
	public final static int TOTAL_SEGUNDOS_POR_PERIODO = 30 * 24 * 60 * 60 ;
	public final static int INTERVALO_POR_PERIODO = TOTAL_SEGUNDOS_POR_PERIODO / INTERVAL_S;

	private static Client client = ClientBuilder.newClient();
	private static WebTarget target = client.target(WS_URL);

	@Override
	public void run() {
		System.out.println("Metodo runnable...");
		/*
		 * 1. Revisa el archivo y obtiene una lista de registros,
		 * si el archivo no es accesible hace N_INTENTOS reintentos cada N_INTERVALO_REITNETO ms
		 */
		boolean lecturaExitosa = false;
		int contadorIntentos = 1;
		while (!lecturaExitosa && contadorIntentos <=  N_INTENTOS) {
			try {
				entorno.listMedidores();
				lecturaExitosa = true;
			}catch (Exception e) {
				lecturaExitosa = false;
				System.err.println("Error leyendo el archivo de registros... " + e.getLocalizedMessage());
				try {
					Thread.sleep(N_INTERVALO_REINTNETO);
				} catch (InterruptedException e2) {
					System.err.println("Error en Intervalo entre reintentos" + e2.getLocalizedMessage());
				}
			}
			contadorIntentos++;
		}
		
		 /*
		  *   si el archivo es accesible, genera lecturas y obtiene una un request.
		  */
		if (lecturaExitosa) {
			Registro registro = entorno.getRegistro();
			
			LecturaRequest request = new LecturaRequest();
			Set<LecturaMedidorRequest> data = new HashSet<>();
			
			for (Medidor medidor : registro.getListaMedidor()) {
				LecturaMedidorRequest lecturaMedidor = this.setMedidorRequest(medidor);
				data.add(lecturaMedidor);
			}
			try {
				entorno.actualizarArchivoSender();
			} catch (ExcepcionEjecucion e) {
				System.err.println("Error al actualizar archivo desde el sender. " + e.getLocalizedMessage());
			}
			
			request.setApiKey(APP_KEY);
			request.setTimestamp(Calendar.getInstance().getTimeInMillis()/1000); // Se divide entre 100 para obteber solo 10 dígitos
			request.setData(data);
			System.out.println("Request element:  " + request.toString());
			System.out.println("*****   Invoke remote ws *********");
			LecturaResponse response = process(request);
			System.out.println("Response element:  " + response.toString());

		}
	}
	
	private LecturaMedidorRequest setMedidorRequest(Medidor medidor) {
		double volumen = 0;
		double temperature = 18;
		//Calcular volumen
		//Numero random del 0 o 1
		long coeficiente = new Long(Math.round(Math.random()*1));
		double valorAsumar = coeficiente * 0.001;
        volumen = medidor.getUltimaLectura() + valorAsumar;

		temperature += Math.round(Math.random())*7;
				
		LecturaMedidorRequest lecturaMedidor = new LecturaMedidorRequest();
		lecturaMedidor.setError("none");
		lecturaMedidor.setMeasurerInternalId(medidor.getMedidorId());
		lecturaMedidor.setVolumen(volumen);
		lecturaMedidor.setTemperature(temperature);
		
		//Altera el medidor para guardar el archivo
		medidor.setUltimaLectura(volumen);
		return lecturaMedidor;
	}

	public static LecturaResponse process(LecturaRequest lecturaRequest) {
		return target.request(MediaType.APPLICATION_JSON).post(Entity.json(lecturaRequest), LecturaResponse.class);
	}
}
