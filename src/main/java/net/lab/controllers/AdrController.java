package net.lab.controllers;

import net.lab.daoimpl.*;
import net.lab.entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lab on 02.12.2014.
 */
public class AdrController extends HttpServlet {
    private static StreetsDaoImplementation sdi = null;
    private static DomaDaoImplementation ddi = null;
    private static KwDaoImplementation kdi = null;
    private static LsDaoImplementation ldi = null;
    private static OrgDaoImplementation odi = null;
    private static DataDaoImplementation datadi = null;

    @Override
    public void init() throws ServletException {
        sdi = new StreetsDaoImplementation();
        ddi = new DomaDaoImplementation();
        kdi = new KwDaoImplementation();
        ldi = new LsDaoImplementation();
        odi = new OrgDaoImplementation();
        datadi = new DataDaoImplementation();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        String str = req.getParameter("name");
        System.out.println(str);
        //////////////////////////////////////////////////
        if(str.equals("streets")){
            JSONArray arr = new JSONArray();
            List<Streets> streets = sdi.GetAll();
            for(int i=0;i<streets.size();i++){
                JSONObject json = new JSONObject();
                json.put("id",streets.get(i).getId());
                json.put("name",streets.get(i).getName());
                arr.add(json);
            }
            out.print(arr);
        }
        //////////////////////////////////////////////////
        if(str.equals("doma")){
            Integer sid = Integer.valueOf(req.getParameter("str_id"));
            JSONArray arr = new JSONArray();
            List<Doma> doma = ddi.GetAll(sid);
            for(int i = 0;i<doma.size();i++){
                JSONObject json = new JSONObject();
                json.put("id",doma.get(i).getId());
                json.put("name",doma.get(i).getName());
                arr.add(json);
            }
            out.print(arr);
        }
        //////////////////////////////////////////////////
        if(str.equals("kw")){
            Integer sid = Integer.valueOf(req.getParameter("dom_id"));
            JSONArray arr = new JSONArray();
            List<Kw> kws = kdi.GetAll(sid);
            for(int i=0;i<kws.size();i++){
                JSONObject json = new JSONObject();
                json.put("id",kws.get(i).getId());
                json.put("name",kws.get(i).getName());
                arr.add(json);
            }
            out.print(arr);
        }
        //////////////////////////////////////////////////
        if(str.equals("ls")){
            Integer kid = Integer.valueOf(req.getParameter("kw_id"));
            Ls realls = ldi.GetLs(kid);
            JSONObject json = new JSONObject();
            json.put("id",realls.getId());
            json.put("name",realls.getName());

            out.print(json);
        }
        //////////////////////////////////////////////////
        if(str.equals("orgs")){
            List<Organizations> orgs = odi.GetOrgs();
            JSONArray arr = new JSONArray();
            for(int i = 0;i<orgs.size();i++) {
                JSONObject json = new JSONObject();
                json.put("id", orgs.get(i).getId());
                json.put("name", orgs.get(i).getName());
                arr.add(json);
            }
            out.print(arr);
        }
        //////////////////////////////////////////////////
        if(str.equals("dat")){
            Integer lid = Integer.valueOf(req.getParameter("ls_id"));
            System.out.println("DATATATATATATATATA " + lid);
            List<Data> datas = datadi.GetData(lid,3);
            JSONArray arr = new JSONArray();
            Format formater = new SimpleDateFormat("yyyy-MM-dd");
            for(int i = 0;i<datas.size();i++) {
                JSONObject json = new JSONObject();
                json.put("month", formater.format(datas.get(i).getMonth()));
                json.put("vx", datas.get(i).getVxSaldo());
                json.put("nach", datas.get(i).getNach());
                json.put("act", datas.get(i).getAct());
                json.put("per", datas.get(i).getPer());
                json.put("opl", datas.get(i).getOpl());
                json.put("isx", datas.get(i).getIsxSaldo());
                arr.add(json);
            }
            out.print(arr);
        }
        //////////////////////////////////////////////////

    }
}
