package net.lab.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Data")
public class Data {
    private Integer id;
    private Integer nls;
    private Integer prop;
    private Float plosh;
    private Date month;
    private Float vxSaldo;
    private Float nach;
    private Float act;
    private Float per;
    private Float vist;
    private Float opl;
    private Float isxSaldo;
    private Integer orgid;

    public Data(){}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    @Column(name = "nls")
    @Index(name = "ind_nls")
    public Integer getNls() {
        return nls;
    }
    @Column(name = "prop")
    public Integer getProp() {
        return prop;
    }
    @Column(name = "plosh")
    public Float getPlosh() {
        return plosh;
    }
    @Column(name = "month")
    public Date getMonth() {
        return month;
    }
    @Column(name = "vxsaldo")
    public Float getVxSaldo() {
        return vxSaldo;
    }
    @Column(name = "nach")
    public Float getNach() {
        return nach;
    }
    @Column(name = "act")
    public Float getAct() {
        return act;
    }
    @Column(name = "per")
    public Float getPer() {
        return per;
    }
    @Column(name = "vist")
    public Float getVist() {
        return vist;
    }
    @Column(name = "opl")
    public Float getOpl() {
        return opl;
    }
    @Column(name = "isxsaldo")
    public Float getIsxSaldo() {
        return isxSaldo;
    }
    @Column(name = "orgid")
    public Integer getOrgid() {
        return orgid;
    }

    public void setNls(Integer nls) {
        this.nls = nls;
    }
    public void setProp(Integer prop) {
        this.prop = prop;
    }
    public void setPlosh(Float plosh) {
        this.plosh = plosh;
    }
    public void setMonth(Date month) {
        this.month = month;
    }
    public void setVxSaldo(Float vxSaldo) {
        this.vxSaldo = vxSaldo;
    }
    public void setNach(Float nach) {
        this.nach = nach;
    }
    public void setAct(Float act) {
        this.act = act;
    }
    public void setPer(Float per) {
        this.per = per;
    }
    public void setVist(Float vist) {
        this.vist = vist;
    }
    public void setOpl(Float opl) {
        this.opl = opl;
    }
    public void setIsxSaldo(Float isxSaldo) {
        this.isxSaldo = isxSaldo;
    }
    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }
    public void setId(Integer id) {
        this.id = id;
    }

}
