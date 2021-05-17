 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;
 import javax.persistence.UniqueConstraint;

 @Entity
 @Table(name="deep_thoughts")
 public class Thought {
   @Id
   @SequenceGenerator(name="deep_thought_generator", sequenceName="deep_thoughts_id_seq", allocationSize = 1)
   @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="deep_thought_generator")
   @Column(name="id", nullable=false, unique=true)
   private long id;

   @Column(name = "description")
   private String description;

   @Column(name = "rating")
   private int rating;

   public long getId() {
     return id;
   }

   public void setId(long id) {
     this.id = id;
   }

   public String getDescription() {
     return description;
   }

   public void setDescription(String description) {
     this.description = description;
   }

   public int getRating() {
     return rating;
   }

   public void setRating(int rating) {
     this.rating = rating;
   }
 }


//public class Thought {
//  private long id;
//  private String description;
//  private int rating;
//
//  public long getId() {
//    return id;
//  }
//
//  public void setId(long id) {
//    this.id = id;
//  }
//
//  public String getDescription() {
//    return description;
//  }
//
//  public void setDescription(String description) {
//    this.description = description;
//  }
//
//  public int getRating() {
//    return rating;
//  }
//
//  public void setRating(int rating) {
//    this.rating = rating;
//  }
//}
