package com.goparty.webservice.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import com.goparty.exception.DuplicateLocationException;
import com.goparty.exception.LocationNotFoundException;
import com.goparty.exception.ValidationException;
import com.goparty.model.Location;
import com.goparty.webservice.LocationService;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service("locationService")
public class LocationServiceImpl implements LocationService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, Location> locations = new HashMap<String, Location>();

	@Autowired
	private Validator validator;
	
	//security handled by URL mapping in the xml 
	//@Secured("ROLE_RESTCLIENT")
	@Override
    public Location readLocation(String location) throws LocationNotFoundException {
		
		Location locationData = locations.get(location);
		
		if (locationData == null) {
			locationData = new Location();
			locationData.setDate(new Date());
			locationData.setLocation(location);
			Random rnd = new Random();
			locationData.setId("id"+rnd.nextInt());
			
			//throw new LocationNotFoundException();
		}
		
		return locationData;
		
    }

	@Override
	public Location createLocation(Location locationData) throws DuplicateLocationException {
		
		if (locations.get(locationData.getLocation()) != null) {
			throw new DuplicateLocationException();
		}

		BeanPropertyBindingResult br = new BeanPropertyBindingResult(locationData, "locationData");
		validator.validate(locationData, br);
		if (br.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError e : br.getFieldErrors()) {
				logger.debug(e.getDefaultMessage());
				errors.put(e.getField(), e.getDefaultMessage());
			}
			
			throw new ValidationException(errors);
		}

		setNewID(locationData);
		storeLocation(locationData);
		
		return locationData;
	}

	@Override
	public Location updateorCreateLocation(Location locationData) {

		if (locations.get(locationData.getLocation()) == null) {
			setNewID(locationData);
		}
		
		storeLocation(locationData);
		
		return locationData;
		
	}

	private void setNewID(Location locationData) {
		//setting the ID
		String id = UUID.randomUUID().toString();
		locationData.setId(id);
		
	}
	
	private void storeLocation(Location locationData) {
		
		locations.put(locationData.getLocation(), locationData);
		
	}

	@Override
	public Collection<Location> readAllLocations() {
		return locations.values();
	}

	@Override
	public void deleteLocation(String location) throws LocationNotFoundException {
		Location locationData = locations.get(location);
		
		if (locationData == null) {
			throw new LocationNotFoundException();
		}

		locations.remove(location);
		
	}

	@Override
	public void deleteAllLocation() {
		
		locations.clear();
		
	}
    
}