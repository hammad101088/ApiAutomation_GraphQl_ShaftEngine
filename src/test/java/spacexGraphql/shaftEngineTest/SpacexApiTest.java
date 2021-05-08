package spacexGraphql.shaftEngineTest;

import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.tools.io.JSONFileManager;

import io.restassured.response.Response;
import spacexGraphql.ShaftEngine.SpacexApiObjectModel;
import utilities.shaftEngine.UtilityClass;

import org.testng.annotations.BeforeClass;

public class SpacexApiTest {

	// ******* variables and instances *******//
	RestActions apiObject;
	SpacexApiObjectModel spacexApiObjectModel;
	JSONFileManager jsonFile;

	@BeforeClass
	public void beforeClass() {
		spacexApiObjectModel = new SpacexApiObjectModel(apiObject);
		jsonFile = new JSONFileManager(System.getProperty("testDataFolderPath")+"SpacexJsonFile.json");
	}

	@Test
	public void testPatLaunches_checkRocketName_shouldBeFalcon9() {

		Response response = spacexApiObjectModel.getPastLaunches();
		UtilityClass.assertGraphqlResponse(response,"data.launchesPast[0].rocket.rocket_name", "Falcon 9");
	}
	
	@Test
	public void insertUser_checkuserName_shouldBeTheSame() {

		Response response = spacexApiObjectModel.insertUser(jsonFile.getTestData("name"),jsonFile.getTestData("rocket"));
		UtilityClass.assertGraphqlResponse(response,"data.insert_users.returning[0].name", "Mohamed Hammad");
		UtilityClass.assertGraphqlResponse(response,"data.insert_users.returning[0].rocket", "Hammad's rocket");

	}

}
