package model;

import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.*;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

import java.math.BigDecimal;
import java.util.*;

import static dbManager.DynamoDBManager.dynamoDB;



public class Order {
    public static final String TABLE_NAME = "Orders";
    public static final Table TABLE = dynamoDB.getTable(TABLE_NAME);
    private String orderId;
	private String userId;
	private String clientId;
	private String location;
	private Location clientLocation;
	private long time;
	private String license;
	//private boolean login;
	
public Order(String orderId) {
	this.orderId = orderId;
    GetItemSpec getItem = new GetItemSpec().withPrimaryKey("orderId", orderId);
    Item item = TABLE.getItem(getItem);
    while (item != null) {
		this.orderId = orderId;
        this.userId = item.getString("userId");
        this.clientId = item.getString("clientId");
        this.location = item.getString("location");
        this.clientLocation = item.getString("clientLocation");
        this.time = item.getLong("time");
        this.license = item.getString("license");
		}
	    if (item.getMap("location") != null && !item.getMap("location").isEmpty()) {
                System.out.println("load location");
                this.location = new Location(item.getMap("location"), true);
            }
	
}

public Order(String orderId, String userId, String ClientId, String ){
	
}
	}

public static class Location {
        double lat;
        double lng;
        String address;
        int postal;
        String state;
        String streetName;
        int streetNumber;

        public Location(Map<String, Object> info, boolean test) {
            if (info != null) {
                System.out.println(info);
                this.lat = ((BigDecimal)info.get("lat")).doubleValue();
                this.lng = ((BigDecimal)info.get("lng")).doubleValue();
                this.address = (String) info.get("address");
                this.postal = ((BigDecimal)info.get("postal")).intValue();
                this.state = (String) info.get("state");
                this.streetName = (String) info.get("streetName");
                this.streetNumber = ((BigDecimal)info.get("streetNumber")).intValue();
                System.out.println(lat + lng + address + postal + state);
            }
        }

        public Location(Map<String, Object> info) {
            if (info != null) {
                System.out.println(info);
                this.lat = (Double)info.get("lat");
                this.lng = (Double)info.get("lng");
                this.address = (String)info.get("address");
                this.postal = (Integer)info.get("postal");
                this.state = (String)info.get("state");
                this.streetName = (String)info.get("streetName");
                this.streetNumber = (Integer)info.get("streetNumber");
                System.out.println(lat + lng + address + postal + state);
            }
        }

        public Location(double lat, double lng, String address, int postal, String state, String streetName, Integer streetNumber) {
            this.lat =  lat;
            this.lng = lng;
            this.address = address;
            this.postal = postal;
            this.state = state;
            this.streetName = streetName;
            this.streetNumber = streetNumber;
        }

        public Map<String, Object> getInfo() {
            Map<String, Object> info = new HashMap<String, Object>();
            info.put("lat", this.lat);
            info.put("lng", this.lng);
            info.put("postal", this.postal);
            info.put("address", this.address);
            info.put("state", this.state);
            info.put("streetName", this.streetName);
            info.put("streetNumber", this.streetNumber);
            return info;
        }