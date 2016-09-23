package com.fruitbay.model;

public class FruitsClass {

    private String name;
    private int stock;
    private float  price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int  getStock(){return stock;}

    public void setStock(String stock){this.stock = Integer.parseInt(stock);}

    public float  getPrice(){return price;}

    public void setPrice(String price){this.price = Float.parseFloat(price);}

}
