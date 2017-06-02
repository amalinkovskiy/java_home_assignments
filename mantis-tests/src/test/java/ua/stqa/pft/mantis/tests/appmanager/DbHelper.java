package ua.stqa.pft.mantis.tests.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.stqa.pft.mantis.tests.model.User;
import ua.stqa.pft.mantis.tests.model.Users;

import java.util.List;

/**
 * Created by amalinkovskiy on 5/23/2017.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;
    private final ApplicationManager app;

    public DbHelper(ApplicationManager app){

        // A SessionFactory is set up once for an application!
        this.app = app;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Users users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery( "from User" ).list();

        session.getTransaction().commit();
        session.close();

        return new Users(result);
    }

}
