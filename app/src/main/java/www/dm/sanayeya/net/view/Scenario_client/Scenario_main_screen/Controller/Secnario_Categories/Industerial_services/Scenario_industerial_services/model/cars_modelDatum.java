package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.model;//
//  cars_modelDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 27, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class cars_modelDatum{

	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public cars_modelDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		name = jsonObject.optString("name");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}