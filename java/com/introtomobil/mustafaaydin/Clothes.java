package com.introtomobil.mustafaaydin;

public class Clothes implements java.io.Serializable {
    private int id;
    private String name;
    private String type;
    private String color;
    private String pattern;
    private Float price;
    private String date;

    public Clothes(int id, String name, String type, String color, String pattern, Float price, String date)  {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.pattern = pattern;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return      "Name       " + name +
                "       Type    " + type +
                "\n\nColor      " + color+
                "       Pattern " + pattern +
                "\n\nPrice      " + price +
                "       Date    " + date ;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
