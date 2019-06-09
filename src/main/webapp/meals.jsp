<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vladislavZag
  Date: 08/06/2019
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>EEE</h1>
<div>
    <table border="1">
        <tr bgcolor="#adacae">
            <th>
                Описание
            </th>
            <th>
                Калории
            </th>
            <th>
                Дата
            </th>
            <th>
                Время
            </th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr <c:if test="${meal.isExcess()}">bgcolor="#c97a7a"</c:if>>
                <th>
                        ${meal.getDescription()}
                </th>
                <th>
                        ${meal.getCalories()}
                </th>
                <th>
                        ${meal.getDateTime().toLocalDate()}
                </th>
                <th>
                        ${meal.getDateTime().toLocalTime()}
                </th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
