package ua.stqa.pft.mantis.tests.model;

/**
 * Created by amalinkovskiy on 6/1/2017.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text = text;
    }
}
