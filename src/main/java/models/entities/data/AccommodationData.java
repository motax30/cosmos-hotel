package models.entities.data;

import models.entities.Accommodation;

public class AccommodationData extends DatableImplementation<Accommodation> {
	@Override
	public boolean create(Accommodation accommodation) {
		return !exists("id", accommodation.getId())
				&& (!(accommodation.getDailyPrice() == 0)
				|| accommodation.getNumberOfBeds() != 0)
				&& super.create(accommodation);
	}
}