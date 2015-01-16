package net.lab.daoimpl;

import net.lab.dao.LsDao;
import net.lab.entity.Ls;
import net.lab.orm.Hutil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LsDaoImplementation implements LsDao {
    private static Session session = null;

    @Override
    public void AddLS(Ls ls) {
        try{
            if(Find(ls)==null) {
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(ls);
                tx.commit();
            }
            else {
                System.out.println("Такой лс уже существует!!!");
            }
        }
        catch (Exception e){System.out.println(e);}
            finally {
            if (session != null && session.isOpen()) {session.close();}
        }
    }

    @Override
    public Integer DelLs(Ls ls) {
        Integer res = 0;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete from Ls where nomer=:nomer");
            q.setParameter("nomer",ls.getNomer());
            res = q.executeUpdate();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return res;
    }

    @Override
    public Ls Find(Ls ls) {
        Ls rls = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Ls where name=:name and kwid=:kwid");
            q.setParameter("name",ls.getName());
            q.setParameter("kwid",ls.getKwid());
            rls = (Ls) q.uniqueResult();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return rls;
    }

    @Override
    public List<Ls> GetAll(Integer idkw) {
        List<Ls> lses = null;
            try {
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Ls where kwid=:kw");
                q.setParameter("kw",idkw);
                lses = q.list();
                tx.commit();
            }
            catch (Exception e){System.out.println(e);}
            finally {
                if (session != null && session.isOpen()) {session.close();}
            }
        return lses;
    }

    @Override
    public Ls GetLs(Integer idkw) {
        Ls ls = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Ls where kwid=:kw");
            q.setParameter("kw",idkw);
            ls = (Ls) q.uniqueResult();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }

        return ls;
    }
}
