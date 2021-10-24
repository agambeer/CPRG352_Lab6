package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {
    
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getAttribute("username") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }
        if (request.getAttribute("action") != null && request.getAttribute("action").equals("logout")) {
            session.setAttribute("username", null);
            request.setAttribute("action", null);
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch(action) {
            case "logout":
                session.setAttribute("username", null);
                request.setAttribute("action", null);
                response.sendRedirect("ShoppingList");
                return;
            case "register":
                session.setAttribute("username", request.getParameter("username"));
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
                return;
            case "add":
                items.add(request.getParameter("item"));
                request.setAttribute("item", null);
                request.setAttribute("itemsList", items);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
                return;
            case "delete":
                items.remove(request.getParameter("item[]"));
                request.setAttribute("item", null);
                request.setAttribute("itemsList", items);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
                return;
            default:
                break;
        }
    }
}
