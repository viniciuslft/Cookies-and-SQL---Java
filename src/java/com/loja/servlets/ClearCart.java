package com.loja.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet("/ClearCart")
public class ClearCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Limpar Carrinho</title>");
            out.println("</head>");
            out.println("<body>");

            HttpSession session = request.getSession(false);
            if (session != null) {
                String userName = (String) session.getAttribute("userName");

                if (userName != null) {
                    ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
                    if (cart != null) {
                        cart.clear();
                        session.setAttribute("cart", cart);
                    }

                    try (Connection conn = DBUtil.getConnection()) {
                        String sql = "DELETE FROM carrinho WHERE userName=?";
                        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                            stmt.setString(1, userName);
                            int rowsAffected = stmt.executeUpdate();
                            if (rowsAffected > 0) {
                                out.println("<h3>Carrinho esvaziado com sucesso!</h3>");
                            } else {
                                out.println("<h3>Não havia itens no carrinho para este usuário no banco de dados.</h3>");
                            }
                        } catch (SQLException e) {
                            out.println("<p>Erro ao limpar o carrinho no banco: " + e.getMessage() + "</p>");
                        }
                    } catch (SQLException e) {
                        out.println("<p>Erro ao conectar com o banco de dados: " + e.getMessage() + "</p>");
                    }
                    out.println("<p><a href='Login'>Continuar Comprando</a></p>");
                } else {
                    out.println("<h3>Usuário não autenticado! Faça login novamente.</h3>");
                    out.println("<a href='index.html'>Voltar para página inicial</a>");
                }
            } else {
                out.println("<h3>Sua sessão expirou! Por favor, faça login novamente.</h3>");
                out.println("<a href='index.html'>Voltar para página inicial</a>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }
}