package net.lab.dao;

import net.lab.entity.Users;

import java.util.List;

public interface UsersDao {
    public void AddUser(Users u);
    public Integer DelUser(Users u);
    public Integer EditUser(Users u, Integer id);
    public Users FindUser(Integer id);
    public Users FindUser(String login);

    public List<Users> GetUsers();
    public boolean Login(Users u);
}
