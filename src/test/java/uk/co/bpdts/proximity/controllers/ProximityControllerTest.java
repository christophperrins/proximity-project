package uk.co.bpdts.proximity.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import uk.co.bpdts.proximity.models.City;
import uk.co.bpdts.proximity.service.CityService;

@RunWith(MockitoJUnitRunner.class)
public class ProximityControllerTest {

	@Mock
	private CityService cityService;
	
	@Spy
	@InjectMocks
	private CityProximityControllerImpl cityProximityControllerImpl;
	
	@Test
	public void cityNameExtractionTest() {
		List<City> cities = new ArrayList<City>();
		cities.add(new City("Berlin", 52.5200, 13.4050));
		cities.add(new City("Paris", 48.8566, 2.3522));

		Mockito.when(cityService.getCities()).thenReturn(cities);
		
		List<String> citiesSelection = new ArrayList<String>();
		citiesSelection.add("Berlin");
		citiesSelection.add("Paris");
		assertEquals(citiesSelection, cityProximityControllerImpl.cities());
		
	}
	
}
