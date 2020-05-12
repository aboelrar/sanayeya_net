package www.dm.sanayeya.net.view.Scenario_main_screen.Controller.Secnario_Categories.twentyfour.Scenario_map_repair_car.model;

public class workshop_list {
    String id,name,address,review;
    int num;

    public workshop_list(String id, String name, String address, int num, String review) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.num = num;
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
