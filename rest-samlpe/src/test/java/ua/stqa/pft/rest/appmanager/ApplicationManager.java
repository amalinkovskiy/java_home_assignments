package ua.stqa.pft.rest.appmanager;

/**
 * Created by amalinkovskiy on 4/20/2017.
 */
public class ApplicationManager {



    private RestHelper restHelper;

    public RestHelper rest(){
//        if (rest == null) {
//            rest = new RestHelper(this);
//        }
        return new RestHelper(this);
    }




}
