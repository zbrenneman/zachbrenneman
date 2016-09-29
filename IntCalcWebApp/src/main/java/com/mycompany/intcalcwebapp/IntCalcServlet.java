/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.intcalcwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "IntCalcServlet", urlPatterns = {"/IntCalcServlet"})
public class IntCalcServlet extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet IntCalcServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet IntCalcServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        
        RequestDispatcher rd = request.getRequestDispatcher("entry.jsp");
        
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
        
        String intRate = request.getParameter("intRate");
        double interestRate = Double.parseDouble(intRate);
        
        String choice = request.getParameter("compound");
        
        String prinAmt = request.getParameter("principalAmt");
        double newYearPrincipal = Double.parseDouble(prinAmt);
        
        String year = request.getParameter("years");
        double years = Double.parseDouble(year);
        
        double originalPrincipal = 0;
        
        
        interestRate = returnRate(choice, interestRate);
        
        List<Integer> numberOfYears = new ArrayList();
        List<Double> startPrincipal = new ArrayList();
        List<Double> interest = new ArrayList();
        List<Double> endPrincipal = new ArrayList();
        
        for (int i = 0; i < years; i++) {

            originalPrincipal = newYearPrincipal;

            newYearPrincipal = calcNewYearPrincipal(newYearPrincipal, interestRate);

            double interestThisYear = (newYearPrincipal - originalPrincipal);
            double endYearPrincipal = newYearPrincipal;
            
            numberOfYears.add(i+1);
            startPrincipal.add(originalPrincipal);
            interest.add(interestThisYear);
            endPrincipal.add(endYearPrincipal);
        }
        
        request.setAttribute("years", numberOfYears);
        request.setAttribute("startP", startPrincipal);
        request.setAttribute("interest", interest);
        request.setAttribute("endP", endPrincipal);
        
        
        RequestDispatcher rd = request.getRequestDispatcher("entry.jsp");

        rd.forward(request, response);
        
        
    }
    
    public double returnRate(String type, double interestRate) {

        switch (type) {
            case "q":
                interestRate = (interestRate / 4) / 100;
                break;
            case "m":
                interestRate = (interestRate / 12) / 100;
                break;
            case "d":
                interestRate = (interestRate / 365) / 100;
                break;
            default:
                break;
        }

        return interestRate;
    }
    
     public double calcNewYearPrincipal(double newYearPrincipal, double Interest) {

        for (int j = 0; j < 4; j++) {

            newYearPrincipal += (newYearPrincipal * Interest);

        }
        return newYearPrincipal;
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
