package resources;

// Enum is special class in java which has collection of constants or methods
public enum APIResourses {

	addPlaceAPI("/maps/api/place/add/json"), 
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resourse;

	private APIResourses(String resourse) {
		// TODO Auto-generated constructor stub
		this.resourse = resourse;
	}

	public String getResourse() {
		return resourse;
	}

}
