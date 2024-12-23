package api.enpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpointsPP {

	public static ResourceBundle getURL() {

		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;

	}
	public static Response createPet(Pet payload) {
	
		String post_url = getURL().getString("post_url");
		
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);

		return response;
	}

	public static Response getPetByID(int petId) {
		String get_url = getURL().getString("get_url");
		Response response = given().pathParam("petId", petId).when().get(get_url);

		return response;
	}



	public static Response updatePetbyPetID(Pet petPayload, String petID) {
		String update_url = getURL().getString("update_url");
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(petPayload).when()
				.put(update_url);

		return response;

	}

	public static Response deletePetByID(int petId) {
		String delete_url = getURL().getString("delete_url");
		Response response = given().pathParam("petId", petId).when().get(delete_url);

		return response;
	}
}
