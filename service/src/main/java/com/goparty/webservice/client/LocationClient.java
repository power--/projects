package com.goparty.webservice.client;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.goparty.exception.DuplicateLocationException;
import com.goparty.exception.LocationNotFoundException;
import com.goparty.exception.meta.LocationResponseExceptionMapper;
import com.goparty.model.Location;
import com.goparty.webservice.LocationService;


public class LocationClient implements LocationService {

	private LocationService locationService;
	
	public static enum CLIENT_TYPE {REST("REST"), SOAP("SOAP");
		
		private String value; 
		private CLIENT_TYPE(String value) {
			this.value = value;
		}
		
		public static CLIENT_TYPE fromString(String value) {
			
			if (REST.value.equalsIgnoreCase(value)) {
				return REST;
			}

			if (SOAP.value.equalsIgnoreCase(value)) {
				return SOAP;
			}
			
			return null;
		}
	};
	
	public LocationClient(String applicationURI, CLIENT_TYPE clientType) {
		
		if (clientType == CLIENT_TYPE.REST) {
			List<Object> providers = new LinkedList<Object>();
			providers.add(new LocationResponseExceptionMapper());
			locationService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/", LocationService.class, providers, true);
			ClientConfiguration cfgProxy = WebClient.getConfig(locationService);
//			cfgProxy.getHttpConduit().getAuthorization().setPassword("restuser");
//			cfgProxy.getHttpConduit().getAuthorization().setUserName("restuser");
		} else {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(LocationService.class);
			factory.setAddress(applicationURI + "/cxf/soap/");
//			factory.setUsername("restuser");
//			factory.setPassword("restuser");
			locationService = (LocationService) factory.create();
		}
		
		
		
	}
	
	@Override
	public Location createLocation(Location locationData)
			throws DuplicateLocationException {
		return locationService.createLocation(locationData);
	}

	@Override
	public Location updateorCreateLocation(Location locationData) {
		return locationService.updateorCreateLocation(locationData);
	}

	@Override
	public Location readLocation(String location) throws LocationNotFoundException {
		return locationService.readLocation(location);
	}

	@Override
	public Collection<Location> readAllLocations() {
		return locationService.readAllLocations();
	}

	@Override
	public void deleteLocation(String location)
			throws LocationNotFoundException {
		
		locationService.deleteLocation(location);
		
	}

	@Override
	public void deleteAllLocation() {
		locationService.deleteAllLocation();		
	}

}