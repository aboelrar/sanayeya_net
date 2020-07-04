package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model;//
//  my_ordersRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on June 26, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class my_ordersRootClass{

	@SerializedName("data")
	private my_ordersDatum[] data;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(my_ordersDatum[] data){
		this.data = data;
	}
	public my_ordersDatum[] getData(){
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
	public my_ordersRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		JSONArray dataJsonArray = jsonObject.optJSONArray("data");
		if(dataJsonArray != null){
			ArrayList<my_ordersDatum> dataArrayList = new ArrayList<>();
			for (int i = 0; i < dataJsonArray.length(); i++) {
				JSONObject dataObject = dataJsonArray.optJSONObject(i);
				dataArrayList.add(new my_ordersDatum(dataObject));
			}
			data = (my_ordersDatum[]) dataArrayList.toArray();
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
				for(my_ordersDatum dataElement : data){
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