package com.ts.productCatalog.entity;



import java.util.Date;

public class EventSearched {

    private String id;
    private String name;
    private String description;
    private String city;
    private String eventType;
    private String stage;
    private Date eventDate;
    private String address;


    public EventSearched(String id, String name, String description, String city, String eventType, String stage, Date eventDate, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.eventType = eventType;
        this.stage = stage;
        this.eventDate = eventDate;
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
