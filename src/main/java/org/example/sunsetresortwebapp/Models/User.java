package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;
import org.example.sunsetresortwebapp.Enum.UserRole;
import org.example.sunsetresortwebapp.Enum.UserStatus;


@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="email" , nullable = false, length = 255, unique = true)
    private String email;
    @Column(name="password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name ="role")
    private UserRole userRole = UserRole.USER;
    private String salt;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private UserStatus status = UserStatus.ACTIVATED;
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private UserProfile profile;
    public User(){}

    public User(String email, String password, UserRole userRole, String salt, UserStatus status) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.salt = salt;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
