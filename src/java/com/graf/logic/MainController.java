/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graf.logic;

import com.graf.bussines.*;
import com.graf.bussines.UserDB;
import com.graf.util.FileIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nich
 */
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //session info extract
        System.out.println("test");
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        User user = (User) session.getAttribute("user");
        checkUser(req, resp);
        user = (User) session.getAttribute("user");
        int userID;
        ToDo todo = (ToDo) session.getAttribute("todo");
        if(todo == null ){
            System.out.println("Todo is nuuul");
             todo=new ToDo();
             
        }
        System.out.println("INAINTE DE NULL POINTER");
       
        if(user == null){
            System.out.println("USer is null");
            userID=-1;
        }else{
            System.out.println("USer firstname " + user.getFirstName());
            userID=UserDB.getUserID(user.getEmail());
        }
        String action = req.getParameter("action");
        todo=restoreToDo(req, resp);
        session.setAttribute("todo", todo);
        
        //
        String url = "/index.jsp";
        if (action == null) {
            action = "nothing";
        }
        if (action.equals("checkuser")) {
            url = checkUser(req, resp);
            

        }
        
        System.out.println("adding");
        if (action.equals("add")) {
            String whatToDo = req.getParameter("text");
            if (whatToDo.length() > 0) {
                todo.addItem(whatToDo);
            }
            UserDB.inserTodo(todo.getLastItem(), userID);
        }
        System.out.println("added");
        if (action.equals("remove")) {
            UserDB.deleteTodo(todo.getItem(req.getParameter("date")), userID);
            todo.removeItem(req.getParameter("date"));

        }

        if (action.equals("details")) {
            Item item = todo.getItem(req.getParameter("date"));
            req.setAttribute("item", item);
            this.getServletContext().getRequestDispatcher("/details.jsp")
                    .forward(req, resp);
        }

        this.getServletContext().getRequestDispatcher(url)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("register")) {
            User user = new User(req.getParameter("email"),
                    req.getParameter("firstName"),
                    req.getParameter("lastName"));
            System.out.println(user.getFirstName());
            if (UserDB.checkUser(user.getEmail())) {
                System.out.println("TE ARUNC BLEA");
                String message = "User exist,enter another email...";
                req.setAttribute("message", message);
                this.getServletContext().getRequestDispatcher("/register.jsp")
                        .forward(req, resp);
                System.out.println("gata team aruncat");
            } else {
                System.out.println("PULA CUI ");
                UserDB.insertUser(user);
            }
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("emailCookie", req.getParameter("email"));
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 365);
            resp.addCookie(cookie);

        }
        if (action.equalsIgnoreCase("signup")) {
            String email = req.getParameter("email");
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            if (UserDB.checkUser(email, firstName, lastName)) {
                User user = new User(email, firstName, lastName);
                HttpSession session = req.getSession();
                System.out.println("Signup succesful" + user.getFirstName());
                session.setAttribute("user", user);
                Cookie cookie = new Cookie("emailCookie", req.getParameter("email"));
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 * 365);
                resp.addCookie(cookie);
                doGet(req, resp);
                this.getServletContext().getRequestDispatcher("/index.jsp")
                        .forward(req, resp);
            }else{
            this.getServletContext().getRequestDispatcher("/register.jsp")
                    .forward(req, resp);
            }

        }
        doGet(req, resp);
    }

    public String checkUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String email = null;
        try {
            if (user == null) {
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("emailCookie")) {
                            email = cookie.getValue();
                            System.out.println("CAUTAM EMAIL " + email);
                            if (UserDB.checkUser(email)) {
                                user = UserDB.getUser(email);
                                System.out.println(user.getFirstName());
                                session.setAttribute("user", user);
                            } else {
                                return "/register.jsp";
                            }

                        }

                    }
                }
                if (email == null || email.equals("")) {
                    return "/register.jsp";

                } else {

                    return "/index.jsp";
                }

            }
            return "/index.jsp";
        } catch (Exception e) {
            return "/register.jsp";
        }
        
    }

    public ToDo restoreToDo(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        System.out.println("testing before null");
        try {
            User user = (User) session.getAttribute("user");
            System.out.println("TESTING FOR NOT NULL" + user.getFirstName());
            int userID = UserDB.getUserID(user.getEmail());
            ToDo toDo=new ToDo();
            toDo=UserDB.selectTodo(toDo, userID);
            return toDo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
