package uk.co.bpdts.proximity.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class LengthUnitTest {

	@Test
	public void lengthUnitConversion() {
		LengthUnit lengthUnit = LengthUnit.MILES;
		double actual = lengthUnit.convertTo(10, LengthUnit.METRES);
		double expected = 16093.4;

		assertEquals(expected, actual, expected*0.01);
	}
}
