package com.example.ltnull.hur.PojoClass;

public class EventPojo {

   private String name ;
   private String places ;
   private String duration ;
   private String  number;
   private String auth ;

    public EventPojo(String name, String places, String duration, String number, String auth) {
        this.name = name;
        this.places = places;
        this.duration = duration;
        this.number = number;
        this.auth = auth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
