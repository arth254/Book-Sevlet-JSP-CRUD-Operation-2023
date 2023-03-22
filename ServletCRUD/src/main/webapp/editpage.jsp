<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>

<style>
       body{
          background-image: url("images/test.jpg");
            height: 100%; 
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            }
    </style>
</head>

<body>
<a href="listpage" class="btn btn-success"style="width: 159px;margin-top: 3%;margin-bottom:-3%;margin-left: 7%;">Back List page</a>
<div class="card" style="width: 500px;height:530px;margin-top: 7%;margin-bottom:4%;margin-left: 4%;border: 1px solid;">
        <h1 style="margin-top: 7%;" class="text-center text-primary">Book Edit page</h1>
        <div class="card-body" style="margin-left: 5%;border-radius: 2%;margin-top: 5%;margin-right: 5%;margin-bottom: 23%;border: 1px solid;">
         <form action="update" method="post">
       
                    <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
               <label class="my-1 text-primary">Title</label>
                    <input type="text" name="title" class="form-control"
                            value="<c:out value='${book.title}' />"placeholder="Enter Book Titel" />
               <label class="my-1 text-primary">Author</label>
                    <input type="text" name="author" class="form-control"
                            value="<c:out value='${book.author}' />"placeholder="Enter Book Author"/>
                <label class="my-1 text-primary">Price</label>
                    <input type="text" name="price" class="form-control"
                            value="<c:out value='${book.price}' />"placeholder="Enter Book Price"/>                               
                    <input type="submit" class="btn btn-primary my-3" value="Submit" />
          </form>
    </div>
</div>

</body>
</html>