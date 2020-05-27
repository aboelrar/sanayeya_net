package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_services.model;//
//  cars_modelRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 27, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class cars_modelRootClass{

	@SerializedName("data")
	private cars_modelDatum[] data;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(cars_modelDatum[] data){
		this.data = data;
	}
	public cars_modelDatum[] getData(){
		return this.data;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public cars_modelRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		JSONArray dataJsonArray = jsonObject.optJSONArray("data");
		if(dataJsonArray != null){
			ArrayList<cars_modelDatum> dataArrayList = new ArrayList<>();
			for (int i = 0; i < dataJsonArray.length(); i++) {
				JSONObject dataObject = dataJsonArray.optJSONObject(i);
				dataArrayList.add(new cars_modelDatum(dataObject));
			}
			data = (cars_modelDatum[]) dataArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			if(data != null && data.length > 0){
				JSONArray dataJsonArray = new JSONArray();
				for(cars_modelDatum dataElement : data){
					dataJsonArray.put(dataElement.toJsonObject());
				}
				jsonObject.put("data", dataJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}