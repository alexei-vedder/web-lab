<%@ page import="vedder.models.DietingPerson" %>
<%@ page import="vedder.models.Ration" %>
<%@ page import="java.util.List" %>
<%@ page import="vedder.controllers.DBManipulator" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="vedder.models.Dish" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet/less" type="text/css" href="main.less">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@400;500;531;600;700;800;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,700;1,700&display=swap" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.9.0/less.min.js"></script>
    <title>Dieters</title>
</head>
<body>
<%
    HttpSession httpSession = request.getSession();
    DietingPerson user = (DietingPerson) httpSession.getAttribute("user");
    List<Ration> rations = null;
    if (user != null) {
        try {
            rations = new DBManipulator().getUsersRations(user);
%>
<header class="main-header header">
    <h1 class="main-header__title title">DIETERS</h1>
    <div class="main-header__wrapper">
        <h2 class="main-header__greeting"> Welcome, <%=user.getName()%></h2>
        <%
            if (request.getParameter("logout") != null) {
                /*HttpSession httpSession2 = request.getSession();*/
                httpSession.removeAttribute("user");
                response.sendRedirect("index.jsp");
            }
        %>
        <form action="#" method="post">
            <input class="main-header__logout button" name="logout" type="submit" value="Log out">
        </form>
    </div>
</header>
<main>
    <div class="main-wrapper">
        <%
            assert rations != null;
            for (Ration ration : rations) {
        %>
        <table class="main-table table">
            <%--<caption>ration name</caption>--%>
            <tr class="main-table__head-row">
                <th>Name</th>
                <th>Calorie (per 100g)</th>
                <th>Mass (g)</th>
                <th>Calorie (total)</th>
            </tr>
            <%
                List<Dish> dishList = ration.getDishList();
                for (Dish dish : dishList) {
            %>
            <tr class="main-table__row">
                <td><%=dish.getName()%>
                </td>
                <td><%=dish.getCaloriePer100g()%>
                </td>
                <td><%=dish.getMassInG()%>
                </td>
                <td><%=dish.getCalorieTotal()%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>
    </div>
    <form class="main-form" action="result.xml" method="get">
        <input class="main-form__button button" type="submit" value="See as XML">
    </form>
</main>
<%
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        response.sendRedirect("index.jsp");
    }
} else {
%>

<p class="main-redirect">
    User not found<br>you will be redirected to the login page after 5 seconds
    <a href="index.jsp">return now</a>
</p>
<meta http-equiv="refresh" content="5; URL=index.jsp">

<%
    }
%>

</body>
</html>
