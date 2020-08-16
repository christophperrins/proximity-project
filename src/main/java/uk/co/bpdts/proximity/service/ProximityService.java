package uk.co.bpdts.proximity.service;

import java.util.List;

import uk.co.bpdts.proximity.models.City;
import uk.co.bpdts.proximity.models.Person;

public interface ProximityService {

	public List<Person> findPeopleInVicinity(City city, double distance);
	
}
