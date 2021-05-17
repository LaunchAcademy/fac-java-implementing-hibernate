import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
//     configuration.addResource("Thought.hbm.xml");
    configuration.addAnnotatedClass(Thought.class);

    // Create the Hibernate service Registry
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
        applySettings(configuration.
            getProperties()).
        build();

     // instantiate the session factory
//     SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//     Session session = sessionFactory.getCurrentSession();
//
//     session.beginTransaction();
//
//     Thought thought = new Thought();
//     thought.setDescription("Hibernate is hard to set up, but easy to work with");
//     thought.setRating(10);
//
//     session.save(thought);
//
//     session.getTransaction().commit();
//     System.out.println(thought.getId());
//
//     session.close();

    // EM Factory pattern

    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("com.launchacademy.thoughts");
    EntityManager em = emf.createEntityManager();

    try {
      Thought thought = new Thought();
      thought.setDescription(
          "Before you criticize someone, you should walk a mile in their shoes. That way when you criticize them, you are a mile away from them and you have their shoes.");
      thought.setRating(8);
      em.getTransaction().begin();
      em.persist(thought);
      em.getTransaction().commit();
    } finally {
      em.close();
      emf.close();
    }

  }
}
