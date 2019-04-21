package com.gea.bot.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.gea.bot.comandos.Comando;
import com.gea.bot.model.Entorno;
import com.gea.bot.model.Utils;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.ExcepcionSinstaxis;
import com.gea.bot.model.exception.TipoError;


public class ProcesadorComando {

    /**
     * Valida y ejecuta el comando con sus respectivos parámetros
     * @param entorno
     * @param comando
     * @param parametros Es una variable tipo vararg, por lo que puyede venir nula, un solo valor, valores separados por coma o como un array.
     */
    public void ejecutaComando(Entorno entorno, String comando, String... parametros) {
        try {
            //Esta variable se "tipea" como la interfaz, luego según usando reflection se instancia el comando correspondiente
            //De tal manera que al llamar el metodo, se ejecutar el código dependiendo de la implementacion (polimorfismo)
      
            //Revisar comando tipo especial

            //Si el comando es nulo o vacío, no hace nada.
            if (!(comando == null || comando.trim().isEmpty())) {
                //Segun el tipo de comando define la clase de la instancia comandoEjecutar, y por lo tanto define
                //cual será su comportamiento
                String className = Utils.getComandoName(comando);
            	try {
    				Class<?> clazz = Class.forName(className);
    				Constructor<?> constructor = clazz.getConstructor();
    				Comando comandoEjecutar = (Comando) constructor.newInstance();
    				
                    //Invoca los métodos para validar y ejecutar
                    comandoEjecutar.validar(parametros);
                    comandoEjecutar.ejecutar(entorno, parametros);
                    
    			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
    				throw new ExcepcionEjecucion(TipoError.ERROR_0001_COMANDO_INVALIDO);
    			}                
            }
        } catch (ExcepcionSinstaxis | ExcepcionEjecucion e) {
            System.out.println(e.getMessage());//Muestra error defindos
        } catch (Exception e) {
            System.out.println("Error no manejado. Detalle: " + e.getMessage());//Muestra error no definidos
        }
    }

}
