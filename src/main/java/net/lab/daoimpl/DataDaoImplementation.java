package net.lab.daoimpl;

import net.lab.dao.DataDao;
import net.lab.entity.Data;
import net.lab.entity.Streets;
import net.lab.orm.Hutil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lab on 24.12.2014.
 */

public class DataDaoImplementation implements DataDao{
    private static Session session = null;
    @Override
    public void Add(Data data) {
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(data);
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
    }

    @Override
    public List<Data> GetData(Integer lsid, Integer orgid) {
        List<Data> ld = null;
        try{
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Data where nls=:nls and orgid=:orgid order by month");
            query.setParameter("nls",lsid);
            query.setParameter("orgid",orgid);
            ld = query.list();
            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }

        return ld;
    }

    public HashMap<Integer,Integer> GetStat() {
        HashMap<Integer,Integer> hm = new HashMap<Integer, Integer>();
        List dss = null;
        try {
            session = Hutil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select distinct orgid as org from Data group by orgid");
            //Query query = session.createQuery("select distinct orgid as org, count(nls) as kol from Data group by orgid");
            //Query query = session.createQuery("from Organizations ");
            dss = query.list();
            for(int i = 0; i<dss.size();i++)
            {
                Query query1 = session.createQuery("select count(nls) as kol from Data where orgid=:org group by orgid");
                query1.setParameter("org",dss.get(i));
                List dss1 = query1.list();
                for(int j=0; j<dss1.size();j++) {
                    System.out.println("Org " + dss.get(i)+" has "+dss1.get(j)+" records");
                    hm.put((Integer)dss.get(i),(Integer)dss1.get(j));
                }
            }

            tx.commit();
        }
        catch (Exception e){System.out.println(e);}
        finally {
            if (session != null && session.isOpen()) {session.close();}
        }
        return hm;
    }
}
