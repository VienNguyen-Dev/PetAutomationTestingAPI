package api.enpoints;

import static io.restassured.RestAssured.*;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpoints {

	public static Response createPet(Pet payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return response;
	}

	public static Response getPetByID(int petId) {
		Response response = given().pathParam("petId", petId).when().get(Routes.get_url);

		return response;
	}

	public static Response updatePetByPetID(Pet payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.when().post(Routes.update_url);

		return response;
	}

	public static Response getPetByPetID(int PetId) {
		Response response = given().pathParam("petId", PetId).when().get(Routes.get_url);
		return response;
	}

	public static Response updatePetbyPetID(Pet petPayload, String petID) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(petPayload).when()
				.put(Routes.update_url);

		return response;

	}

	public static Response deletePetByID(int petId) {
		Response response = given().pathParam("petId", petId).when().get(Routes.delete_url);

		return response;
	}
}
