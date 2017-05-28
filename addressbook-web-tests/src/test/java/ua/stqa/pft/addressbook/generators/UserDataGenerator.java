package ua.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ua.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amalinkovskiy on 5/19/2017.
 */
public class UserDataGenerator {

    @Parameter(names = "-c", description = "User count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {

        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);

        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }


        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("xml")) {
            saveAsXml(users,new File(file));
        } else if (format.equals("json")) {
            saveAsJson(users,new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<UserData> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<UserData> users, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(UserData.class);
        String xml = xstream.toXML(users);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private List<UserData> generateUsers(int count) {
        List<UserData> users = new ArrayList<UserData>();

        for (int i=0; i<count;i++){
            users.add(new UserData().withFirstname(String.format("First Name %s",i))
                    .withMiddlename(String.format("Middle %s",i)).withLastname(String.format("Last Name %s",i))
                    .withNickname(String.format("amalinkovskiy %s",i)).withTitle(String.format("title",i)).withAddress(String.format("add",i))/*.withGroup("test1")*/);
        }

        return users;
    }


}
