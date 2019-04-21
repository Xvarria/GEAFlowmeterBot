package com.gea.bot.comandos.impl;

import com.gea.bot.model.Entorno;
import com.gea.bot.model.exception.ExcepcionEjecucion;

public class ComandoAdd extends ComandoBase {

    @Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
    	System.out.flush();
    	System.out.println("Ejecuta...");
	}

	@Override
	public void help() {
		System.out.println("Add: Agregar un medidor al registro de medidores");
	}

	@Override
	public void helpParametros() {
		this.help();
		System.out.println("add [id] {segmento} {conteo inicial}");
		System.out.println(" -S                     Sobrescribe el valor");
		System.out.println(" -Help --Help           Ayuda");
	}


}
