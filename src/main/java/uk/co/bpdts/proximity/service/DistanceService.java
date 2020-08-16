package uk.co.bpdts.proximity.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceService implements GpsCoordinateDistanceCalculatable{

	/**
	 * Calculates the distance between two coordinates in metres
	 * 
	 * @return distance in metres
	 */
	public double distanceBetweenCoordinates(double lat1, double long1, double lat2, double long2) {
		int earthRadius = 6371000;
		double deltaLatitudeRadians = (lat1 - lat2) * Math.PI / 180;
		double deltaLongitudeRadians = (long1 - long2) * Math.PI / 180;
		double halfChordLengthSquared = (Math.sin(deltaLatitudeRadians / 2) * Math.sin(deltaLatitudeRadians / 2))
				+ Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) * Math.sin(deltaLongitudeRadians / 2)
						* Math.sin(deltaLongitudeRadians / 2);
		double angularDistance = 2
				* Math.atan2(Math.sqrt(halfChordLengthSquared), Math.sqrt(1 - halfChordLengthSquared));
		return earthRadius * angularDistance;
	}
}
