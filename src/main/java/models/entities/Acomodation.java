package models.entities;

import java.util.List;

public class Acomodation {
	private Integer qtd_camas;
	private List<AccommodationType> acomodationType;
	
	public Acomodation() {
	}
	
	public Acomodation(Integer qtd_camas, List<AccommodationType> acomodationType) {
		super();
		this.qtd_camas = qtd_camas;
		this.acomodationType = acomodationType;
	}
	/**
	 * @return the qtd_camas
	 */
	public Integer getQtd_camas() {
		return qtd_camas;
	}
	/**
	 * @param qtd_camas the qtd_camas to set
	 */
	public void setQtd_camas(Integer qtd_camas) {
		this.qtd_camas = qtd_camas;
	}
	/**
	 * @return the acomodationType
	 */
	public List<AccommodationType> getAcomodationType() {
		return acomodationType;
	}
	/**
	 * @param acomodationType the acomodationType to set
	 */
	public void setAcomodationType(List<AccommodationType> acomodationType) {
		this.acomodationType = acomodationType;
	}
	
	
}
