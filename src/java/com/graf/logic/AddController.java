/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graf.logic;

import com.graf.bussines.ToDo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nich
 */
@WebServlet("/addToDo")
public class AddController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo todo=(ToDo)req.getSession().getAttribute("todo");
        //todo.getTitles().add("test");
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
}
