package ua.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by amalinkovskiy on 4/17/2017.
 */
public class SquareTests {

    @Test
    public void testArea () {
        Square s = new Square(5);
        Assert.assertEquals(s.area(),20.0);


    }
}
