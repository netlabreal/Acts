package net.lab.dao;

import net.lab.entity.Kw;

import java.util.List;

public interface KwDao {
    public void AddKw(Kw kw);
    public Integer Del(Kw kw);
    public Kw Find(Kw kw);
    public List<Kw> GetAll(Integer iddoma);
}
