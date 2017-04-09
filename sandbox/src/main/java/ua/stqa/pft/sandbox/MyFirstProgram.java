package ua.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("Hello");
        hello("Hi");
        hello("Hola");

        double len = 5.0;
        System.out.println("Площадь квадрата со стороной " + len + " = " + area(len));

        double a = 4.0;
        double b = 6.0;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a,b));
    }

    public static void hello(String greeting) {
        System.out.println(greeting + ", Hero!");
    }

    public static double area (double l){
        return l * l;
    }

    public static double area (double a, double b){
        return a * b;
    }

}