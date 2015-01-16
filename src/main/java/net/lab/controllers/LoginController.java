package net.lab.controllers;

import net.lab.daoimpl.UsersDaoImplementation;
import net.lab.entity.Users;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        String uname = (String) ses.getAttribute("username");


        resp.setContentType("application/json");
        resp.setHeader("Cache-Control","nocache");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out= resp.getWriter();

        JSONObject json = new JSONObject();

       System.out.println(uname);
        if (uname == null) {
            boolean tr = false;
              Users u = new Users();
              String s = req.getParameter("login");
              String p = req.getParameter("password");
              u.setLogin(s);
              u.setPassword(p);
              tr =  new UsersDaoImplementation().Login(u);
                      if(tr==true){
                      ses.setAttribute("username", u.getLogin());
                      ses.setAttribute("userid", u.getId());
                      json.put("username", u.getLogin());
                      json.put("userid", u.getLogin());

                      out.print(json);
                  }
              else {
                  json.put("username", "");
                  json.put("userid", u.getLogin());
                  out.print(json);
              }
        }


    }
}
