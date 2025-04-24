package com.loja.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println("<head>");
 out.println("<title>Logout</title>");
 out.println("</head>");
 out.println("<body>");

 HttpSession session = request.getSession(false);

 if (session != null) {
 String userName = (String) session.getAttribute("userName");

 session.invalidate();

 out.println("<h3>Obrigado por comprar conosco, " + userName + "!</h3>");
 out.println("<p>Sua sessão foi encerrada com sucesso.</p>");
 } else {
 out.println("<h3>Sua sessão já expirou!</h3>");
 }

 out.println("<p><a href='index.html'>Voltar para página inicial</a></p>");
 out.println("</body>");
 out.println("</html>");
 }
 }
} 
