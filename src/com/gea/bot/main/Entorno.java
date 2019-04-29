package com.gea.bot.main;

import static com.gea.bot.model.Constants.INTERVAL_MS;
import static com.gea.bot.model.Constants.RUTA_ARCHIVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.gea.bot.model.Medidor;
import com.gea.bot.model.Registro;
import com.gea.bot.model.exception.ExcepcionArchivo;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.TipoError;

public class Entorno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean finalizar;
	private Registro registro = new Registro();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getIntervalMs() {
		return INTERVAL_MS;
	}

	public static String getRutaArchivo() {
		return RUTA_ARCHIVO;
	}

	public boolean isFinalizar() {
		return finalizar;
	}

	public void setFinalizar(boolean finalizar) throws ExcepcionEjecucion{
		try {
			this.finalizar = finalizar;
			this.desbloquearArchivo();
		} catch (ExcepcionArchivo e) {
			throw new ExcepcionEjecucion(e.getError());
		}
	}

	public void recuerperarArchivoHandled() {
		try {
			this.leerRegistro();
			this.mostrarRegistro();
		} catch (ExcepcionArchivo e) {
			System.out.println("Archivo no recuperado: " + e.getError().getMensajeFormateado());
			registro = new Registro();
			registro.setBloqueado(false);
			registro.initMedidorList();
			try {
				this.guardarArchivo();
				System.out.println("Archivo nuevo sobrescrito");
			} catch (ExcepcionArchivo e1) {
				System.err.println("Error sobreescribiendo archivo: " + e1.getError().getMensajeFormateado());
			}
		}
	}
	
	public void mostrarRegistro() {
        System.out.println("Registro " + (registro.isBloqueado() ? "bloqueado" : "no bloqueado"));
		System.out.println("Medidores recuperados -> ");
		registro.displayMedidores();
	}
	
	public void listMedidores() throws  ExcepcionEjecucion, ExcepcionArchivo{
		this.listMedidores(false);
	}
	
	public void listMedidores(boolean bloqueo) throws  ExcepcionEjecucion, ExcepcionArchivo{
		try {
			if (bloqueo) {
				this.bloquearArchivo();
			}
			this.leerRegistro();
			this.mostrarRegistro();
		} catch (ExcepcionArchivo e) {
			this.desbloquearArchivo();
			throw new ExcepcionEjecucion(e.getError());
		}
	}
	
	public void addMedidor(Medidor medidor, boolean sobreescribir) throws ExcepcionEjecucion {
		try {
			this.bloquearArchivo();
			this.registro.addMedidor(medidor, sobreescribir);
			this.guardarArchivo();
		} catch (ExcepcionArchivo e) {
			throw new ExcepcionEjecucion(e.getError());
		} finally {
			try {
				this.desbloquearArchivo();
			} catch (ExcepcionArchivo e) {
				throw new ExcepcionEjecucion(e.getError());
			}
		}
	}
	
	public void removeMedidor(int medidorId) throws ExcepcionEjecucion {
		try {
			this.bloquearArchivo();
			this.registro.removeMedidor(medidorId);
			this.guardarArchivo();
		} catch (ExcepcionArchivo e) {
			throw new ExcepcionEjecucion(e.getError());
		} finally {
			try {
				this.desbloquearArchivo();
			} catch (ExcepcionArchivo e) {
				throw new ExcepcionEjecucion(e.getError());
			}
		}
	}
	
	public void actualizarArchivoSender() throws ExcepcionEjecucion {
		try {
			this.guardarArchivo();
		} catch (ExcepcionArchivo e) {
			throw new ExcepcionEjecucion(e.getError());
		}		
	}
	
	private void bloquearArchivo() throws ExcepcionArchivo{
		cambiarBloqueo(true);
	}

	private void desbloquearArchivo() throws ExcepcionArchivo{
		cambiarBloqueo(false);
	}
	
	private void cambiarBloqueo(boolean bloqueado) throws ExcepcionArchivo {
		this.leerRegistro();
		this.registro.setBloqueado(bloqueado);
		this.guardarArchivo();
	}
	
	private void leerRegistro() throws ExcepcionArchivo {
		FileInputStream archivo = null;
		ObjectInputStream objectStream = null;
		try {
			// Se abre el archivo y se inicializa con la dirección
			archivo = new FileInputStream(new File(RUTA_ARCHIVO));
			// Se cre aun objecto InputStream para leer obheto serializados del archivo
			// (esto permite guardar y leer todo el objeto de manera más fácil y segura)
			objectStream = new ObjectInputStream(archivo);

			Registro registro = (Registro) objectStream.readObject();// Obtiene el objeto desde el archivo.
			
			if (archivo != null) {
				archivo.close();
			}

			if (objectStream != null) {
				objectStream.close();
			}
			
			this.registro = registro;
		} catch (Exception e) {
			throw new ExcepcionArchivo(TipoError.ERROR_0006_ERROR_LEER_ARCHIVO);
		}
	}

	private void guardarArchivo() throws ExcepcionArchivo {
		FileOutputStream archivo = null;
		ObjectOutputStream objectStream = null;
		try {
			archivo = new FileOutputStream(new File(RUTA_ARCHIVO));
			// Guarda el objento MapaArchivo del entorno, con todo su contenido
			objectStream = new ObjectOutputStream(archivo);
			objectStream.writeObject(this.registro);
			objectStream.close();
		} catch (IOException e) {
			throw new ExcepcionArchivo(TipoError.ERROR_0005_ERROR_GUARDAR_ARCHIVO);
		}
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	
}
