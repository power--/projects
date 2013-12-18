package com.goparty.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goparty.model.Location;
import com.goparty.webservice.client.LocationClient;

import static org.junit.Assert.assertEquals;

public class LocationClientTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Test
	public void getLocation() {
		
		String city = "test";
		
		Location locationData = new LocationClient("http://localhost:8080", LocationClient.CLIENT_TYPE.REST).readLocation(city);
		logger.debug("", locationData);
		
		assertEquals(city, locationData.getLocation());
		
	}
	
}
