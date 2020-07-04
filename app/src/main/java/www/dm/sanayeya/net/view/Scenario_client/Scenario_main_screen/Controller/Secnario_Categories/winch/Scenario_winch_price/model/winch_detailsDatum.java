package www.dm.sanayeya.net.view.Scenario_client.Scenario_main_screen.Controller.Secnario_Categories.winch.Scenario_winch_price.model;//
//  winch_detailsDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on June 8, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class winch_detailsDatum{
	@SerializedName("arrived_at")
	private String arrivedAt;
	@SerializedName("cost")
	private String cost;
	@SerializedName("destination_address")
	private String destinationAddress;
	@SerializedName("destination_lat")
	private double destinationLat;
	@SerializedName("destination_lng")
	private double destinationLng;
	@SerializedName("estiamte_time")
	private String estiamteTime;
	@SerializedName("id")
	private int id;
	@SerializedName("location_address")
	private String locationAddress;
	@SerializedName("location_lat")
	private double locationLat;
	@SerializedName("location_lng")
	private double locationLng;
	@SerializedName("status")
	private int status;
	@SerializedName("user_id")
	private int userId;
	@SerializedName("winch_id")
	private int winchId;
	@SerializedName("winch_lat")
	private double winchLat;
	@SerializedName("winch_lng")
	private double winchLng;

	public void setArrivedAt(String arrivedAt){
		this.arrivedAt = arrivedAt;
	}
	public String getArrivedAt(){
		return this.arrivedAt;
	}
	public void setCost(String cost){
		this.cost = cost;
	}
	public String getCost(){
		return this.cost;
	}
	public void setDestinationAddress(String destinationAddress){
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationAddress(){
		return this.destinationAddress;
	}
	public void setDestinationLat(double destinationLat){
		this.destinationLat = destinationLat;
	}
	public double getDestinationLat(){
		return this.destinationLat;
	}
	public void setDestinationLng(double destinationLng){
		this.destinationLng = destinationLng;
	}
	public double getDestinationLng(){
		return this.destinationLng;
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
	public void setLocationAddress(String locationAddress){
		this.locationAddress = locationAddress;
	}
	public String getLocationAddress(){
		return this.locationAddress;
	}
	public void setLocationLat(double locationLat){
		this.locationLat = locationLat;
	}
	public double getLocationLat(){
		return this.locationLat;
	}
	public void setLocationLng(double locationLng){
		this.locationLng = locationLng;
	}
	public double getLocationLng(){
		return this.locationLng;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public int getUserId(){
		return this.userId;
	}
	public void setWinchId(int winchId){
		this.winchId = winchId;
	}
	public int getWinchId(){
		return this.winchId;
	}
	public void setWinchLat(double winchLat){
		this.winchLat = winchLat;
	}
	public double getWinchLat(){
		return this.winchLat;
	}
	public void setWinchLng(double winchLng){
		this.winchLng = winchLng;
	}
	public double getWinchLng(){
		return this.winchLng;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public winch_detailsDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		arrivedAt = jsonObject.optString("arrived_at");
		destinationAddress = jsonObject.optString("destination_address");
		destinationLat = jsonObject.optDouble("destination_lat");
		destinationLng = jsonObject.optDouble("destination_lng");
		estiamteTime = jsonObject.optString("estiamte_time");
		locationAddress = jsonObject.optString("location_address");
		locationLat = jsonObject.optDouble("location_lat");
		locationLng = jsonObject.optDouble("location_lng");
		winchLat = jsonObject.optDouble("winch_lat");
		winchLng = jsonObject.optDouble("winch_lng");
		cost = jsonObject.optString("cost");
		id = jsonObject.optInt("id");
		status = jsonObject.optInt("status");
		userId = jsonObject.optInt("user_id");
		winchId = jsonObject.optInt("winch_id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("arrived_at", arrivedAt);
			jsonObject.put("cost", cost);
			jsonObject.put("destination_address", destinationAddress);
			jsonObject.put("destination_lat", destinationLat);
			jsonObject.put("destination_lng", destinationLng);
			jsonObject.put("estiamte_time", estiamteTime);
			jsonObject.put("id", id);
			jsonObject.put("location_address", locationAddress);
			jsonObject.put("location_lat", locationLat);
			jsonObject.put("location_lng", locationLng);
			jsonObject.put("status", status);
			jsonObject.put("user_id", userId);
			jsonObject.put("winch_id", winchId);
			jsonObject.put("winch_lat", winchLat);
			jsonObject.put("winch_lng", winchLng);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}