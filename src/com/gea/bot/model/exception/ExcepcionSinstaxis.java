package com.gea.bot.model.exception;

/**
 * Excepcion heraredada solo para errores durante la validacion de parametros
 *
 * @author mchavarria
 */
public class ExcepcionSinstaxis extends ExcepcionBase {

    public ExcepcionSinstaxis(TipoError error) {
        super(error);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
