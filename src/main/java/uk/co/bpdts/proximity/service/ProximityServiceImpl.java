package uk.co.bpdts.proximity.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.bpdts.proximity.exceptions.BaseException;
import uk.co.bpdts.proximity.models.City;
import uk.co.bpdts.proximity.models.Person;
import uk.co.bpdts.proximity.utils.ReadConfig;

@Service
public class ProximityServiceImpl implements ProximityService {

	private GpsCoordinateDistanceCalculatable distanceCalculatable;
	private ReadConfig readConfig;

	public ProximityServiceImpl(GpsCoordinateDistanceCalculatable distanceCalculatable, ReadConfig readConfig) {
		this.distanceCalculatable = distanceCalculatable;
		this.readConfig = readConfig;
	}

	public String getUsersAsJson() {
		try {
			URL url = new URL(readConfig.getPropertyValue("kerokuappurl"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("accept", "application/json");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setInstanceFollowRedirects(true);
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			return content.toString();
		} catch (MalformedURLException e) {
			throw new BaseException("URL in config file does not look correct", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ProtocolException e) {
			throw new BaseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			throw new BaseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Finds a list of people and converts them into Person List
	 * @return Person list
	 */
	public List<Person> findAllPeople() {
		try {
			String jsonString = getUsersAsJson();
			return Arrays.asList(new ObjectMapper().readValue(jsonString, Person[].class));
		} catch (JsonProcessingException e) {
			throw new BaseException("Our user server has sent something unrecognised. Please check back later", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param person
	 * @param city
	 * @param maxDistance allowed to still be withing the city proximity in metres
	 * @return whether person is in the vicinity of a city
	 * @throws NullPointerException if person has null latitude or longitude
	 */
	boolean checkProximity(Person person, City city, double maxDistance) throws NullPointerException {
		if (person.getLatitude() == null || person.getLongitude() == null) {
			throw new NullPointerException("null pointer on person latitude or longitude");
		}
		double distance = distanceCalculatable.distanceBetweenCoordinates(city.getLatitude(), city.getLongitude(),
				person.getLatitude(), person.getLongitude());
		return distance < maxDistance;
	}

	@Override
	public List<Person> findPeopleInVicinity(City city, double distance) {
		return findAllPeople().stream().filter(person -> {
			try {
				return checkProximity(person, city, distance);
			} catch (NullPointerException e) {
				return false;
			}
		}).collect(Collectors.toList());
	}

}
