package ua.stqa.pft.sandbox;

/**
 * Created by amalinkovskiy on 4/9/2017.
 */
public class Rectangle {
    public double a;
    public double b;

    public Rectangle(double a , double b){
        this.a = a;
        this.b = b;
    }

    public double area (){
        return this.a * this.b;
    }
}
