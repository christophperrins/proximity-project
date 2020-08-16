package uk.co.bpdts.proximity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import uk.co.bpdts.proximity.exceptions.NotFoundException;
import uk.co.bpdts.proximity.models.City;

@Service
public class CityServiceImpl implements CityService {

	public List<City> getCities() {
		ArrayList<City> cities = new ArrayList<City>();
		cities.add(new City("London", 51.509865, -0.118092));
		return cities;
	}

	@Override
	public City findCityCoordinates(String cityName) {
		return getCities().stream().filter(city -> city.getName().toLowerCase().equals(cityName.toLowerCase()))
			.findFirst()
			.orElseThrow(() -> new NotFoundException("Please visit /api/city for a list of accepted cities"));
		
	}
	
}
