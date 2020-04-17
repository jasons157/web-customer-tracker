<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 4/6/2020
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List Customers</title>

    <!-- reference our style sheet -->
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <!-- put new button: add customer -->
        <input type="button" value="Add Customer"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"
        />

        <!-- add our html table here -->
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempCustomer" items="${customers}">

                <!-- construct an update link with customer id -->
                <c:url var="updateLink" value="/customer/showFormForUpdate">

                    <!-- This sets the update value to hold the correct id parameter, so we know who we're updating -->
                    <c:param name="customerId" value="${tempCustomer.id}"/>

                </c:url>

                <!-- construct an update link with customer id -->
                <c:url var="deleteLink" value="/customer/delete">

                    <!-- This sets the update value to hold the correct id parameter, so we know who we're updating -->
                    <c:param name="customerId" value="${tempCustomer.id}"/>

                </c:url>

                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td>
                        <!-- display update link -->
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                        onclick="if (!(confirm('Are you sure you want to delete this?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </div>
</div>

</body>
</html>
