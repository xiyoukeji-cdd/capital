package com.xiyoukeji.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@Entity
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @OneToOne
    private File photo;
    @Column(length = 50, unique = true)
    private String userName;
    private String password;
    @ManyToOne
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Role role;
    private String name;
    private String position;
    private String address;
    private String phone;
    private int isBand = 0;
    private int available = 1;
    @ManyToMany
    @JoinTable(name = "user_foundation", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "foundation_id")})
    private List<Foundation> list_foundation = new ArrayList<>();
    @OneToMany
    @JoinTable(name = "user_project", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private List<Project> list_project = new ArrayList<>();

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsBand() {
        return isBand;
    }

    public void setIsBand(int isBand) {
        this.isBand = isBand;
    }

    public List<Foundation> getList_foundation() {
        return list_foundation;
    }

    public void setList_foundation(List<Foundation> list_foundation) {
        this.list_foundation = list_foundation;
    }


    public List<Project> getList_project() {
        return list_project;
    }

    public void setList_project(List<Project> list_project) {
        this.list_project = list_project;
    }
}
