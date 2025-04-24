package com.loja.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Adicionar ao Carrinho</title>");
            out.println("</head>");
            out.println("<body>");

            HttpSession session = request.getSession(false);

            if (session != null) {
                String product = request.getParameter("product");

                ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

                if (product != null && !product.isEmpty()) {
                    cart.add(product);
                    String userName = (String) session.getAttribute("userName");

                    try (Connection conn = DBUtil.getConnection()) {
                        String sql = "INSERT INTO carrinho (userName, produto) VALUES (?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, userName);
                        stmt.setString(2, product);
                        stmt.executeUpdate();
                    } catch (SQLException e) {
                        out.println("<p>Erro ao salvar produto no banco: " + e.getMessage() + "</p>");
                    }

                    out.println("<h3>Produto '" + product + "' adicionado ao carrinho!</h3>");
                }

                session.setAttribute("cart", cart);

                out.println("<p>ID da Sessão: " + session.getId() + "</p>");
                long creationTime = session.getCreationTime();
                out.println("<p>Sessão criada em: " + new java.util.Date(creationTime) + "</p>");

                out.println("<p><a href='Login'>Voltar para Produtos</a></p>");
                out.println("<p><a href='ViewCart'>Ver Carrinho</a></p>");
            } else {
                out.println("<h3>Sua sessão expirou! Por favor, faça login novamente.</h3>");
                out.println("<a href='index.html'>Voltar para página inicial</a>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }
}
