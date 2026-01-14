package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.SetPlace;

public class TestDataSetup {
	
	public SetPlace addPlacePayload() {
		
		SetPlace place = new SetPlace();
		place.setAccuracy(50);
		place.setName("Mandeep House");
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress("29, side layout, cohen 09");
		place.setWebsite("http://googleIn.com");
		place.setLanguage("Hindi");
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

}
