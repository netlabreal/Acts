package net.lab.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "Organizations")
public class Organizations {
    private Integer Id;
    private String Name;
    private String Prim;

    public Organizations(){}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    @Column(name = "id")
    public Integer getId() {
        return Id;
    }
    @Column(name = "name")
    public String getName() {
        return Name;
    }
    @Column(name = "prim")
    public String getPrim() {
        return Prim;
    }



    public void setId(Integer id) {
        Id = id;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setPrim(String prim) {
        Prim = prim;
    }
}
