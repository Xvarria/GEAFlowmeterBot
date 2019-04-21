package com.gea.bot.comandos.impl;

/**
 * Enum de los comandos, válidos y que pueden ser procesados
 *
 * @author naviles
 */
public enum TipoComando {
    ADD,
    REMOVE,
    LIST,
    HELP,
	EXIT,
    ;

    /**
     * Obtiene el tipo, basado en el comando digitado
     *
     * @param comando
     * @return
     */
    public static TipoComando getTipoComando(String comando) {
        for (TipoComando tipoComando : TipoComando.values()) {
            if (tipoComando.toString().equalsIgnoreCase(comando)) {
                return tipoComando;
            }
        }
        return null;
    }
}
