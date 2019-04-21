package com.gea.bot.model;

import java.io.Serializable;

public class Medidor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int medidorId;
	private SegmentoConsumo segmentoConsumo;
	private float ultimaLectura;
	
	public int getMedidorId() {
		return medidorId;
	}
	public void setMedidorId(int medidorId) {
		this.medidorId = medidorId;
	}
	public SegmentoConsumo getSegmentoConsumo() {
		return segmentoConsumo;
	}
	public void setSegmentoConsumo(SegmentoConsumo segmentoConsumo) {
		this.segmentoConsumo = segmentoConsumo;
	}
	public float getUltimaLectura() {
		return ultimaLectura;
	}
	public void setUltimaLectura(float ultimaLectura) {
		this.ultimaLectura = ultimaLectura;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + medidorId;
		result = prime * result + ((segmentoConsumo == null) ? 0 : segmentoConsumo.hashCode());
		result = prime * result + Float.floatToIntBits(ultimaLectura);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medidor other = (Medidor) obj;
		if (medidorId != other.medidorId)
			return false;
		if (segmentoConsumo != other.segmentoConsumo)
			return false;
		if (Float.floatToIntBits(ultimaLectura) != Float.floatToIntBits(other.ultimaLectura))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Medidor [medidorId=");
		builder.append(medidorId);
		builder.append(",\nsegmentoConsumo=");
		builder.append(segmentoConsumo);
		builder.append(",\nultimaLectura=");
		builder.append(ultimaLectura);
		builder.append("]");
		return builder.toString();
	}	
}
