package ua.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("Hello");
        hello("Hi");
        hello("Hola");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4.0,6.0);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(String greeting) {
        System.out.println(greeting + ", Hero!");
    }





}