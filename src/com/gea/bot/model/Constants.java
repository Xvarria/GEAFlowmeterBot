package com.gea.bot.model;

public final class Constants {

	/*Archivo de registro*/
	public static final String RUTA_FOLDER = "/home/mchavarria/proyectos/GEAFlowmeterBot/resources";
	public static final String RUTA_ARCHIVO = "registroBot.bot";
	
	/*WS URL*/
	public static final String WS_URL = "http://localhost:8080/GEAWeb/service/registrarLecturaRaw.do";
	
	public static final String APP_KEY = "599a2382-ed18-423b-8074-d70cfe78b921";
	
	public final static int DELAY_MS = 1000;
	public final static int INTERVAL_S = 3600;
	public final static int INTERVAL_MS = INTERVAL_S * 1000;

	
	private Constants() {};
}
