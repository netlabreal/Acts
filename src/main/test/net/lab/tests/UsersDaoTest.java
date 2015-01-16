package net.lab.tests;

import net.lab.daoimpl.UsersDaoImplementation;
import net.lab.entity.Users;
import org.junit.Test;

import java.util.List;

/**
 * Created by lab on 17.11.2014.
 */
public class UsersDaoTest {
    private static UsersDaoImplementation udi;
    public UsersDaoTest(){
        udi = new UsersDaoImplementation();
    }
    @Test
    public void AllUsersTest(){
        List<Users> ll = udi.GetUsers();
        System.out.println(ll.size());
    }
    @Test
    public void AddUserTest(){
    Users us = new Users();
        us.setLogin("Vasa");
        us.setUsername("Vasa");
        us.setPassword("real");
        us.setAdm(1);

        udi.AddUser(us);
    }

    @Test
    public void FindUserTest(){
        Users u = udi.FindUser(10);
        if(u!=null) {
            System.out.println(u.toString());
        }
        else {
            System.out.println("null");
        }
    }
   // @Test
    public void EditUserTest(){
        Users u = new Users();
        u.setId(11);u.setUsername("KOLA");u.setLogin("kola");u.setAdm(0);

        udi.EditUser(u,1);
    }
   // @Test
    public void DelUserTest(){
        Users u = udi.FindUser(1);
        if(u!=null) {
            System.out.println(u.toString());
            udi.DelUser(u);
        }
        else {
            System.out.println("null");
        }
    }

    @Test
    public void LoginTest(){
        Users u = new Users();
        u.setLogin("Vasa");
        u.setUsername("Vasa");
        u.setPassword("1");
        u.setAdm(1);

        boolean tip = udi.Login(u);
        System.out.println(tip);

    }

}
