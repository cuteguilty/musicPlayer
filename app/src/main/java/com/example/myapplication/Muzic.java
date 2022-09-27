package com.example.myapplication;

public class Muzic {
    private String isop; // название
    private String name;  // столица
    private int albom; // ресурс флага

    public Muzic(String isop, String name, int albom){

        this.isop=isop;
        this.name=name;
        this.albom=albom;
    }

    public String getIsop() {
        return this.isop;
    }

    public void setIsop(String isop) {
        this.isop = isop;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlbom() {
        return this.albom;
    }

    public void setAlbom(int albom) {
        this.albom = albom;
    }
}
