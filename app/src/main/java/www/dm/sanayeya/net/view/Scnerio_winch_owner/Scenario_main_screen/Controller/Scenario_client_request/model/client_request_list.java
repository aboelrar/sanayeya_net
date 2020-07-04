package www.dm.sanayeya.net.view.Scnerio_winch_owner.Scenario_main_screen.Controller.Scenario_client_request.model;

public class client_request_list {
    String id, request_id, from, to;
    double address_lat, address_lng, winch_lat, winch_lng;

    public client_request_list(String id, String request_id, String from, String to, double address_lat, double address_lng, double winch_lat, double winch_lng) {
        this.id = id;
        this.request_id = request_id;
        this.from = from;
        this.to = to;
        this.address_lat = address_lat;
        this.address_lng = address_lng;
        this.winch_lat = winch_lat;
        this.winch_lng = winch_lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAddress_lat() {
        return address_lat;
    }

    public void setAddress_lat(double address_lat) {
        this.address_lat = address_lat;
    }

    public double getAddress_lng() {
        return address_lng;
    }

    public void setAddress_lng(double address_lng) {
        this.address_lng = address_lng;
    }

    public double getWinch_lat() {
        return winch_lat;
    }

    public void setWinch_lat(double winch_lat) {
        this.winch_lat = winch_lat;
    }

    public double getWinch_lng() {
        return winch_lng;
    }

    public void setWinch_lng(double winch_lng) {
        this.winch_lng = winch_lng;
    }
}
