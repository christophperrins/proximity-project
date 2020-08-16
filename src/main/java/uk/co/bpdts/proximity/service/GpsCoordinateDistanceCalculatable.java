package uk.co.bpdts.proximity.service;

public interface GpsCoordinateDistanceCalculatable {

	double distanceBetweenCoordinates(double lat1, double long1, double lat2, double long2);
}
