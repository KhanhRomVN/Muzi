package com.example.muzi.data.model;

public class Room {
    private String id;
    private String hotelId;
    private String roomNumber;
    private String type; // "SINGLE", "DOUBLE", "SUITE"
    private double price;
    private String imagePath;
    private boolean isSelected;

    public Room() {}

    public Room(String id, String hotelId, String roomNumber, String type, double price, String imagePath) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.imagePath = imagePath;
        this.isSelected = false;
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
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
