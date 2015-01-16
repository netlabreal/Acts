package net.lab.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


@Entity
@Table(name = "Ls")
public class Ls {
    private Integer Id;
    private Integer Nomer;
    private String Name;
    private Integer Kwid;

    public Ls(){};

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    @Column(name = "id")
    public Integer getId() {
        return Id;
    }
    @Column(name = "nomer")
    public Integer getNomer() {
        return Nomer;
    }
    @Column(name = "name")
    public String getName() {
        return Name;
    }
    @Column(name ="kwid")
    public Integer getKwid() {
        return Kwid;
    }



    public void setId(Integer id) {
        Id = id;
    }
    public void setNomer(Integer nomer) {
        Nomer = nomer;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setKwid(Integer kwid) {
        Kwid = kwid;
    }
}
