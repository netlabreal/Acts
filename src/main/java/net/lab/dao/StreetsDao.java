package net.lab.dao;

import net.lab.entity.Streets;
import java.util.List;


public interface StreetsDao {
    public void Add(Streets street);
    public Integer Del(String street);
    public Streets Find(String street);
    public Streets Find(Integer strid);
    public List<Streets> GetAll();
}
