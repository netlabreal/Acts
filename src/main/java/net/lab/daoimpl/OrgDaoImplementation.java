package net.lab.daoimpl;

import net.lab.dao.OrgDao;
import net.lab.entity.Organizations;
import net.lab.orm.Hutil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrgDaoImplementation implements OrgDao {
    private static Session session=null;

    @Override
    public Organizations FindOrg(String orgname) {
        Organizations org = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Organizations where name=:name");
            query.setParameter("name",orgname);
            org = (Organizations) query.uniqueResult();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }

        return org;
    }

    @Override
    public void AddOrg(Organizations o) {
        try{
            if(FindOrg(o.getName())==null) {
                session = Hutil.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();
                session.save(o);
                tx.commit();
                System.out.println("Организация :" + o.getName() + "была добавлена!");
            }
            else {
                System.out.println("Такая организация уже существует!");
            }
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
    }

    @Override
    public Integer DelOrg(Organizations o) {
        Integer res = 0;
        try{
        session = Hutil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
            Query query =session.createQuery("delete from Organizations where name=:name");
            query.setParameter("name",o.getName());
            res = query.executeUpdate();
            tx.commit();
            if(res==1) {
                System.out.println("Организация :" + o.getName() + " была удалена!");
            }else{System.out.println("Организация :" + o.getName() + " не была удалена!");}
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }

        return res;
    }

    @Override
    public List<Organizations> GetOrgs() {
        List<Organizations> orgs = null;

        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            orgs = session.createQuery("from Organizations order by name").list();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }

        return orgs;
    }
}
