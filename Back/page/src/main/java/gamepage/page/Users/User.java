package gamepage.page.Users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private  String name;
  private String passwd;
  

  public User(long id, String name, String passwd) {
    this.id = id;
    this.name = name;
    this.passwd = passwd;
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

  public String getPasswd() {
    return this.passwd;
  }

  public void setPasswd(String paswd) {
    this.passwd = paswd;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", paswd='" + getPasswd() + "'" +
      "}";
  }

  

}
