package net.lab.dao;

import net.lab.entity.Doma;

import java.util.List;

public interface DomaDao {
    public void Add(Doma doma);

    public Integer Del(Doma dom);

    public Doma Find(Doma dom);

    public List<Doma> GetAll(Integer strid);
}


