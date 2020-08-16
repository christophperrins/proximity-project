package uk.co.bpdts.proximity.models;

public enum LengthUnit {
	MILES(0.621371),
	KILOMETRES(1),
	METRES(1000);
	
	private double conversionFromKilometres;
	
	private LengthUnit(double conversionFromKilometres) {
		this.conversionFromKilometres = conversionFromKilometres;
	}
	
	public double convertTo(double currentValue, LengthUnit lengthUnit) {
		return currentValue / this.conversionFromKilometres * lengthUnit.conversionFromKilometres;
	}
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
}
