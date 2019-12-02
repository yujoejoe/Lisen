package POJO;

public class Users extends Base{

  private static final long serialVersionUID = 1L;
  
  int id;
  String name;
  String pswd;
  String phone;
  String sex;
  String email;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  public String getPswd() {
    return pswd;
  }
  public void setPswd(String pswd) {
    this.pswd = pswd;
  }
  
  public String getPhone() {
    return phone;
  }
  public void setPhone(String pswd) {
    this.pswd = phone;
  }
  
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String toString() {
    return "Users [id=" + id + ", name=" + name + ", pswd=" + pswd
        + ", phone=" + phone + ", sex=" + sex + ", email=" + email + ", condition=" + condition
        + ", limit=" + limit + ", orderBy=" + orderBy + "]";
  }

}