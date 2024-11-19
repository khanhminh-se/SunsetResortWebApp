package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="email" , nullable = false, length = 255, unique = true)
    private String email;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="fullname" , nullable = false)
    private String fullname;
    @Column(name="address")
    private String address;
    @Column(name ="phoneNumber")
    private String phoneNumber;
    @Column(name= "role", nullable = false)
    private String role ="USER";
    @Column(name = "enabled" )
    private boolean isEnabled = true;
    @Column(name="salt")
    private String salt;
    public User(){}

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User(String email, String password, String fullname, String address, String phoneNumber, String role, boolean isEnabled) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.isEnabled = isEnabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
