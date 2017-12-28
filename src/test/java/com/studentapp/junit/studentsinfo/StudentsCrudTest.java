package com.studentapp.junit.studentsinfo;

import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class StudentsCrudTest extends TestBase {
	public static String firstName= "Ashwin"+TestUtils.getRandomValue();
	
	
	@Title("Adding a Student using Post Request")
	@Test
	public void test001 ()  {
		
		String student = "{\"firstName\":\"Ashwin2\",\"lastName\":\"Harper\",\"email\":\"ashwin.rhoncus.Proin@massaQuisqueporttitor.org\",\"programme\":\"Financial Analysis\",\"courses\":[\"Accounting\",\"Statistics\"]}";
		
		 SerenityRest.given()
		.contentType(ContentType.JSON)
		.when()
		.body(student)
		.post()
		.then().statusCode(201);
		
	}
	
	@Title("Checking whether added Student is present")
	@Test
	public void test002 ()  {
		
		
		
	HashMap<String,Object> values= SerenityRest.rest().given()
			.when()
			.get("/list")
			.then()
			.extract()
			.path("findAll{it.firstName=='Ashwin2'}.get(0)");
	
	System.out.println("The values are "+values);
	assertThat(values,hasValue("Ashwin2"));
		
	}
	
	
	@Title("Updating the added Student")
	@Test
	public void test003 ()  {
		
	String student = "{\"firstName\":\"Ashwin1_Updated\",\"lastName\":\"Harper\",\"email\":\"ashwin15.rhoncus.Proin@massaQuisqueporttitor.org\",\"programme\":\"Financial Analysis\",\"courses\":[\"Accounting\",\"Statistics\"]}";
		
		
	SerenityRest.rest().given().contentType(ContentType.JSON)
			.when()
			.body(student)
			.put("/105");
	
		
	}

}
