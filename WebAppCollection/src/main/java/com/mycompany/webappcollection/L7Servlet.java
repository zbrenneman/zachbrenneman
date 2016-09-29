/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webappcollection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apprentice
 */
@WebServlet(name = "L7Servlet", urlPatterns = {"/L7Servlet"})
public class L7Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//       
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("lucky7.jsp");
        
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String myAnswer = request.getParameter("myResponse");

        double money = Double.parseDouble(myAnswer);

        double bet = 0;
        double maxMoney = 0;
        int diceTotal = 0;
        int i = 0;
        int rolls = 0;

        while (money > 0) {
            diceTotal = rollDice();

            money = winOrLose(diceTotal, money);
            rolls++;

            if ((diceTotal == 7) && (money > bet)) {
                maxMoney = money;
                i = rolls;
            }

        }

        String messageToUser = "You went broke after " + rolls + " rolls";

        String messageToUser2 = "You should have quit at " + i + " rolls when you had $" + maxMoney;

        request.setAttribute("message", messageToUser);

        request.setAttribute("message2", messageToUser2);

        RequestDispatcher rd = request.getRequestDispatcher("lucky7.jsp");

        rd.forward(request, response);

    }

    public double winOrLose(int diceTotal, double money) {
        if (diceTotal == 7) {
            money += 4;
        } else {
            money -= 1;
        }

        return money;
    }

    public int rollDice() {
        int diceRoll1, diceRoll2, diceTotal;

        Random rand = new Random();
        diceRoll1 = 1 + rand.nextInt(6);
        diceRoll2 = 1 + rand.nextInt(6);

        diceTotal = diceRoll1 + diceRoll2;

        return diceTotal;

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
