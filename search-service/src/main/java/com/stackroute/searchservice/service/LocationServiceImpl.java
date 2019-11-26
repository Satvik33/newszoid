package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Location;
import com.stackroute.searchservice.exceptions.LocationAlreadyExistsException;
import com.stackroute.searchservice.exceptions.LocationNotFoundException;
import com.stackroute.searchservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository LocationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository LocationRepository) {
        this.LocationRepository = LocationRepository;
    }

    @Override
    public Location getLocation(String Location) throws LocationNotFoundException {
        Location LocationFind = LocationRepository.findLocationByLocation(Location);
        if(LocationFind == null) {
            throw new LocationNotFoundException("Location with name "+Location+" not found!");
        }
        return LocationFind;
    }

    @Override
    public Location addLocation(Location Location) throws LocationAlreadyExistsException {
        Location LocationFind = LocationRepository.findLocationByLocation(Location.getLocation());
        if(LocationFind != null) {
            throw new LocationAlreadyExistsException("Location with name "+Location+" already exists!");
        }
        return LocationRepository.save(Location);
    }

    @Override
    public Location updateLocation(Location Location) throws LocationNotFoundException {
        Location LocationFind = LocationRepository.findLocationByLocation(Location.getLocation());
        if(LocationFind == null) {
            throw new LocationNotFoundException("Location with name "+Location+" not found!");
        }
        return LocationRepository.save(Location);
    }

    @Override
    public Location deleteLocation(String Location) throws LocationNotFoundException {
        Location LocationFind = LocationRepository.findLocationByLocation(Location);
        if(LocationFind == null) {
            throw new LocationNotFoundException("Location with name "+Location+" not found!");
        }
        LocationRepository.delete(LocationFind);
        return LocationFind;
    }
}
