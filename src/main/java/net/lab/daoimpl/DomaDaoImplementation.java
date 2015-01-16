package net.lab.daoimpl;


import net.lab.dao.DomaDao;
import net.lab.entity.Doma;
import net.lab.entity.Streets;
import net.lab.orm.Hutil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DomaDaoImplementation implements DomaDao {
    private static Session session=null;

    @Override
    public void Add(Doma doma) {
        try{
            if(Find(doma)==null) {
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(doma);
                tx.commit();
            }
            else
            {
                System.out.println("Такой дом уже существует!!!");
            }
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
    }

    @Override
    public Integer Del(Doma dom) {
        Integer res=0;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete from Doma where nstr=:nstr and name=:name");
            q.setParameter("nstr",dom.getNstr());q.setParameter("name",dom.getName());
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
    public Doma Find(Doma dom) {
        Doma d = null;
        try {
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Doma where nstr=:nstr and name=:name");
            q.setParameter("nstr",dom.getNstr());q.setParameter("name",dom.getName());
            d = (Doma) q.uniqueResult();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return d;
    }

    @Override
    public List<Doma> GetAll(Integer strid) {
        List<Doma> doma = null;
            try{
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Doma where nstr=:nstr order by dom,kdom");
                q.setParameter("nstr",strid);
                doma = q.list();
                tx.commit();
            }
            catch (Exception e){System.out.println(e);}
            finally {
                if (session != null && session.isOpen()) {session.close();}
            }
        return doma;
    }
}

