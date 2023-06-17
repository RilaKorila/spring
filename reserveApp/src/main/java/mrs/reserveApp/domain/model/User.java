package mrs.reserveApp.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="usr")
public class User implements Serializable {
    @Id
    private String userId;

    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

//    public User(){
//        this(0, "", "", "", RoleName.USER);
//    }
//
//    public User(String userId, String firstName, String lastName, String password, RoleName roleName){
//        this.userId = userId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.password = password;
//        this.roleName = roleName;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
