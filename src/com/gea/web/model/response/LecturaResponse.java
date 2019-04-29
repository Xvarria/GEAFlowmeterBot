package com.gea.web.model.response;

/**
 * 
 * @author Mchavarria
 * @since 13/04/2019
 * 
 * POJO para serializar respuesta a la solicitud creación de lecturas de medidores
 */
public class LecturaResponse extends ProcesoResponse {
	
	private long initTime;
	private long finishTime;
	
	public LecturaResponse() {
		super();
	}
	
	public LecturaResponse(ProcesoResponse response, long initTime, long finishTime) {
		super(response);
		this.initTime = initTime;
		this.finishTime = finishTime;
	}

	public LecturaResponse(ReasonCode reasonCode, CompletationCode completationCode, long initTime, long finishTime) {
		super();
		this.reasonCode = reasonCode;
		this.completationCode = completationCode;
		this.initTime = initTime;
		this.finishTime = finishTime;
	}

	public long getInitTime() {
		return initTime;
	}
	
	public void setInitTime(long initTime) {
		this.initTime = initTime;
	}
	
	public long getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (finishTime ^ (finishTime >>> 32));
		result = prime * result + (int) (initTime ^ (initTime >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LecturaResponse other = (LecturaResponse) obj;
		if (finishTime != other.finishTime)
			return false;
		if (initTime != other.initTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LecturaResponse [ProcesoResponse=");
		builder.append(super.toString());
		builder.append(",\\n initTime=");
		builder.append(initTime);
		builder.append(",\\n finishTime=");
		builder.append(finishTime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
