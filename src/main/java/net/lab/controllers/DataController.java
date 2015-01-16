package net.lab.controllers;

import net.lab.daoimpl.UsersDaoImplementation;
import net.lab.entity.Users;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by lab on 20.11.2014.
 */
public class DataController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDaoImplementation udi = new UsersDaoImplementation();

        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        String sq = req.getParameter("name");
        //***************************//
        if (sq.equals("all_users")) {
            JSONArray arr = new JSONArray();
            List<Users> users = udi.GetUsers();
            for (int i = 0; i < users.size(); i++) {
                JSONObject json = new JSONObject();
                json.put("id", users.get(i).getId());
                json.put("username", users.get(i).getUsername());
                json.put("login", users.get(i).getLogin());
                arr.add(json);
            }
            out.print(arr);
        }
        //***************************//
        if (sq.equals("user")) {
            String login = req.getParameter("login");
            if (login != null) {
                Users u = udi.FindUser(login);
                JSONObject json = new JSONObject();
                json.put("id", u.getId());
                json.put("username", u.getUsername());
                json.put("login", u.getLogin());
                json.put("pass", u.getPassword());
                json.put("adm", u.getAdm());
                out.print(json);
            }
        }
        //***************************//
        if (sq.equals("add_user")) {
            Users u =null;
            String login = req.getParameter("login");
            String username = req.getParameter("username");
            String pass = req.getParameter("pass");
            String adm = req.getParameter("adm");
            JSONObject json = new JSONObject();
            if (login != null) {
                u = udi.FindUser(login);
                if (u == null) {
                    u = new Users();
                    u.setLogin(login);
                    u.setUsername(username);
                    u.setPassword(pass);
                    if (adm.equals("true")){
                        u.setAdm(1);
                    }else{
                        u.setAdm(0);
                    }
                    udi.AddUser(u);
                    json.put("req", "yes");
                } else {
                    json.put("req","no");
                    //  }
                }
            }
            //***************************//
            out.print(json);
        }
        //***************************//
        if (sq.equals("del_user")) {
            String login = req.getParameter("login");
            JSONObject json = new JSONObject();
            if (login != null) {
                Users u = udi.FindUser(login);
                Integer r = udi.DelUser(u);
                json.put("res", r);
            }
            out.print(json);
        }
        //***************************//
        if (sq.equals("edit_user")) {
            String login = req.getParameter("login");
            JSONObject json = new JSONObject();
            if (login != null) {
                Users us =new Users();
                us.setLogin(req.getParameter("login"));
                us.setUsername(req.getParameter("username"));
                us.setPassword(req.getParameter("pass"));
                Integer ss = 0;
                if(req.getParameter("adm").equals("true")){ss=1;}
                us.setAdm(ss);

                Users u = udi.FindUser(login);
                Integer r = udi.EditUser(us, u.getId());
                json.put("res", r);
            }
            out.print(json);
        }
        //***************************//


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/Acts/welcome.jsp");
    }
}