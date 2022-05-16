package com.codename1.uikit.pheonixui.model;

public class Product {
    String id;
    String name;
    String price;
    String quant;
    String designation;
    String cat;
    String img;
    int quantToBuy;

    public int getQuantToBuy() {
        return quantToBuy;
    }

    public void setQuantToBuy(int quantToBuy) {
        this.quantToBuy = quantToBuy;
    }

    public Product() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
