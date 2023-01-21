package model;

public class Apple extends Food{
    public String color;

    public Apple(int amount, double price, String color){
        this.amount = amount;
        this.price = price;
        this.color = color;
        this.isVegetarian = true;
    }

}
