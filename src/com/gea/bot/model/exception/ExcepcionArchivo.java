package com.gea.bot.model.exception;

/**
 * Excepcion heraredada solo para errores durante la ejecución
 * @author mchavarria
 */
public class ExcepcionArchivo extends ExcepcionBase {

	public ExcepcionArchivo(TipoError error) {
		super(error);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
