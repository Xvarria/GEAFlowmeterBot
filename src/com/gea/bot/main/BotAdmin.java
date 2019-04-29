package com.gea.bot.main;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mchavarria
 */
public class BotAdmin {

    /**
     *
     */
    private static final String BIENVENIDA = "BOT admin";

    //Clase entorno, contiene los datos del entorno de la ejecución
    //Prompt, Memoria, Sistema de Archivos
    private static Entorno entorno;

    // Clase principal
    public static void main(String[] Args) throws IOException {
        inicializaEntorno();
        mostrarBievenida();
        //
        consola();
    }

    /**
     * HAce una instancia del entorno y la inicializa con los valores de los
     * archivos, si los archivos no existen o no son validos inicializa con
     * valores por defecto.
     *
     * @throws IOException
     */
    public static void inicializaEntorno() throws IOException {
        // Busca los archivos para inicializar el entorno
        // Si los archivos no existen los crea e inicializa el entorno con
        // valores por defecto
    	entorno = new Entorno();
    	entorno.recuerperarArchivoHandled();
    }

    /**
     * Muestra el texto de bienvenida
     */
    private static void mostrarBievenida() {
        System.out.print(BIENVENIDA);
    }

    /**
     * Este método permite hacer la lectura del "stream" del teclado
     * @throws IOException 
     */
    public static void consola() throws IOException {
        String cadena;
        Scanner teclado;

        //Inicializa una instnacia del método que llama a los comandos
        ProcesadorComando procesador = new ProcesadorComando();

        while (!entorno.isFinalizar()) {
            System.out.print("\n#GEA Bot admin>");
            teclado = new Scanner(System.in);
            cadena = teclado.nextLine(); //Registra el texto del teclado

            //Limpia los espacios y tabuladores
            cadena = cadena != null ? cadena.trim() : "";

            // Identifica comandos
            String[] secuencia = cadena.split(" ");
            String[] parametros = new String[]{};

            //Obtiene los parámetros si los hay
            String comando = null;
            if (secuencia.length > 0) {
                comando = secuencia[0];
                if (secuencia.length > 1) {
                    parametros = new String[secuencia.length - 1];
                    int index = 0;
                    for (String parametro : secuencia) {
                        if (index > 0) {
                            parametros[index - 1] = parametro;
                        }
                        index++;
                    }

                }
            }
            //Procesa el commando y sus parámetros, envía el entorno, para que los comando
            //que tiene impacto en el entorno, registren sus cambios
            procesador.ejecutaComando(entorno, comando, parametros);
        }
    }
}
