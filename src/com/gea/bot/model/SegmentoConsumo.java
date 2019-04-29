package com.gea.bot.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public enum SegmentoConsumo implements Serializable {
	C1_0_15(0, 15, 355, 1403, 355, 1043),
	C2_16_25(16, 25, 712, 1703, 712, 1703),
	C3_26_40(26, 40, 783, 1703, 712, 1703),
	C4_41_60(41, 60, 927, 1703, 712, 1703),
	C5_61_80(61, 80, 1703, 1703, 783, 1703),
	C6_81_100(81, 100, 1703, 1703, 783, 1703),
	C6_101_120(101, 120, 1703, 1703, 783, 1703),
	C7_121_MAS(121, -1, 1790, 1790, 783, 1790);
	
	private double minimo;
	private double maximo;
	private double domiciliar;
	private double empresarial;
	private double preferencial;
	private double gobierno;

	private SegmentoConsumo(double minimo, double maximo, double domiciliar, double empresarial, double preferencial, double gobierno) {
		this.minimo = minimo;
		this.maximo = maximo;
		this.domiciliar = domiciliar;
		this.empresarial = empresarial;
		this.preferencial = preferencial;
		this.gobierno = gobierno;
	}

	public double getMinimo() {
		return minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}

	public double getDomiciliar() {
		return domiciliar;
	}

	public void setDomiciliar(double domiciliar) {
		this.domiciliar = domiciliar;
	}

	public double getEmpresarial() {
		return empresarial;
	}

	public void setEmpresarial(double empresarial) {
		this.empresarial = empresarial;
	}

	public double getPreferencial() {
		return preferencial;
	}

	public void setPreferencial(double preferencial) {
		this.preferencial = preferencial;
	}

	public double getGobierno() {
		return gobierno;
	}

	public void setGobierno(double gobierno) {
		this.gobierno = gobierno;
	}
	
	public static SegmentoConsumo getSegmentoConsumoPorNombe(String nombre) {
		SegmentoConsumo retVal = null;
		Iterator<SegmentoConsumo> segIterator = Arrays.asList(SegmentoConsumo.values()).iterator();
		while(segIterator.hasNext() && retVal == null) {
			SegmentoConsumo actual = segIterator.next();
			if (actual.toString().equalsIgnoreCase(nombre)) {
				retVal = actual;
			}
		}
		return retVal;
	}
}
