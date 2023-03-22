<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <h1 class="my-2" >Book list</h1>
    
    <form action="search" method="post">
    <div class="row">
    				<div class="col-3" >
                    <input type="text" name="id" class="form-control"
                            value="<c:out value='${book.id}' />"placeholder="Search Book id...."/> 
                   </div>
                    <div class="col-4" >                             
                    <input type="submit" class="btn btn-primary" value="Search" />
                    </div>
              </div>
          </form>
 <a href="addbook.jsp" class="btn btn-info my-3">Add Book</a>
 <a href="listbook" class="btn btn-info my-3">List Book</a>
    <table class="table table-bordered table-hover table-striped table-sm w-75 my-3" >
        <thead>
            <tr>
                <td>Id</td>
                <td>name</td>
                <td>price</td>
                <td>author</td>
                <td style="width: 169px;">action</td>
            </tr>
        </thead>
        <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td style="width: 186px;">
                        <a class="btn btn-danger"onClick='return confirm("do you want to delete this record?")' href="delete?id=<c:out value='${book.id}' />">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="edit?id=<c:out value='${book.id}' />"class="btn btn-success">Update</a>                     
                    </td>
                </tr>
            </c:forEach>
    </table>
    </div>
</body>
</html>