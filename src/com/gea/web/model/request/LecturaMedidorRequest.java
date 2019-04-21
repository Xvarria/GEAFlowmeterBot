package com.gea.web.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Mchavarria
 * @since 13/04/2019
 * 
 * POJO para serializar lecturas de medidores
 */
public class LecturaMedidorRequest {
	
	private int measurerInternalId;
	private double volumen;
	private double temperature;
	private String error;
	
	@JsonProperty("measurer_internal_id")
	public int getMeasurerInternalId() {
		return measurerInternalId;
	}
	
	public void setMeasurerInternalId(int measurerInternalId) {
		this.measurerInternalId = measurerInternalId;
	}
	
	public double getVolumen() {
		return volumen;
	}
	
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + measurerInternalId;
		long temp;
		temp = Double.doubleToLongBits(temperature);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volumen);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		LecturaMedidorRequest other = (LecturaMedidorRequest) obj;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (measurerInternalId != other.measurerInternalId)
			return false;
		if (Double.doubleToLongBits(temperature) != Double.doubleToLongBits(other.temperature))
			return false;
		if (Double.doubleToLongBits(volumen) != Double.doubleToLongBits(other.volumen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LecturaMedidorRequest [measurerInternalId=");
		builder.append(measurerInternalId);
		builder.append(", \n volumen=");
		builder.append(volumen);
		builder.append(", \n temperature=");
		builder.append(temperature);
		builder.append(", \n error=");
		builder.append(error);
		builder.append("]");
		return builder.toString();
	}
}
