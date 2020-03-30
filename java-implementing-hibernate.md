# Implementing Hibernate

---

## Hibernate is an Object Relational Mapper (ORM)

- We're bridging the gap between database tables and Java objects
- We must *map* that bridge: we instruct Java how it connects to the database
- We can use an XML-based mapping or an Annotation-based mapping
- We recommend the Annotation-based mapping

---

## In This Session we'll

- Configure a Java application for Hibernate
- Show how we would implement an XML-based mapping
- Implement an Annotation-based mapping
- Persist a record

---

## Let's First Set Hibernate Up

1. Install Dependencies
2. Create the Database and Schema
3. Configure Your Database Connection

---

## Dependencies

- We need the ability to connect to our Postgresql database - we do so via the JDBC driver
- We need the Hibernate **Engine** known as core - this is what gives us the ability to map Java classes to database tables
---

## Install Dependencies - Hibernate Core

```xml
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>5.4.2.Final</version>
</dependency>
```

---

## Install Dependencies - Postgres JDBC

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.2.5</version>
</dependency>
```

---

## Create the Database

```no-highlight
createdb thoughts
```

---

## Create the Schema

```sql
DROP TABLE IF EXISTS deep_thoughts;
CREATE TABLE deep_thoughts (
  id SERIAL PRIMARY KEY,
  description TEXT,
  rating INTEGER
);

CREATE SEQUENCE deep_thoughts_id_seq;
```

```no-highlight
psql thoughts < src/main/resources/schema.sq
```

---

## Configure Your Database Connection

- Copy and paste the XML boilerplate
- **Do not try to memorize this!**
- Change the database name in the connection
- Change the user name and password, if needed

---

## Create Your Mapping File

```xml
<?xml version = "1.0" encoding = "utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
  "-//Hibernate/Hibernate Mapping DTD//EN"
  "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
  <class name="Thought" table="deep_thoughts">
    <meta attribute="class-description">
      Deep Thoughts recorded in a DB forever
    </meta>
    <id name="id" type="long" column="id">
      <generator class="sequence">
        <param name="optimizer">none</param>
        <param name="increment_size">1</param>
        <param name="sequence_name">deep_thoughts_id_seq</param>
      </generator>
    </id>
    <property name ="description" column="description" type = "string" />
    <property name ="rating" column="rating" type = "int" />
  </class>
</hibernate-mapping>
```

---

## Create Your Java Bean

```java
public class Thought {
  private long id;
  private String description;
  private int rating;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  //...other setters and getters
}
```

---

## Wait - What's a Bean?

- A Java Bean is a special kind of POJO (Java class)
- It must have a zero argument constructor for easy creation
- It must have getters and setters defined for all of its fields
- Officially it must also implement the `Serializable` interface (don't worry about that for now)

---

## Say Hello to a LOT More Boilerplate

1. Load up our Configuration
2. Set Up Your Session

---

## Load Up Your Configuration and mapping

```java
// load the hibernate configure file and our mapping
Configuration configuration = new Configuration();
configuration.configure("hibernate.cfg.xml");
configuration.addResource("Thought.hbm.xml");
```

---

## Set Up Your Session

```java
// Create the Hibernate service Registry
ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
  applySettings(configuration.
      getProperties()).
  build();

// instantiate the session factory
SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
Session session = sessionFactory.getCurrentSession();
```

---

## PHEW - Start Querying

```java
session.beginTransaction();

Thought thought = new Thought();
thought.setDescription("Hibernate is hard to set up, but easy to work with");
thought.setRating(10);

session.save(thought);

session.getTransaction().commit();
System.out.println(thought.getId());

session.close();
```

---

## Ok - Let's Change to an Annotation-based Mapping

```java
@Entity
@Table(name="deep_thoughts", uniqueConstraints = {@UniqueConstraint(columnNames="id")})
public class Thought {
  @Id
  @SequenceGenerator(name="deep_thought_generator", sequenceName="deep_thoughts_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="deep_thought_generator")
  @Column(name="id", nullable=false, unique=true)
  private long id;

  @Column
  private String description;

  @Column
  private int rating

  //...
}
```

---

## Make Some Adjustments in the Runner

We don't need the mapping any more!

Swap:

```java
configuration.addResource("Thought.hbm.xml");
```

With:

```java
configuration.addAnnotatedClass(Thought.class);
```

---

## Run the Same Code to Start Querying

- Annotations are better!
- The Bridge and the logic co-exist together
- The JPA has rich, annotation-based features sets we can take advantage of
