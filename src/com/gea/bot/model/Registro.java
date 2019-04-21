package com.gea.bot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gea.bot.model.exception.ExcepcionEjecucion;
import com.gea.bot.model.exception.TipoError;

public class Registro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean bloqueado;
	private List<Medidor> listaMedidor;

	public Registro() {
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	protected List<Medidor> getListaMedidor() {
		return listaMedidor;
	}

	protected void setListaMedidor(List<Medidor> listaMedidor) {
		this.listaMedidor = listaMedidor;
	}
	
	public void initMedidorList() {
		this.listaMedidor = new ArrayList<>();
	}
	
	public void addMedidor(Medidor medidor, boolean sobreescribir) throws ExcepcionEjecucion{
		boolean existe = false;
		Iterator <Medidor> medidorIterator = this.listaMedidor.iterator();
		int index = -1;
		while (medidorIterator.hasNext() && !existe) {
			index++;
			Medidor current = medidorIterator.next(); 
			existe = current.getMedidorId() == medidor.getMedidorId();
		}
		if (existe && sobreescribir) {
			listaMedidor.set(index, medidor);
		} else if (existe && !sobreescribir) {
			throw new ExcepcionEjecucion(TipoError.ERROR_0007_MEDIDOR_YA_EXISTE);
		}else if (!existe) {
			this.listaMedidor.add(medidor);
		}
	}
	
	public void removeMedidor(int medidorId) throws ExcepcionEjecucion {
		boolean existe = false;
		Iterator <Medidor> medidorIterator = this.listaMedidor.iterator();
		int index = -1;
		while (medidorIterator.hasNext() && !existe) {
			index++;
			Medidor current = medidorIterator.next(); 
			existe = current.getMedidorId() == medidorId;
		}
		if (existe) {
			listaMedidor.remove(index);
		} else {
			throw new ExcepcionEjecucion(TipoError.ERROR_0010_MEDIDOR_NO_EXISTE);
		}		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bloqueado ? 1231 : 1237);
		result = prime * result + ((listaMedidor == null) ? 0 : listaMedidor.hashCode());
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
		Registro other = (Registro) obj;
		if (bloqueado != other.bloqueado)
			return false;
		if (listaMedidor == null) {
			if (other.listaMedidor != null)
				return false;
		} else if (!listaMedidor.equals(other.listaMedidor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Registro [bloqueado=");
		builder.append(bloqueado);
		builder.append(",\nlistaMedidor=");
		builder.append(listaMedidor);
		builder.append("]");
		return builder.toString();
	}
	
	public void displayMedidores() {
		int index = 0;
		for(Medidor medidor : this.listaMedidor) {
	    	System.out.println(" - "+index+" -> " + medidor.toStringLine());
	    	index++;
	    }			
	}
}