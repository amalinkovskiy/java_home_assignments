package ua.stqa.pft.firtstassignment;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.pft.firstassignment.Point;

/**
 * Created by amalinkovskiy on 4/17/2017.
 */
public class PointTests {

    @Test //"Test 3,4,5 triangle"
    public void distPoint (){
        Point p1 = new Point(0,0);
        Point p2 = new Point(3,4);

        Assert.assertEquals(p1.distanceInside(p2), 5.0);

    }

    @Test //"Test zero and less than zero"
    public void distPointBig(){
        Point p1 = new Point(-5,-1);
        Point p2 = new Point(-5,1);
        Assert.assertEquals(p1.distanceInside(p2), 2.0);
    }

}
