package net.lab.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "Streets")
public class Streets {
    private Integer Id;
    private String Name;

    public Streets(){};

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

    public void setId(Integer id) {
        Id = id;
    }
    public void setName(String name) {
        Name = name;
    }
}
