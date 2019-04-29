package com.gea.bot.model.exception;

/**
 * Enum con los errores, define el c�digo y el texto de cada error.
 *
 * @author naviles
 */
public enum TipoError {

    ERROR_0001_COMANDO_INVALIDO(1, "Comando Inválido"),
    ERROR_0002_OPCION_INVALIDA(2, "Opción digitada no es válida"),
    ERROR_0003_REQUIERE_PARAMETROS(3, "El Comando requiere parámetros"),
    ERROR_0004_COMANDO_NO_IMPLEMENTADO(4, "Comando no implementado"),
    ERROR_0005_ERROR_GUARDAR_ARCHIVO(5, "Error al guardar el archivo"),
    ERROR_0006_ERROR_LEER_ARCHIVO(6, "Error al leer el archivo"),
    ERROR_0007_MEDIDOR_YA_EXISTE(7, "Medidor ya existe"),
    ERROR_0008_PARAMETRO_INVALIDO(8,"Parámetro inválido"),
	ERROR_0009_PARAMETRO_ID_REQUERIDO(9,"Parámetro ID requreido debe ser entero"),
	ERROR_0010_MEDIDOR_NO_EXISTE(10,"Medidor no existe"),
	ERROR_0011_ERROR_ARCHIVO_NO_DETALLADO(11, "Error archivo no detallado"),;
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
