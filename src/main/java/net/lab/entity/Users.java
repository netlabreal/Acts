package net.lab.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;

@Entity
@Table(name="Users")
public class Users {
    private Integer id;
    private String login;
    private String username;
    private String password;
    private Integer adm;

    public Users(){}

@Id
@GeneratedValue(generator = "increment")
@GenericGenerator(name="increment",strategy = "increment")
@Column(name = "id")
    public Integer getId() {
        return id;
    }
@Column(name = "login")
    public String getLogin() {
        return login;
    }
@Column(name = "username")
    public String getUsername() {
        return username;
    }
@Column(name = "adm")
    public Integer getAdm() {
        return adm;
    }
@Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setAdm(Integer adm) {
        this.adm = adm;
    }
}
