package com.gea.bot.model.exception;

/**
 * Modelo básico de excepcion, para definir el mensaje basado en el enum de errores.
 * @author mchavarria
 */
public class ExcepcionBase extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TipoError error;

    public TipoError getError() {
        return error;
    }

    public void setError(TipoError error) {
        this.error = error;
    }

    public ExcepcionBase(TipoError error) {
        super(error.getMensajeFormateado());
        this.error = error;
    }

}
