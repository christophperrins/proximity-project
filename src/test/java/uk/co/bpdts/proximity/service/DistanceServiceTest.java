package uk.co.bpdts.proximity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DistanceServiceTest {

	private DistanceService distanceService;

	@Before
	public void setup() {
		distanceService = new DistanceService();
	}
	
	@Test
	public void distanceCalculationTest() {
		double calculatedDistance = distanceService.distanceBetweenCoordinates(52.4, 54.1, 51.3, 55.6);
		double actualDistance = 160189.675;
		assertEquals(actualDistance, calculatedDistance, actualDistance*0.01);
	}
} 
