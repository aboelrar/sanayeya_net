package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.model;//
//  winch_detailsDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on June 8, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class winch_detailsDatum{

	@SerializedName("address")
	private String address;
	@SerializedName("arrived_at")
	private String arrivedAt;
	@SerializedName("cost")
	private int cost;
	@SerializedName("estiamte_time")
	private String estiamteTime;
	@SerializedName("id")
	private int id;
	@SerializedName("status")
	private int status;

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setArrivedAt(String arrivedAt){
		this.arrivedAt = arrivedAt;
	}
	public String getArrivedAt(){
		return this.arrivedAt;
	}
	public void setCost(int cost){
		this.cost = cost;
	}
	public int getCost(){
		return this.cost;
	}
	public void setEstiamteTime(String estiamteTime){
		this.estiamteTime = estiamteTime;
	}
	public String getEstiamteTime(){
		return this.estiamteTime;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
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
	public winch_detailsDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = jsonObject.optString("address");
		arrivedAt = jsonObject.optString("arrived_at");
		estiamteTime = jsonObject.optString("estiamte_time");
		cost = jsonObject.optInt("cost");
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
			jsonObject.put("address", address);
			jsonObject.put("arrived_at", arrivedAt);
			jsonObject.put("cost", cost);
			jsonObject.put("estiamte_time", estiamteTime);
			jsonObject.put("id", id);
			jsonObject.put("status", status);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}