package models;

public class AccommodationType {
	private double dailyPrice;
	private int numberBeds;
	private String description;
	
	public AccommodationType(double dailyPrice,int numberBeds,String description) {
		super();
		this.dailyPrice = dailyPrice;
		this.numberBeds = numberBeds;
		this.description = description;
	}
	
	public double getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	public int getNumberBeds() {
		return numberBeds;
	}
	public void setNumberBeds(int numberBeds) {
		this.numberBeds = numberBeds;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dailyPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + numberBeds;
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
		AccommodationType other = (AccommodationType) obj;
		if (Double.doubleToLongBits(dailyPrice) != Double.doubleToLongBits(other.dailyPrice))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (numberBeds != other.numberBeds)
			return false;
		return true;
	}
}
