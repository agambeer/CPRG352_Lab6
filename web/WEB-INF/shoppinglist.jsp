<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}</p>
        <p><a href="ShoppingList?action=logout">Logout</a></p>
        
        <form method="POST" action="">
            <h2>Add Item</h2>
            <input type="text" name="item" value="${item}"> <input type="submit" value="Add Item">
            <input type="hidden" name="action" value="add">
        </form>
        
        <form method="POST" action="">
            <ul>
                <c:forEach var="thing" items="${itemsList}">
                    <li><input type="radio" name="item[]" value="${thing}"> <label>${thing}</label></li>
                </c:forEach>
            </ul>
            <c:if test="${itemsList.size() != 0}">
                <input type="submit" value="Delete">
                <input type="hidden" name="action" value="delete">
            </c:if>
        </form>
    </body>
</html>
