package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.Industerial_services.Scenario_industerial_details.model;//
//  industerial_service_detailsDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on May 27, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class industerial_service_detailsDatum{

	@SerializedName("car_models")
	private String carModels;
	@SerializedName("desc")
	private String desc;
	@SerializedName("id")
	private int id;
	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;
	@SerializedName("phone")
	private String phone;
	@SerializedName("province")
	private String province;
	@SerializedName("rate")
	private int rate;
	@SerializedName("rate_users")
	private industerial_service_detailsRateUser[] rateUsers;
	@SerializedName("rates_count")
	private int ratesCount;
	@SerializedName("sms")
	private String sms;
	@SerializedName("whatsapp")
	private String whatsapp;
	@SerializedName("work_shop")
	private String workShop;

	public void setCarModels(String carModels){
		this.carModels = carModels;
	}
	public String getCarModels(){
		return this.carModels;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
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
	public void setProvince(String province){
		this.province = province;
	}
	public String getProvince(){
		return this.province;
	}
	public void setRate(int rate){
		this.rate = rate;
	}
	public int getRate(){
		return this.rate;
	}
	public void setRateUsers(industerial_service_detailsRateUser[] rateUsers){
		this.rateUsers = rateUsers;
	}
	public industerial_service_detailsRateUser[] getRateUsers(){
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
	public void setWorkShop(String workShop){
		this.workShop = workShop;
	}
	public String getWorkShop(){
		return this.workShop;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public industerial_service_detailsDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		carModels = jsonObject.optString("car_models");
		desc = jsonObject.optString("desc");
		image = jsonObject.optString("image");
		name = jsonObject.optString("name");
		phone = jsonObject.optString("phone");
		province = jsonObject.optString("province");
		sms = jsonObject.optString("sms");
		whatsapp = jsonObject.optString("whatsapp");
		workShop = jsonObject.optString("work_shop");
		id = jsonObject.optInt("id");
		rate = jsonObject.optInt("rate");
		ratesCount = jsonObject.optInt("rates_count");
		JSONArray rateUsersJsonArray = jsonObject.optJSONArray("rate_users");
		if(rateUsersJsonArray != null){
			ArrayList<industerial_service_detailsRateUser> rateUsersArrayList = new ArrayList<>();
			for (int i = 0; i < rateUsersJsonArray.length(); i++) {
				JSONObject rateUsersObject = rateUsersJsonArray.optJSONObject(i);
				rateUsersArrayList.add(new industerial_service_detailsRateUser(rateUsersObject));
			}
			rateUsers = (industerial_service_detailsRateUser[]) rateUsersArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("car_models", carModels);
			jsonObject.put("desc", desc);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			jsonObject.put("province", province);
			jsonObject.put("rate", rate);
			jsonObject.put("rates_count", ratesCount);
			jsonObject.put("sms", sms);
			jsonObject.put("whatsapp", whatsapp);
			jsonObject.put("work_shop", workShop);
			if(rateUsers != null && rateUsers.length > 0){
				JSONArray rateUsersJsonArray = new JSONArray();
				for(industerial_service_detailsRateUser rateUsersElement : rateUsers){
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