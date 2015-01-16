package net.lab.daoimpl;

import net.lab.dao.KwDao;
import net.lab.entity.Kw;
import net.lab.orm.Hutil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class KwDaoImplementation implements KwDao {
    private static Session session = null;

    @Override
    public void AddKw(Kw kw) {
        try{
            if(Find(kw)==null) {
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(kw);
                tx.commit();
            }
            else
            {
                System.out.println("Такая квартира уже существует!!!");
            }
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
    }

    @Override
    public Integer Del(Kw kw) {
        Integer res = 0;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete from Kw where ndoma=:ndoma and name=:name");
            q.setParameter("ndoma",kw.getNdoma());q.setParameter("name",kw.getName());
            res= q.executeUpdate();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return res;
    }

    @Override
    public Kw Find(Kw kw) {
        Kw kwl = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q =session.createQuery("from Kw where ndoma=:ndoma and name=:name");
            q.setParameter("ndoma",kw.getNdoma());q.setParameter("name",kw.getName());
            kwl =(Kw) q.uniqueResult();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }

        return kwl;
    }

    @Override
    public List<Kw> GetAll(Integer iddoma) {
        List<Kw> kws = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Kw where ndoma=:ndoma");
            q.setParameter("ndoma",iddoma);
            kws = q.list();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return kws;
    }
}
