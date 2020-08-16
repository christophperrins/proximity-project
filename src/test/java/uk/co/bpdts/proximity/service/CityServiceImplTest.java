package uk.co.bpdts.proximity.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import uk.co.bpdts.proximity.exceptions.NotFoundException;
import uk.co.bpdts.proximity.models.City;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

	
	@Spy
	private CityServiceImpl cityServiceImpl;
	
	@Test
	public void testCitySearch() {
		List<City> cities = new ArrayList<City>();
		City paris = new City("Paris", 48.8566, 2.3522);
		cities.add(paris);
		
		Mockito.doReturn(cities).when(cityServiceImpl).getCities();
		
		City city = cityServiceImpl.findCityCoordinates("paris");		
		assertTrue(city.equals(paris));
	}
	
	@Test(expected = NotFoundException.class)
	public void testCitySearchFailure() {
		List<City> cities = new ArrayList<City>();
		City paris = new City("Paris", 48.8566, 2.3522);
		cities.add(paris);
		
		Mockito.doReturn(cities).when(cityServiceImpl).getCities();
		
		City city = cityServiceImpl.findCityCoordinates("london");		
	}
}
