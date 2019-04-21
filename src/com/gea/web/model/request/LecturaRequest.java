package com.gea.web.model.request;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Mchavarria
 * @since 13/04/2019
 * 
 * POJO para serializar lecturas
 */
public class LecturaRequest {

	private String apiKey;
	private long timestamp;
	private Set<LecturaMedidorRequest> data;
	
	public LecturaRequest() {
		super();
		this.data = new HashSet<>();
	}

	@JsonProperty("api_key")
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Set<LecturaMedidorRequest> getData() {
		return data;
	}

	public void setData(Set<LecturaMedidorRequest> data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiKey == null) ? 0 : apiKey.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
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
		LecturaRequest other = (LecturaRequest) obj;
		if (apiKey == null) {
			if (other.apiKey != null)
				return false;
		} else if (!apiKey.equals(other.apiKey))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LecturaRequest [apiKey=");
		builder.append(apiKey);
		builder.append(", \\n timestamp=");
		builder.append(timestamp);
		builder.append(", \\n data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
}
