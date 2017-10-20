package models.util;

import java.util.EnumMap;
import java.util.Map;

import models.enumerates.TypeAccommodation;

public class TableDayleValue {
	public static Map<TypeAccommodation, Double> table;
	public TableDayleValue() {
		 table = new EnumMap<>(TypeAccommodation.class);
		 table.put(TypeAccommodation.SIMPLES, (double) 90);
		 table.put(TypeAccommodation.DUPLO, (double) 120);
	}
}
