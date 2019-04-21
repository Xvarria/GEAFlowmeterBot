package com.gea.bot.comandos.impl;

import com.gea.bot.main.Entorno;
import com.gea.bot.model.Medidor;
import com.gea.bot.model.Utils;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.ExcepcionSinstaxis;
import com.gea.bot.model.exception.TipoError;

public class ComandoRemove extends ComandoBase {
	
	@Override
	public boolean validar(String... parametros) throws ExcepcionSinstaxis {
		if (parametros == null || parametros.length == 0) {
			throw new ExcepcionSinstaxis(TipoError.ERROR_0008_PARAMETRO_INVALIDO);
		}else if (!isParametroAyuda(parametros) && !Utils.esEntero(parametros[0])) {
			throw new ExcepcionSinstaxis(TipoError.ERROR_0009_PARAMETRO_ID_REQUERIDO);
		}
		return true;
	}

	@Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
    	System.out.flush();
    	entorno.removeMedidor(Integer.parseInt(parametros[0]));;
	}

	@Override
	public void help() {
		System.out.println("Remove: Elimina un medidor de la lista");
	}

	@Override
	public void helpParametros() {
		this.help();
		System.out.println("remove [id]");
		System.out.println(" -Help --Help           Ayuda");
	}


}
