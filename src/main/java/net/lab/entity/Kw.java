package net.lab.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "Kw")
public class Kw {
    private Integer id;
    private Integer Ndoma;
    private Integer Kw;
    private String Kkw;
    private String name;

    public Kw(){}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    @Column(name = "ndoma")
    public Integer getNdoma() {
        return Ndoma;
    }
    @Column(name = "kw")
    public Integer getKw() {
        return Kw;
    }
    @Column(name = "kkw")
    public String getKkw() {
        return Kkw;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNdoma(Integer ndoma) {
        Ndoma = ndoma;
    }
    public void setKw(Integer kw) {
        Kw = kw;
    }
    public void setKkw(String kkw) {
        Kkw = kkw;
    }
    public void setName(String name) {
        this.name = name;
    }
}
