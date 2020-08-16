package uk.co.bpdts.proximity.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uk.co.bpdts.proximity.dto.PersonDto;
import uk.co.bpdts.proximity.models.LengthUnit;

public interface CityProximityController {
	
	/**
	 * This endpoint looks for users in the vicinity of a city
	 * @param cityName see /api/city for list available
	 * @param lengthUnit type of unit to be used
	 * @param distance of the unit used
	 * @return List of PersonDto
	 */
	@RequestMapping(path = "/api/city/{city}/users", method = {RequestMethod.GET})
	public List<PersonDto> getUsersNearCity(@PathVariable(name = "city", required = true) String cityName, 
			@RequestParam(name = "unit", defaultValue = "miles") LengthUnit lengthUnit,
			@RequestParam(name = "distance", defaultValue = "60.0") Double distance
			);
	
	/**
	 * 
	 * @return a list of city names available
	 */
	@RequestMapping(path = "/api/city", method = {RequestMethod.GET})
	public List<String> cities();

}
