package com.gea.bot.comandos.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.gea.bot.model.Entorno;
import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.TipoError;

public class ComandoExit extends ComandoBase {

    @Override
	public void ejecutarAccion(Entorno entorno, String... parametros) throws ExcepcionEjecucion {
    	System.out.flush();
        System.out.println("¿Seguro que desea terminar? (S/N)");
        Scanner teclado = new Scanner(System.in);
        char opc = teclado.next().charAt(0);
        teclado.close();
        switch (opc) {
            case 's':
            case 'S':
                System.out.println("Digite una tecla para salir ¡Hasta pronto!");
                //Guarda la información en los archivo
                FileOutputStream archivo = null;
                ObjectOutputStream objectStream = null;
                try {
                    archivo = new FileOutputStream(new File(Entorno.RUTA_ARCHIVO));
                    //Guarda el objento MapaArchivo del entorno, con todo su contenido
                    objectStream = new ObjectOutputStream(archivo);
                    objectStream.writeObject(entorno.getListaMedidor());

                } catch (IOException e) {
                    throw new ExcepcionEjecucion(TipoError.ERROR_0002_OPCION_INVALIDA);
                } finally {
                    entorno.setFinalizar(true);
                    System.exit(0);
                }

                break;
            case 'n':
            case 'N':
                break;
            default:
                throw new ExcepcionEjecucion(TipoError.ERROR_0002_OPCION_INVALIDA);
        }
	}

	@Override
	public void help() {
		System.out.println("Exit: Sale del programa");
	}

	@Override
	public void helpParametros() {
		this.help();
		System.out.println(" No tiene parmetros");
	}

    
    
}
