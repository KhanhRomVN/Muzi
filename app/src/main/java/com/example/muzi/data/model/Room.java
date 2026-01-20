package com.example.muzi.data.model;

public class Room {
    private String id;
    private String hotelId;
    private String roomNumber;
    private String type; // "SINGLE", "DOUBLE", "SUITE"
    private double price;

    public Room() {}

    public Room(String id, String hotelId, String roomNumber, String type, double price) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
