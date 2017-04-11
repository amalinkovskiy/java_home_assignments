package ua.stqa.pft.firstassignment;

/**
 * Created by amalinkovskiy on 4/9/2017.
 */
public class MySecondProgram {
    public static void main(String[] args){

        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);
        System.out.println(p1.distanceInside(p2));

    }
/*
    public static double distance(Point p1, Point p2){
        return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
    }
*/
}
