package com.gea.bot.model.exception;

/**
 * Enum con los errores, define el c�digo y el texto de cada error.
 *
 * @author naviles
 */
public enum TipoError {

    ERROR_0001_COMANDO_INVALIDO(1, "Comando Inválido"),
    ERROR_0002_OPCION_INVALIDA(2, "Opción digitada no es válida"),
    ERROR_0003_PARAMETRO_INVALIDO(3, "Parametro '$' es inválido"),
    ERROR_0004_COMANDO_NO_IMPLEMENTADO(4, "Comando no implementado"),;
    private final int codigo;
    private final String mensaje;

    private TipoError(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el mensaje en el formato adecuado para ser presentado en consola
     *
     * @return
     */
    public String getMensajeFormateado() {
        return "     Error: " + String.format("%3s", codigo).replace(" ", "0") + " - Descripción: " + mensaje;
    }

    /**
     * Obtiene el tipo
     *
     * @param error
     * @return
     */
    public TipoError getError(TipoError error) {
        for (TipoError retError : TipoError.values()) {
            if (retError.equals(error)) {
                return retError;
            }
        }
        return null;
    }
}
