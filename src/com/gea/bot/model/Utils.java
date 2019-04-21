package com.gea.bot.model;

/**
 * Clase con metodos utilitarios
 */
public final class Utils {

    public static final  String getComandoName(String tipoComandoName) {
    	int size = tipoComandoName.length() ;
    	return "com.gea.bot.comandos.impl.Comando" + (size > 0 ? tipoComandoName.substring(0, 1).toUpperCase() + (size > 1 ? tipoComandoName.substring(1, size).toLowerCase() : "") : "");
    			
    }
    
    /**
     * Revisa si un String es un valor numérico
     * @param cadena
     * @return 
     */
    public final static boolean esNumerico(String cadena) {
        boolean parseoCorrecto = false;
        try {
            Double.parseDouble(cadena);
            parseoCorrecto = true;//Realiza la conversion sin problema
        } catch (Exception e) {
            //Error al convertir de cadena de caracteres a numerico
            //por lo que no es un valor numerico
            parseoCorrecto = false;
        }
        return parseoCorrecto;
    }

    public final static boolean esEntero(String cadena) {
        boolean parseoCorrecto = false;
        try {
            Integer.parseInt(cadena);
            parseoCorrecto = true;//Realiza la conversion sin problema
        } catch (Exception e) {
            //Error al convertir de cadena de caracteres a numerico
            //por lo que no es un valor numerico
            parseoCorrecto = false;
        }
        return parseoCorrecto;
    }

}
