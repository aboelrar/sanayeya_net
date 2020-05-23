package www.dm.sanayeya.net.view.Scenario_client.welcome_screens.Scenario_signup.model;//
//  signupRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 22, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class signupRootClass{

	@SerializedName("data")
	private signupDatum data;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(signupDatum data){
		this.data = data;
	}
	public signupDatum getData(){
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
	public signupRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		data = new signupDatum(jsonObject.optJSONObject("data"));
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