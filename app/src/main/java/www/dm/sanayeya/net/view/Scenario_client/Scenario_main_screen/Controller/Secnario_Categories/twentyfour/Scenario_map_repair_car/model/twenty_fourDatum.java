package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model;//
//  twenty_fourDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 29, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class twenty_fourDatum{

	@SerializedName("desc")
	private Object desc;
	@SerializedName("id")
	private int id;
	@SerializedName("image")
	private String image;
	@SerializedName("lat")
	private String lat;
	@SerializedName("lng")
	private String lng;
	@SerializedName("name")
	private String name;
	@SerializedName("phone")
	private String phone;
	@SerializedName("rate")
	private int rate;
	@SerializedName("rate_users")
	private twenty_fourRateUser[] rateUsers;
	@SerializedName("rates_count")
	private int ratesCount;
	@SerializedName("sms")
	private String sms;
	@SerializedName("whatsapp")
	private String whatsapp;

	public void setDesc(Object desc){
		this.desc = desc;
	}
	public Object getDesc(){
		return this.desc;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
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
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setRate(int rate){
		this.rate = rate;
	}
	public int getRate(){
		return this.rate;
	}
	public void setRateUsers(twenty_fourRateUser[] rateUsers){
		this.rateUsers = rateUsers;
	}
	public twenty_fourRateUser[] getRateUsers(){
		return this.rateUsers;
	}
	public void setRatesCount(int ratesCount){
		this.ratesCount = ratesCount;
	}
	public int getRatesCount(){
		return this.ratesCount;
	}
	public void setSms(String sms){
		this.sms = sms;
	}
	public String getSms(){
		return this.sms;
	}
	public void setWhatsapp(String whatsapp){
		this.whatsapp = whatsapp;
	}
	public String getWhatsapp(){
		return this.whatsapp;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public twenty_fourDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		image = jsonObject.optString("image");
		lat = jsonObject.optString("lat");
		lng = jsonObject.optString("lng");
		name = jsonObject.optString("name");
		phone = jsonObject.optString("phone");
		sms = jsonObject.optString("sms");
		whatsapp = jsonObject.optString("whatsapp");
		desc = jsonObject.optString("desc");
		id = jsonObject.optInt("id");
		rate = jsonObject.optInt("rate");
		ratesCount = jsonObject.optInt("rates_count");
		JSONArray rateUsersJsonArray = jsonObject.optJSONArray("rate_users");
		if(rateUsersJsonArray != null){
			ArrayList<twenty_fourRateUser> rateUsersArrayList = new ArrayList<>();
			for (int i = 0; i < rateUsersJsonArray.length(); i++) {
				JSONObject rateUsersObject = rateUsersJsonArray.optJSONObject(i);
				rateUsersArrayList.add(new twenty_fourRateUser(rateUsersObject));
			}
			rateUsers = (twenty_fourRateUser[]) rateUsersArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("desc", desc);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("lat", lat);
			jsonObject.put("lng", lng);
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			jsonObject.put("rate", rate);
			jsonObject.put("rates_count", ratesCount);
			jsonObject.put("sms", sms);
			jsonObject.put("whatsapp", whatsapp);
			if(rateUsers != null && rateUsers.length > 0){
				JSONArray rateUsersJsonArray = new JSONArray();
				for(twenty_fourRateUser rateUsersElement : rateUsers){
					rateUsersJsonArray.put(rateUsersElement.toJsonObject());
				}
				jsonObject.put("rate_users", rateUsersJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}