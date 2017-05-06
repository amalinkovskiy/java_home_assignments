package ua.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by amalinkovskiy on 5/6/2017.
 */
public class Collections {
    public static void main(String args[]){
        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");


        for (String l : languages){
            System.out.println("I want to learn " + l);
        }
    }
}
