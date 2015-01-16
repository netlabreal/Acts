package net.lab.daoimpl;

import net.lab.dao.StreetsDao;
import net.lab.entity.Streets;
import net.lab.orm.Hutil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StreetsDaoImplementation implements StreetsDao {
    private static Session session=null;

    @Override
    public Streets Find(Integer strid) {
        Streets street = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Streets where id=:id");
            q.setParameter("id",strid);
            street = (Streets) q.uniqueResult();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return street;
    }

    @Override
    public void Add(Streets street) {
        try{
            if(Find(street.getName())==null){
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(street);
                tx.commit();
                System.out.println("улица : " + street.getName() + "была добавлена!");            }
                else
            {
                System.out.println("Такая улица уже существует!");
            }
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
    }

    @Override
    public Integer Del(String street) {
        Integer res = 0;
            try{
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("delete from Streets where name=:name");
                q.setParameter("name",street);
                res= q.executeUpdate();
                tx.commit();
                System.out.println("улица : " + street + " была удалена!");
            }
            catch (Exception e){System.out.println(e);}
            finally {
                if (session != null && session.isOpen()) {session.close();}
            }
        return res;
    }

    @Override
    public Streets Find(String street) {
        Streets s = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Streets where name = :name");
            q.setParameter("name",street);
            s = (Streets) q.uniqueResult();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return s;
    }

    @Override
    public List<Streets> GetAll() {
        List<Streets> streets =null;
            try{
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                Query q = session.createQuery("from Streets order by name");
                streets = q.list();
                tx.commit();
            }
            catch (Exception e){System.out.println(e);}
            finally {
                if (session != null && session.isOpen()) {session.close();}
            }

        return streets;
    }
}
