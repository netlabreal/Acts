package net.lab.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Doma")
public class Doma {
    private Integer id;
    private Integer Nstr;
    private Integer Dom;
    private String Kdom;
    private String name;

public Doma(){};

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    @Column(name = "nstr")
    public Integer getNstr() {
        return Nstr;
    }
    @Column(name = "dom")
    public Integer getDom() {
        return Dom;
    }
    @Column(name = "kdom")
    public String getKdom() {
        return Kdom;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }




    public void setId(Integer id) {
        this.id = id;
    }
    public void setNstr(Integer nstr) {
        Nstr = nstr;
    }
    public void setDom(Integer dom) {
        Dom = dom;
    }
    public void setKdom(String kdom) {
        Kdom = kdom;
    }
    public void setName(String name) {
        this.name = name;
    }
}


