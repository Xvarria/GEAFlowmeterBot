package com.gea.bot.comandos.impl;

import com.gea.bot.main.Entorno;
import com.gea.bot.model.exception.ExcepcionEjecucion;

public class ComandoList extends ComandoBase {
	
	@Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
    	System.out.flush();
    	entorno.listMedidores();
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
