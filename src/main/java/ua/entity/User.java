package ua.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 1/5/2017.
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",columnDefinition="INT(8)")
    private Integer id;
    @Column(name="name",length = 25)
    private String name;
    @Column(name="age",columnDefinition="INT(8)")
    private Integer age;
    @Column(name="isAdmin")
    private Boolean isAdmin=false;
    @Column(name = "createdDate",columnDefinition="TIMESTAMP")
    @Type(type="timestamp")
    private Date createdDate;
    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
