<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sunny.ToDo.model.ToDo" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View To Do Item List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <style>
        a {
            color: white;

        }
        a:hover {
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="p-3"> To-Do Item List</h1>
    <form:form>
        <table class="table table-bordered">
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Date</th>
                <th>Status</th>
                <th>Mark Completed</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <%
                List<ToDo> list = (List<ToDo>) request.getAttribute("list");
                for(ToDo todo: list){
            %>
                  <tr>
                        <td><%= todo.getId() %></td>
                        <td><%= todo.getTitle() %></td>
                        <td><%= todo.getDate() %></td>
                        <td><%= todo.getStatus() %></td>
                        <td><button type="button" class="btn btn-success">
                                <a href="/updateToDoStatus/<%= todo.getId() %>">Mark Complete</a>
                            </button></td>
                        <td><a href="/editToDoItem/<%= todo.getId() %>" class="btn btn-primary">Edit</a></td>
                        <td><a href="/deleteToDoItem/<%= todo.getId() %>" class="btn btn-danger">Delete</a></td>
                  </tr>
            <%
                }
            %>
        </table>
    </form:form>
    <a href="/addToDoItem" class="btn btn-primary btn-block">Add New To-Do Item</a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script th:inline="javascript">
    window.onload = function() {
        var msg = "${message}";
        if (msg == "Save Success") {
            toastr["success"]("Item added successfully!!");
        } else if (msg == "Delete Success") {
            toastr["success"]("Item deleted successfully!!");
        } else if (msg == "Delete Failure") {
            toastr["error"]("Some error occurred, couldn't delete item");
        } else if (msg == "Edit Success") {
            toastr["success"]("Item updated successfully!!");
        }

        toastr.options = {
            "closeButton": true,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": false,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
    };
</script>

</body>
</html>
