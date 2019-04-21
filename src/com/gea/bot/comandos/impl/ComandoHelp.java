package com.gea.bot.comandos.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.gea.bot.comandos.ParametroAyuda;
import com.gea.bot.main.TipoComando;
import com.gea.bot.model.Entorno;
import com.gea.bot.model.Utils;
import com.gea.bot.model.exception.ExcepcionEjecucion;

public class ComandoHelp extends ComandoBase {

    @Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
    	System.out.flush();
        System.out.println("Comandos permitidos:");
        //Lista todos los comandos definidos en TipoComando
        for(TipoComando tipoComando : TipoComando.values()) {
        	String className = Utils.getComandoName(tipoComando.toString());
        	try {
				Class<?> clazz = Class.forName(className);
				Constructor<?> constructor = clazz.getConstructor();
				ParametroAyuda comando = (ParametroAyuda) constructor.newInstance();
				comando.help();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.out.println("Clase " + className + " no existe o no es accesible");
				//e.printStackTrace();
			}
        }
	}

	@Override
	public void help() {
		System.out.println("Help: Muestra la ayuda del programa");
	}

	@Override
	public void helpParametros() {
		this.help();
		System.out.println(" No tiene parmetros");
	}


}
