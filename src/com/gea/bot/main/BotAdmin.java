package com.gea.bot.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gea.bot.model.Entorno;
import com.gea.bot.model.Medidor;

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
    	List<Medidor> listaMedidor = inicializarMedidores();
    	entorno = new Entorno();
    	entorno.setListaMedidor(listaMedidor);
    }

    /**
     * Lee el archivo de archivos y directorios, recupera la información. Si el
     * archivo no existe o no es válido, inicializa con valores por default.
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	private static List<Medidor> inicializarMedidores() throws FileNotFoundException, IOException {
        //Se valida la existencia del archivo y el contenido, si el archivo no existe o su contenido es invalido
        //La informacion es cargada con valores por defecto
    	List<Medidor> listaMedidor = new ArrayList<>();
        boolean datoArchivoCorrecto = false;
        FileInputStream archivo = null;
        ObjectInputStream objectStream = null;

        try {
            //Se abre el archivo y se inicializa con la dirección
            archivo = new FileInputStream(new File(Entorno.RUTA_ARCHIVO));
            //Se cre aun objecto InputStream para leer obheto serializados del archivo (esto permite guardar y leer todo el objeto de manera m�s f�cil y segura)
            objectStream = new ObjectInputStream(archivo);

            listaMedidor = (List<Medidor>)objectStream.readObject();//Obtiene el objeto desde el archivo.
            datoArchivoCorrecto = true;

        } catch (Exception e) {
            datoArchivoCorrecto = false;
        } finally {
            if (archivo != null) {
                archivo.close();
            }

            if (objectStream != null) {
                objectStream.close();
            }
        }
        //Registra el resultado del proceso
        if (datoArchivoCorrecto) {
            System.out.println("Medidores recuperados -> ");
            for(Medidor medidor : listaMedidor) {
            	System.out.println(medidor.toString());
            }
        } else {
            System.out.println("Medidores NO recuperados");
        }

        return listaMedidor;
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

            //Obtiene los par�metros si los hay
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
            //Procesa el commando y sus par�metros, env�a el entorno, para que los comando
            //que tiene impacto en el entorno, registren sus cambios
            procesador.ejecutaComando(entorno, comando, parametros);
        }
    }
}
