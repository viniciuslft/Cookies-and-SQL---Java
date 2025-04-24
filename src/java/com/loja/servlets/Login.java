package com.loja.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("userName");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userName".equals(cookie.getName())) {
                        name = cookie.getValue();
                        break;
                    }
                }
            }

            if (name == null) {
                name = request.getParameter("userName");
            }

            if (name == null) {
                out.println("<p>Por favor, informe seu nome de usuário.</p>");
                return;
            }

            ArrayList<String> cart = new ArrayList<>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // ou seu driver JDBC
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root", "root");

                PreparedStatement psCart = conn.prepareStatement("SELECT produto FROM carrinho WHERE userName=?");
                psCart.setString(1, name);
                ResultSet rsCart = psCart.executeQuery();
                while (rsCart.next()) {
                    cart.add(rsCart.getString("produto"));
                }

                conn.close();
            } catch (Exception e) {
                out.println("<p>Erro ao acessar o banco de dados: " + e.getMessage() + "</p>");
            }

            HttpSession session = request.getSession();
            session.setAttribute("userName", name);
            session.setAttribute("cart", cart);
            session.setMaxInactiveInterval(1800);

            Cookie userCookie = new Cookie("userName", name);
            userCookie.setMaxAge(60 * 60 * 24 * 7); // 7 dias
            response.addCookie(userCookie);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loja Virtual</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Bem-vindo, " + name + "!</h2>");
            out.println("<p>Sua sessão expira em 30 minutos de inatividade</p>");
            out.println("<h3>Produtos disponíveis:</h3>");
            out.println("<ul>");
            out.println("<li><a href='AddToCart?product=Livro'>Livro - R$ 50,00</a></li>");
            out.println("<li><a href='AddToCart?product=Notebook'>Notebook - R$ 3000,00</a></li>");
            out.println("<li><a href='AddToCart?product=Smartphone'>Smartphone - R$ 1500,00</a></li>");
            out.println("</ul>");
            out.println("<p><a href='ViewCart'>Ver Carrinho</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
