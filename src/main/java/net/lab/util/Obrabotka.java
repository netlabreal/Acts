package net.lab.util;

import net.lab.daoimpl.*;
import net.lab.entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Obrabotka {
    private String filename;

    public Obrabotka(String fname){
        filename = fname;
    }

    public Boolean isExists(){
        Boolean b = false;
        File f = new File(filename);
        b = f.exists();
        return b;
    }

    public void Perebor(){
        String stroka;
        Integer strid = -1;Integer did = -1;Integer kid = -1; Integer lid =- 1;

        StreetsDaoImplementation sdi = new StreetsDaoImplementation();
        DomaDaoImplementation ddi = new DomaDaoImplementation();
        KwDaoImplementation kdi = new KwDaoImplementation();
        LsDaoImplementation ldi = new LsDaoImplementation();
        DataDaoImplementation datadi = new DataDaoImplementation();


        try {
            if (isExists()) {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                int i = 0;
                try {
                    while ((stroka = br.readLine()) != null) {
                        String[] ss = stroka.split(";");
                        String dt = ss[21];
                        if(!dt.equals("Month")) {
                            //**************************//
                            strid = sdi.Find(ss[0]).getId();

                            Doma d = new Doma();
                            d.setDom(Integer.valueOf(ss[1]));d.setKdom(ss[2]);
                            d.setName(ss[1]+ss[2]);d.setNstr(strid);
                            did = ddi.Find(d).getId();

                            Kw k = new Kw();
                            k.setKw(Integer.valueOf(ss[3]));k.setKkw(ss[4]);
                            k.setName(ss[3]+ss[4]);k.setNdoma(did);
                            kid = kdi.Find(k).getId();

                            lid = ldi.GetLs(kid).getId();

                            //**************************//
                        }
                            i++;
                        System.out.println(i+" запись");
                    }
                } catch (Exception e) {
                }
            }
        }
        catch (Exception e){}
    }

    public void Razbor(){
    String[] ss1=null;
    String stroka;
    Data data = null;
    Integer orgid = -1;

        OrgDaoImplementation odi = new OrgDaoImplementation();
        StreetsDaoImplementation sdi = new StreetsDaoImplementation();
        DomaDaoImplementation ddi = new DomaDaoImplementation();
        KwDaoImplementation kdi = new KwDaoImplementation();
        LsDaoImplementation ldi = new LsDaoImplementation();
        DataDaoImplementation datadi = new DataDaoImplementation();

        try{
            if(isExists()) {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                int i = 0;
                try {
                    while ((stroka = br.readLine()) != null) {
                        Integer strid = -1;Integer did = -1;Integer kid = -1; Integer lid =- 1;

                        String[] ss = stroka.split(";");
                        String dt = ss[21];
                        if(!dt.equals("Month")) {
                            //**************************//
                            if(orgid==-1){
                                if(odi.FindOrg(ss[24])==null) {
                                    Organizations org = new Organizations();
                                    org.setName(ss[24]);
                                    odi.AddOrg(org);
                                }
                                orgid = odi.FindOrg(ss[24]).getId();
                            }
                            //**************************//
                            data = new Data();
                            DateFormat df =new SimpleDateFormat("dd.MM.yyyy");
                            //**************************//
                            if(sdi.Find(ss[0])==null){
                                Streets str = new Streets();
                                str.setName(ss[0]);
                                sdi.Add(str);
                            }
                            strid = sdi.Find(ss[0]).getId();
                            //**************************//
                            Doma d = new Doma();
                            d.setDom(Integer.valueOf(ss[1]));d.setKdom(ss[2]);
                            d.setName(ss[1]+ss[2]);d.setNstr(strid);
                            if(ddi.Find(d)==null){
                                ddi.Add(d);
                            }
                            did = ddi.Find(d).getId();
                            //**************************//
                            Kw k = new Kw();
                            k.setKw(Integer.valueOf(ss[3]));k.setKkw(ss[4]);
                            k.setName(ss[3]+ss[4]);k.setNdoma(did);
                            if(kdi.Find(k)==null){
                                kdi.AddKw(k);
                            }
                            kid = kdi.Find(k).getId();
                            //**************************//
                            Ls l = new Ls();
                            l.setName(ss[5]);l.setKwid(kid);
                            if(ldi.GetLs(kid)==null){
                                ldi.AddLS(l);
                            }
                            lid = ldi.GetLs(kid).getId();
                                data = new Data();
                                data.setMonth(df.parse(dt));
                                data.setNls(lid);
                                data.setProp(Integer.valueOf(ss[6]));
                                data.setPlosh(Float.valueOf(ss[7]));

                                data.setNach(Float.valueOf(ss[11]));
                                data.setAct(Float.valueOf(ss[13]));
                                data.setPer(Float.valueOf(ss[14]));
                                data.setVist(Float.valueOf(ss[15]));
                                data.setOpl(Float.valueOf(ss[17]));

                                data.setVxSaldo(Float.valueOf(ss[22]));
                                data.setIsxSaldo(Float.valueOf(ss[23]));
                                data.setOrgid(orgid);

                                datadi.Add(data);

                            i++;
                            System.out.println(i+" запись");
                            //**************************//
//                            data.setStreet(ss[0]);
//                            data.setNdom(Integer.valueOf(ss[1]));
//                            data.setKdom(ss[2]);
//                            data.setNkw(Integer.valueOf(ss[3]));
//                            data.setKkw(ss[4]);
//                            data.setFio(ss[5]);
//                            data.setProp(Integer.valueOf(ss[6]));
//                            data.setPlosh(Float.valueOf(ss[7]));

//                            data.setNach(Float.valueOf(ss[11]));
//                            data.setAct(Float.valueOf(ss[13]));
//                            data.setPer(Float.valueOf(ss[14]));
//                            data.setVist(Float.valueOf(ss[15]));
//                            data.setOpl(Float.valueOf(ss[17]));

//                            data.setVxSaldo(Float.valueOf(ss[22]));
//                            data.setIsxSaldo(Float.valueOf(ss[23]));
//                            data.setOrgid(orgid);
                            //**************************//
                            int gg =1;
                        }
                        else {System.out.println("1111111111111111111111");}
                    }
                } catch (IOException e) {e.printStackTrace();}
            }
            }
        catch (Exception eq){}
    }

}
