package uk.co.bpdts.proximity.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import uk.co.bpdts.proximity.dto.PersonDto;
import uk.co.bpdts.proximity.models.City;
import uk.co.bpdts.proximity.models.LengthUnit;
import uk.co.bpdts.proximity.service.CityService;
import uk.co.bpdts.proximity.service.ProximityService;

@RestController
public class CityProximityControllerImpl implements CityProximityController {

	private CityService cityService;
	private ProximityService proximityService;
	private ModelMapper modelMapper;

	public CityProximityControllerImpl(CityService cityService, ProximityService proximityService,
			ModelMapper modelMapper) {
		this.cityService = cityService;
		this.proximityService = proximityService;
		this.modelMapper = modelMapper;
	}

	/**
	 * Finds users in the vicinity of a named city
	 * 
	 * @param cityName name of city
	 * @param lengthUnit Type of unit for measuring
	 * @param distance length between points to be considered "in the vicinity of"
	 */
	public List<PersonDto> getUsersNearCity(String cityName, LengthUnit lengthUnit, Double distance) {
		City city = cityService.findCityCoordinates(cityName);
		distance = lengthUnit.convertTo(distance, LengthUnit.METRES);

		return proximityService
				.findPeopleInVicinity(city, distance)
				.stream()
				.map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<String> cities() {
		return cityService.getCities().stream().map(city -> city.getName()).collect(Collectors.toList());
	}

}
