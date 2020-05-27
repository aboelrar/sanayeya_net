package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model;//
//  industerial_service_detailsRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 27, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class industerial_service_detailsRootClass{

	@SerializedName("data")
	private industerial_service_detailsDatum data;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(industerial_service_detailsDatum data){
		this.data = data;
	}
	public industerial_service_detailsDatum getData(){
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
	public industerial_service_detailsRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		data = new industerial_service_detailsDatum(jsonObject.optJSONObject("data"));
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
			jsonObject.put("data", data.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}