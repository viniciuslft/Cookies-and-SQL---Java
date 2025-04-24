package com.loja.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewCart")
public class ViewCart extends HttpServlet {
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println("<head>");
 out.println("<title>Carrinho de Compras</title>");
 out.println("</head>");
 out.println("<body>");

 HttpSession session = request.getSession(false);

 if (session != null) {
 String userName = (String) session.getAttribute("userName");
 out.println("<h2>Carrinho de " + userName + "</h2>");

 ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

 if (cart != null && !cart.isEmpty()) {
 out.println("<h3>Itens no carrinho:</h3>");
 out.println("<ul>");
 for (String item : cart) {
 out.println("<li>" + item + "</li>");
 }
 out.println("</ul>");

 out.println("<p><a href='ClearCart'>Limpar Carrinho</a></p>");
 } else {
 out.println("<p>Seu carrinho está vazio!</p>");
 }

 int maxInactiveInterval = session.getMaxInactiveInterval();
 out.println("<p>Tempo restante da sessão: " + maxInactiveInterval + " segundos</p>");

 out.println("<p><a href='Login'>Continuar Comprando</a></p>");
 out.println("<p><a href='Logout'>Encerrar Sessão</a></p>");
 } else {
 out.println("<h3>Sua sessão expirou! Por favor, faça login novamente.</h3>");
 out.println("<a href='index.html'>Voltar para página inicial</a>");
 }

 out.println("</body>");
 out.println("</html>");
 }
 }
}