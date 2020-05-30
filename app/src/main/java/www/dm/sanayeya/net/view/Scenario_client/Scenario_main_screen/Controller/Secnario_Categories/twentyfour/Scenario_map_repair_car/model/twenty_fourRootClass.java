package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model;//
//  twenty_fourRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 29, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class twenty_fourRootClass{

	@SerializedName("data")
	private twenty_fourDatum[] data;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(twenty_fourDatum[] data){
		this.data = data;
	}
	public twenty_fourDatum[] getData(){
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
	public twenty_fourRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		JSONArray dataJsonArray = jsonObject.optJSONArray("data");
		if(dataJsonArray != null){
			ArrayList<twenty_fourDatum> dataArrayList = new ArrayList<>();
			for (int i = 0; i < dataJsonArray.length(); i++) {
				JSONObject dataObject = dataJsonArray.optJSONObject(i);
				dataArrayList.add(new twenty_fourDatum(dataObject));
			}
			data = (twenty_fourDatum[]) dataArrayList.toArray();
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
				for(twenty_fourDatum dataElement : data){
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