package com.gea.bot.main;

/**
 * Enum de los comandos, v�lidos y que pueden ser procesados
 *
 * @author naviles
 */
public enum TipoComando {
    EXIT,
    HELP;

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
