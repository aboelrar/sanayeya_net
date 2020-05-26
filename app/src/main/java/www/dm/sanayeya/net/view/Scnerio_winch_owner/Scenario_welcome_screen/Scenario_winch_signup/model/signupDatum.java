package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_welcome_screen.Scenario_winch_signup.model;//
//  signupDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 22, 2020

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


public class signupDatum{

	@SerializedName("email")
	private String email;
	@SerializedName("id")
	private int id;
	@SerializedName("lang")
	private String lang;
	@SerializedName("lat")
	private String lat;
	@SerializedName("lng")
	private String lng;
	@SerializedName("phone")
	private String phone;
	@SerializedName("status")
	private int status;
	@SerializedName("token")
	private String token;
	@SerializedName("type")
	private String type;
	@SerializedName("username")
	private String username;

	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setLang(String lang){
		this.lang = lang;
	}
	public String getLang(){
		return this.lang;
	}
	public void setLat(String lat){
		this.lat = lat;
	}
	public String getLat(){
		return this.lat;
	}
	public void setLng(String lng){
		this.lng = lng;
	}
	public String getLng(){
		return this.lng;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setToken(String token){
		this.token = token;
	}
	public String getToken(){
		return this.token;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public signupDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		email = jsonObject.optString("email");
		lang = jsonObject.optString("lang");
		lat = jsonObject.optString("lat");
		lng = jsonObject.optString("lng");
		phone = jsonObject.optString("phone");
		token = jsonObject.optString("token");
		type = jsonObject.optString("type");
		username = jsonObject.optString("username");
		id = jsonObject.optInt("id");
		status = jsonObject.optInt("status");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("email", email);
			jsonObject.put("id", id);
			jsonObject.put("lang", lang);
			jsonObject.put("lat", lat);
			jsonObject.put("lng", lng);
			jsonObject.put("phone", phone);
			jsonObject.put("status", status);
			jsonObject.put("token", token);
			jsonObject.put("type", type);
			jsonObject.put("username", username);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}