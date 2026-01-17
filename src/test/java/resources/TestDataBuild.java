package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.DeletePlace;
import pojo.Location;
import pojo.SetPlace;

public class TestDataBuild {
	
	public SetPlace addPlacePayload(String name, String language, String address) {
		
		SetPlace place = new SetPlace();
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress(language);
		place.setWebsite("http://googleIn.com");
		place.setLanguage(address);
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		place.setTypes(list);
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		place.setLocation(location);
		return place;
	}
	
	public DeletePlace deletePlacePayload(String place_id) {
		DeletePlace deletePlace = new DeletePlace();
		deletePlace.setPlace_id(place_id);
		return deletePlace;
	}

}
