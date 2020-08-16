package uk.co.bpdts.proximity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uk.co.bpdts.proximity.models.City;

public interface CityService {
	
	public City findCityCoordinates(String city);
	
	public List<City> getCities();
}
