package net.lab.tests;

import net.lab.daoimpl.DataDaoImplementation;
import net.lab.daoimpl.OrgDaoImplementation;
import net.lab.entity.Organizations;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class OrgDaoTest {
    private static OrgDaoImplementation odi=null;
    private static DataDaoImplementation ddi = null;

    public OrgDaoTest(){
        odi = new OrgDaoImplementation();
        ddi = new DataDaoImplementation();
    }


    @Test
    public void AllOrgsTest(){
        List<Organizations> orgs = null;

        orgs = odi.GetOrgs();
        System.out.println(orgs.size());
    }
    @Test
    public void AddOrgTest(){
        Organizations org = new Organizations();
        org.setName("Городок ЖКХ");
        org.setPrim("Городок ЖКХ");
        odi.AddOrg(org);
    }

    @Test
    public void DelOrgTest(){
        Organizations org = new Organizations();
        org.setName("Городок ЖКХ");
        org.setPrim("Городок ЖКХ");
        odi.DelOrg(org);
    }

    @Test
    public void Tmp(){
        HashMap<Integer,Integer> dd = ddi.GetStat();
        System.out.println(dd.size());
        int d = 1;
    }
}

