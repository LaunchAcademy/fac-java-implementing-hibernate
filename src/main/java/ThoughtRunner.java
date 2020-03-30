import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ThoughtRunner {

  public static void main(String[] args) {
    // load the hibernate configure file and our mapping
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    configuration.addAnnotatedClass(Thought.class);

    // Create the Hibernate service Registry
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
        applySettings(configuration.
            getProperties()).
        build();

    // instantiate the session factory
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    Session session = sessionFactory.getCurrentSession();

    session.beginTransaction();

    Thought thought = new Thought();
    thought.setDescription("Hibernate is hard to set up, but easy to work with");
    thought.setRating(10);

    session.save(thought);

    session.getTransaction().commit();
    System.out.println(thought.getId());

    session.close();
  }
}
