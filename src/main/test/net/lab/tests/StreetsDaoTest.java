package net.lab.tests;

import net.lab.daoimpl.DomaDaoImplementation;
import net.lab.daoimpl.KwDaoImplementation;
import net.lab.daoimpl.LsDaoImplementation;
import net.lab.daoimpl.StreetsDaoImplementation;
import net.lab.entity.Doma;
import net.lab.entity.Kw;
import net.lab.entity.Ls;
import net.lab.entity.Streets;
import org.junit.Test;

import java.util.List;

/**
 * Created by lab on 28.11.2014.
 */
public class StreetsDaoTest {
    private static StreetsDaoImplementation sdi = null;
    private static DomaDaoImplementation ddi = null;
    private static KwDaoImplementation kdi = null;
    private static LsDaoImplementation ldi = null;


    public StreetsDaoTest(){
        sdi = new StreetsDaoImplementation();
        ddi = new DomaDaoImplementation();
        kdi = new KwDaoImplementation();
        ldi = new LsDaoImplementation();
    }

    @Test
    public void AddStreetTest(){
        List<Doma> doma = null;
        Streets s = null;
        //s.setName("Луначарского");
        //sdi.Add(s);

        s = sdi.Find("Луначарского");

        //Integer del = sdi.Del(s.getName());

        //List<Streets> ls= sdi.GetAll();

        //for(int i=0;i<ls.size();i++) {
        //    s = ls.get(i);
        //    System.out.println(s.getName());
       // }

        Doma d = new Doma();
        d.setNstr(s.getId());
        d.setDom(11);d.setName("11");
        ddi.Add(d);

        doma = ddi.GetAll(s.getId());
        for(int i =0; i<doma.size();i++){
            System.out.println("ул. "+s.getName()+" дом № "+doma.get(i).getName());
        }
    }

    @Test
    public void TestKw(){
        Kw kw = new Kw();
        kw.setKw(8);kw.setName("8");kw.setNdoma(1);

        kdi.AddKw(kw);



    }

    @Test
    public void TestLs(){
        Ls ls = new Ls();
        ls.setKwid(1);ls.setNomer(10203041);ls.setName("FIO1");
        ldi.AddLS(ls);
    }


    @Test
    public void AllTest(){
        List<Streets> streets = null;
        List<Doma> doma = null;
        List<Kw> kws = null;
        List<Ls>lses = null;

        streets = sdi.GetAll();
        for(int i=0;i<streets.size();i++){
            doma = ddi.GetAll(streets.get(i).getId());
            for(int i1 = 0;i1<doma.size();i1++){
                kws = kdi.GetAll(doma.get(i1).getId());
                for(int i2 = 0;i2<kws.size();i2++){
                    lses = ldi.GetAll(kws.get(i2).getId());
                    for(int i3 = 0;i3<lses.size();i3++){
                        System.out.println("ул. "+streets.get(i).getName()+" дом "+doma.get(i1).getName()+" кв. "+kws.get(i2).getName()+" "+lses.get(i3).getNomer()+" "+lses.get(i3).getName());
                    }
                }
            }
        }

    }
}
