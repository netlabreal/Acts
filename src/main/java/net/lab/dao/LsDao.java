package net.lab.dao;


import net.lab.entity.Ls;

import java.util.List;

public interface LsDao {
    public void AddLS(Ls ls);
    public Integer DelLs(Ls ls);
    public Ls Find(Ls ls);
    public List<Ls> GetAll(Integer idkw);
    public Ls GetLs(Integer idkw);
}
