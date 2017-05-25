package ua.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.Groups;
import ua.stqa.pft.addressbook.model.UserData;
import ua.stqa.pft.addressbook.model.Users;

import java.util.List;

/**
 * Created by amalinkovskiy on 5/23/2017.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(){

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        for (GroupData group : result) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();

        return new Groups(result);
    }

    public Users users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where deprecated = '0000-00-00'" ).list();
        for (UserData user : result) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();

        return new Users(result);
    }

}