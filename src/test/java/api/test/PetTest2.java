package api.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.enpoints.PetEndpoints;
import api.enpoints.PetEndpointsPP;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tags;
import io.restassured.response.Response;

public class PetTest2 {
	Faker faker;
	Pet petPayload;
	Category cat;
	List<Tags> tags;
	Logger logger;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		petPayload = new Pet();
		cat = new Category();
		tags = new ArrayList<Tags>();
		logger = LogManager.getLogger(this.getClass());

		petPayload.setId(faker.idNumber().hashCode());
		cat.setId(faker.idNumber().hashCode());
		cat.setName(faker.animal().name());
		petPayload.setCategory(cat);
		petPayload.setName(faker.animal().name());

		for (int i = 0; i < 3; i++) {
			int id = faker.idNumber().hashCode();
			String name = faker.lorem().word();
			tags.add(new Tags(id, name));
		}
		petPayload.setTags(tags);

		String[] photoUrls = { faker.internet().url(), faker.internet().url() };

		petPayload.setPhotoUrls(photoUrls);

	}

	@Test(priority = 1)
	private void testPostPet() {
		logger.info("Starting Testing Create Pet");
		logger.info("Creating a new pet");
		Response response = PetEndpointsPP.createPet(petPayload);
		response.then().log().all();
		logger.info("Validate pet response with status code is 200");
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("Finish Testing Create Pet");
	}

	@Test(priority = 2)
	private void testGetPetByPetID() {
		logger.info("Starting Get Pet By Pet ID");

		logger.info("Getting Pet By: " + this.petPayload.getId());
		Response response = PetEndpoints.getPetByPetID(this.petPayload.getId());
		response.then().log().all();
		logger.info("Validate Pet Response");

		Assert.assertEquals(response.statusCode(), 200);
		logger.info("Finish Testing get Pet By Pet ID");

	}

	@Test(priority = 3)
	private void testUpdatePetByPetID() {
		petPayload.setName(faker.animal().name());
		logger.info("Starting Testing update Pet by " + this.petPayload.getId());
		logger.info("Updating pet with PetId");
		Response response = PetEndpoints.updatePetByPetID(this.petPayload);

		response.then().log().all();

		logger.info("Validate Pet Response after update Pet");
		Assert.assertEquals(response.statusCode(), 200);

		logger.info("Finish Testing Update Pet with " + this.petPayload.getId());
	}

	@Test(priority = 4)
	private void testDeletePetByPetID() {

		logger.info("Starting Testing Delete Pet");
		logger.info("Deleting Pet with " + this.petPayload.getId());

		Response response = PetEndpoints.deletePetByID(this.petPayload.getId());
		response.then().log().all();
		logger.info("Validate Pet Response after delete pet with status code is 200");
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("Finish Testing Delete Pet with PetID");

	}

}
