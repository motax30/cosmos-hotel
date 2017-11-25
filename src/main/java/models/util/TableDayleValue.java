package models.util;

import java.util.EnumMap;
import java.util.Map;

import models.enumerates.AccommodationType;

public class TableDayleValue {
	public static Map<AccommodationType, Double> table;
	public TableDayleValue() {
		 table = new EnumMap<>(AccommodationType.class);
		 table.put(AccommodationType.SINGLE, (double) 90);
		 table.put(AccommodationType.DOUBLE, (double) 120);
	}
}
