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
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tags;
import api.utilities.DataProvider;
import io.restassured.response.Response;


public class DDTest {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
	public void testPostPet(String petId, String categoryId, String categoryName,  String petName, String photoUrl, String tagId, String tagName, String status) {
		
		Pet petPayload = new Pet();
		Category category = new Category();
		List<Tags> tags = new ArrayList<Tags>();
		String[] photoUrls = {photoUrl};
		
		category.setId(Integer.parseInt(categoryId));
		category.setName(categoryName);
		petPayload.setId(Integer.parseInt(petId));
		petPayload.setCategory(category);
		petPayload.setName(petName);
		petPayload.setPhotoUrls(photoUrls);
		tags.add(new Tags(Integer.parseInt(tagId), tagName));
		petPayload.setTags(tags);
		petPayload.setStatus(status);
		
		Response response = PetEndpoints.createPet(petPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}

//
	@Test(priority = 4, dataProvider = "PetID", dataProviderClass = DataProvider.class)
	private void testDeletePetByPetID(String petId) {
		

		Response response = PetEndpoints.deletePetByID(Integer.parseInt(petId));
		response.then().log().all();
//		
	

	}

}
