package com.gea.bot.comandos.impl;

import com.gea.bot.main.Entorno;
import com.gea.bot.model.exception.ExcepcionArchivo;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.TipoError;

public class ComandoList extends ComandoBase {
	
	@Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
    	System.out.flush();
    	try {
			entorno.listMedidores();
		} catch (ExcepcionArchivo e) {
			throw new ExcepcionEjecucion(TipoError.ERROR_0011_ERROR_ARCHIVO_NO_DETALLADO);
		}
	}

	@Override
	public void help() {
		System.out.println("List: Lista los medidores actuales registrados en el archivo");
	}

	@Override
	public void helpParametros() {
		this.help();
		System.out.println("list ");
		System.out.println(" -Help --Help           Ayuda");
	}


}
