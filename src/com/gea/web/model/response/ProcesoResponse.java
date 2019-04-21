package com.gea.web.model.response;

public class ProcesoResponse {

	protected ReasonCode reasonCode;
	protected CompletationCode completationCode;

	public ProcesoResponse(ProcesoResponse procesoResponse) {
		super();
		this.reasonCode = procesoResponse.getReasonCode();
		this.completationCode = procesoResponse.getCompletationCode();
	}
	
	public ProcesoResponse() {
		super();
	}

	public ReasonCode getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(ReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}

	public CompletationCode getCompletationCode() {
		return completationCode;
	}

	public void setCompletationCode(CompletationCode completationCode) {
		this.completationCode = completationCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completationCode == null) ? 0 : completationCode.hashCode());
		result = prime * result + ((reasonCode == null) ? 0 : reasonCode.hashCode());
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
		ProcesoResponse other = (ProcesoResponse) obj;
		if (completationCode != other.completationCode)
			return false;
		if (reasonCode != other.reasonCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProcesoResponse [reasonCode=");
		builder.append(reasonCode);
		builder.append(", \n completationCode=");
		builder.append(completationCode);
		builder.append("]");
		return builder.toString();
	}
	
	

}