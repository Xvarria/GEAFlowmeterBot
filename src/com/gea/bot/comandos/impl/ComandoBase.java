package com.gea.bot.comandos.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gea.bot.comandos.Comando;
import com.gea.bot.comandos.ParametroAyuda;
import com.gea.bot.model.Entorno;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.ExcepcionSinstaxis;
import com.gea.bot.model.exception.TipoError;

public class ComandoBase implements Comando, ParametroAyuda {

	public static final List<String> PARAMETRO_AYUADA;
	
	static {
		PARAMETRO_AYUADA = new ArrayList<>();
		PARAMETRO_AYUADA.add("/?");
		PARAMETRO_AYUADA.add("--?");
		PARAMETRO_AYUADA.add("-?");
		PARAMETRO_AYUADA.add("--help");
		PARAMETRO_AYUADA.add("-help");
	}
	
	protected boolean isParametroAyuda(String... parametros) {
		return parametros != null && parametros.length > 0 && isParmetroAyuda(parametros[0]);
	}
	
	private boolean isParmetroAyuda(String parametro) {
		boolean retVal = false;
		Iterator<String> iterator = PARAMETRO_AYUADA.iterator();
		while(iterator.hasNext() && !retVal){
			retVal = iterator.next().equalsIgnoreCase(parametro);
		}
		return retVal;
	}
	
	 public void ejecutar(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
		 if (this.isParametroAyuda(parametros)) {
	        	this.helpParametros();
	        }else {
	        	this.ejecutarAccion(entorno, parametros);
	        }
	 }

	@Override
	public boolean validar(String... parametros) throws ExcepcionSinstaxis {
		return true;
	}

	@Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
		throw new ExcepcionEjecucion(TipoError.ERROR_0004_COMANDO_NO_IMPLEMENTADO);
	}

	@Override
	public void help() {
		System.out.println("Comando: Descripción");
	}

	@Override
	public void helpParametros() {
		this.help();
		System.out.println(" Detalle de parametros");
	}
}
