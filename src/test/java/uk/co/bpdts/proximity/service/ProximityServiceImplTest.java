package uk.co.bpdts.proximity.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import uk.co.bpdts.proximity.models.City;
import uk.co.bpdts.proximity.models.Person;
import uk.co.bpdts.proximity.utils.ReadConfig;

@RunWith(MockitoJUnitRunner.class)
public class ProximityServiceImplTest {

	@Mock
	private GpsCoordinateDistanceCalculatable gpsCoordinateDistanceCalculatable;
	
	@Mock
	private ReadConfig readConfig;
	
	@Spy
	@InjectMocks
	private ProximityServiceImpl proximityServiceImpl;
		
	@Test
	public void checkProximityTest() {
		double personLat = 53.471990;
		double personLong =  -2.289820;
		double cityLat = 51.509865;
		double cityLong = -0.118092;
		
		Person person = new Person(1L, "Chris", "Perrins", "chrisperrins95@gmail.com", "127.0.0.1", personLat, personLong);
		City city = new City("London", cityLat, cityLong);
		Mockito.doReturn(2610000d).when(gpsCoordinateDistanceCalculatable).distanceBetweenCoordinates(cityLat, cityLong, personLat, personLong);
		assertTrue(proximityServiceImpl.checkProximity(person, city, 2630000d));
	}
	
	@Test
	public void checkProximityFailTest() {
		double personLat = 53.471990;
		double personLong =  -2.289820;
		double cityLat = 51.509865;
		double cityLong = -0.118092;
		
		Person person = new Person(1L, "Chris", "Perrins", "chrisperrins95@gmail.com", "127.0.0.1", personLat, personLong);
		City city = new City("London", cityLat, cityLong);
		Mockito.doReturn(2610000d).when(gpsCoordinateDistanceCalculatable).distanceBetweenCoordinates(cityLat, cityLong, personLat, personLong);
		assertFalse(proximityServiceImpl.checkProximity(person, city, 2600000d));
	}
	
	@Test(expected = NullPointerException.class)
	public void checkProximityNullTest() {
		Double personLat = null;
		Double personLong =  -2.289820;
		double cityLat = 51.509865;
		double cityLong = -0.118092;
		
		Person person = new Person(1L, "Chris", "Perrins", "chrisperrins95@gmail.com", "127.0.0.1", personLat, personLong);
		City city = new City("London", cityLat, cityLong);

		proximityServiceImpl.checkProximity(person, city, 2600000d);
	}
	
}
