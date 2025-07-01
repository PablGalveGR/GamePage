package gamepage.page.Users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private  String name;
  private String paswd;
  

  public User(long id, String name, String paswd) {
    this.id = id;
    this.name = name;
    this.paswd = paswd;
  }
  public User(){
  }

  // standard constructors / setters / getters / toString
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getPaswd() {
    return this.paswd;
  }

  public void setPaswd(String paswd) {
    this.paswd = paswd;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", paswd='" + getPaswd() + "'" +
      "}";
  }

  

}
