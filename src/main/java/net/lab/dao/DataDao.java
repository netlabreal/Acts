package net.lab.dao;

import net.lab.entity.Data;

import java.util.HashMap;
import java.util.List;
/**
 * Created by lab on 24.12.2014.
 */
public interface DataDao {
    public void Add(Data data);
    public List<Data> GetData(Integer lsid, Integer orgid);
}
