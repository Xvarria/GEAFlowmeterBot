package com.gea.bot.model;

/**
 * Clase con metodos utilitarios
 */
public final class Utils {

    public static final  String getComandoName(String tipoComandoName) {
    	int size = tipoComandoName.length() ;
    	return "com.gea.bot.comandos.impl.Comando" + (size > 0 ? tipoComandoName.substring(0, 1).toUpperCase() + (size > 1 ? tipoComandoName.substring(1, size).toLowerCase() : "") : "");
    			
    }
    
}
