package com.gea.bot.model;

import java.io.Serializable;
import java.util.List;

public class Entorno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int intervalS = 30;
	private final static int INTERVAL_MS = intervalS * 1000;
	public static final String RUTA_ARCHIVO = "/registoBot";
	private boolean finalizar;

	private List<Medidor> listaMedidor;

	public static int getIntervalS() {
		return intervalS;
	}

	public static void setIntervalS(int intervalS) {
		Entorno.intervalS = intervalS;
	}

	public List<Medidor> getListaMedidor() {
		return listaMedidor;
	}

	public void setListaMedidor(List<Medidor> listaMedidor) {
		this.listaMedidor = listaMedidor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getIntervalMs() {
		return INTERVAL_MS;
	}

	public static String getRutaArchivo() {
		return RUTA_ARCHIVO;
	}

	public boolean isFinalizar() {
		return finalizar;
	}

	public void setFinalizar(boolean finalizar) {
		this.finalizar = finalizar;
	}	
}
