package net.lab.daoimpl;

import net.lab.dao.UsersDao;
import net.lab.entity.Users;
import net.lab.orm.Hutil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by lab on 17.11.2014.
 */
public class UsersDaoImplementation implements UsersDao {
    private static Session session = null;


    @Override
    public Users FindUser(Integer id) {
        Users u =null;
        try {
            session = Hutil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Users where id=:id");
            query.setParameter("id",id);
            u = (Users)query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return u;
    }

    @Override
    public Users FindUser(String login) {
        Users u =null;
        try {
            session = Hutil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Users where login=:login");
            query.setParameter("login", login);
            u = (Users)query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return u;
    }

    @Override
    public void AddUser(Users u) {
        try {
            if(FindUser(u.getLogin())==null) {
                session = Hutil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(u);
                session.getTransaction().commit();
            }
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
    }
    @Override
    public Integer DelUser(Users u) {
        Integer res = 0;
        try {
            session = Hutil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from Users where id=:id");
            query.setParameter("id",u.getId());
            res = query.executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return res;
    }
    @Override
    public Integer EditUser(Users u, Integer id) {
        Integer res = 0;
        try {
            session = Hutil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("update Users set username = :username, login=:login, adm=:adm, password=:pass where id=:id");
            query.setParameter("id",id);
            query.setParameter("username",u.getUsername());
            query.setParameter("login",u.getLogin());
            query.setParameter("pass",u.getPassword());
            query.setParameter("adm",u.getAdm());
            res = query.executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return res;
    }
    @Override
    public List<Users> GetUsers() {
        List<Users> lu = null;
        try {
            session = Hutil.getSessionFactory().openSession();
            session.beginTransaction();
            lu = session.createQuery("from Users order by id").list();
            session.getTransaction().commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return lu;
    }
    @Override
    public boolean Login(Users u) {
        boolean t = false;
        Users ur = FindUser(u.getLogin());
        if(ur!=null) {
            if (ur.getPassword().equals(u.getPassword()) && ur.getLogin().equals(u.getLogin())) {
                t = true;
            }
        }
        return t;
    }
}
