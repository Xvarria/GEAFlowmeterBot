package com.gea.bot.comandos;

import com.gea.bot.model.Entorno;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.ExcepcionSinstaxis;

/**
 * Interface Comando, define cuales son los métodos que todo comando debe tener (mas no especifica su implementacion)
 * @author naviles
 */
public interface Comando {

        /**
         * Valida los parametros
         * @param parametros
         * @return
         * @throws ExcepcionSinstaxis 
         */
	public boolean validar(String... parametros) throws ExcepcionSinstaxis;
	
        /**
         * Ejecuta los comandos
         * @param entorno
         * @param parametros
         * @throws ExcepcionEjecucion Si el comando falla, se tira esta excepcion
         */
	public void ejecutar(Entorno entorno, String... parametros) throws ExcepcionEjecucion;
	
	/**
	 * 
	 * @param entorno
	 * @param parametros
	 * @throws ExcepcionEjecucion
	 */
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion;
	
}
