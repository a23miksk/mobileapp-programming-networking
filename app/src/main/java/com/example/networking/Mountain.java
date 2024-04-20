package com.example.networking;

public class Mountain {
    private String name;
    private String location;
    private int height;
    private String imageLoc;

    public Mountain(String name, String location, int height, String imageLoc) {
        this.name = name;
        this.location = location;
        this.height = height;
        this.imageLoc = imageLoc;
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", height=" + height +
                ", imageLoc='" + imageLoc + '\'' +
                '}';
    }

    public Mountain(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }
}
