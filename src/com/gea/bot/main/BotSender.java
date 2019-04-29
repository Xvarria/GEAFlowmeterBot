package com.gea.bot.main;

import static com.gea.bot.model.Constants.DELAY_MS;
import static com.gea.bot.model.Constants.INTERVAL_MS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BotSender {

	public static void main(String[] args) throws InterruptedException {

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		/*  */
		EmularMedidores task1 = new EmularMedidores();

		System.out.println("Registra tarea programada...");
		// init Delay = 5, repeat the task every 1 second
		@SuppressWarnings("unused")
		ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, DELAY_MS, INTERVAL_MS, TimeUnit.MILLISECONDS);
	}


}
