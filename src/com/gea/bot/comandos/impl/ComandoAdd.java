package com.gea.bot.comandos.impl;

import com.gea.bot.main.Entorno;
import com.gea.bot.model.Medidor;
import com.gea.bot.model.SegmentoConsumo;
import com.gea.bot.model.Utils;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.ExcepcionSinstaxis;
import com.gea.bot.model.exception.TipoError;

public class ComandoAdd extends ComandoBase {

	private static final String OPTION_S = "-S";
	
	@Override
	public boolean validar(String... parametros) throws ExcepcionSinstaxis {
		if (parametros == null || parametros.length == 0) {
			throw new ExcepcionSinstaxis(TipoError.ERROR_0008_PARAMETRO_INVALIDO);
		}else if (!isParametroAyuda(parametros) && !Utils.esEntero(parametros[0])) {
			throw new ExcepcionSinstaxis(TipoError.ERROR_0009_PARAMETRO_ID_REQUERIDO);
		}else if (parametros.length > 1 && !isOpcion(parametros[1])) {
			SegmentoConsumo consumo = SegmentoConsumo.getSegmentoConsumoPorNombe(parametros[1]);
			if (consumo == null) {
				throw new ExcepcionSinstaxis(TipoError.ERROR_0008_PARAMETRO_INVALIDO);	
			}
		}else if (parametros.length > 2 && !isOpcion(parametros[2]) && !Utils.esNumerico(parametros[2])) {
			throw new ExcepcionSinstaxis(TipoError.ERROR_0008_PARAMETRO_INVALIDO);
		}
		return true;
	}

	@Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
    	System.out.flush();
    	boolean sobreescribir = false;
    	SegmentoConsumo segmentoConsumo = SegmentoConsumo.C1_0_15;
    	Medidor medidor = new Medidor();
    	medidor.setMedidorId(Integer.parseInt(parametros[0]));
		if (parametros.length > 1) {
			if (isOpcion(parametros[1])) {
				sobreescribir = true;
			}else {
				SegmentoConsumo consumoAux = SegmentoConsumo.getSegmentoConsumoPorNombe(parametros[1]);
				if (consumoAux != null) {
					segmentoConsumo = consumoAux;
				}
			}
		}
		if (parametros.length > 2){
			if (isOpcion(parametros[2])) {
				sobreescribir = true;
			}else {
				medidor.setUltimaLectura(Double.parseDouble(parametros[2]));
			}
		}  
		
		if (parametros.length > 3 && isOpcion(parametros[3])) {
			sobreescribir = true;
		}   
		medidor.setSegmentoConsumo(segmentoConsumo);
    	entorno.addMedidor(medidor, sobreescribir);
	}

	@Override
	public void help() {
		System.out.println("Add: Agregar un medidor al registro de medidores");
	}

	@Override
	public void helpParametros() {
		this.help();
		System.out.println("add [id] {segmento} {conteo inicial} ");
		System.out.println(" -S                     Sobrescribe el valor");
		System.out.println(" -Help --Help           Ayuda");
	}

	private boolean isOpcion(String parametro) {
		return OPTION_S.equals(parametro);
	}

}
