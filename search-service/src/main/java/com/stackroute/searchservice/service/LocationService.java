package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Location;
import com.stackroute.searchservice.exceptions.LocationAlreadyExistsException;
import com.stackroute.searchservice.exceptions.LocationNotFoundException;


public interface LocationService {
    Location getLocation(String Location) throws LocationNotFoundException;
    Location addLocation(Location Location) throws LocationAlreadyExistsException;
    Location updateLocation(Location Location) throws LocationNotFoundException;
    Location deleteLocation(String Location) throws LocationNotFoundException;
}
