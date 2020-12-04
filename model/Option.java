package model;

import java.io.Serializable;

public class Option implements Serializable {

    private String name;
    private float price;

    // --------> Constructors <--------//
    protected Option() {
        name = "";
        price = 0;
    };

    protected Option(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // --------> Getter(s) <--------//
    protected String g_Name() {
        return name;
    }

    protected float g_Price() {
        return price;
    }

    // --------> Setter(s) <--------//
    protected void s_Name(String name) {
        this.name = name;
    }

    protected void setPrice(Float price) {
        this.price = price;
    }

    // --> Option Object Output
    protected void print() {
        System.out.println(toString());
    }

    // --> toString Converter
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Option Name: ").append(name).append(", Price: $").append(price);
        String str = stringBuffer.toString();
        return str;
    }

}
