<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User</title>
</head>
<div class="row">
    <div class="col-md-3 col-xs-12"></div>
    <div class="col-md-7 col-xs-12">
        <div class="row">
            <div class="col-md-12 col-xs-12">
                <form:form class="form-horizontal" action="/admin/user" method="POST" modelAttribute="user">

                    <div class="form-group">
                        <div class="form-group">
                            <label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
                        </div>
                        <label for="name" class="col-sm-2 control-label">Name(unique!)</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="name" id="name"/>
                        </div>

                        <label for="age" class="col-sm-2 control-label">User age</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="age" id="age"/>
                        </div>

                        <label for="isAdmin" class="col-sm-2 control-label">Admin or not?</label>
                        <div class="col-sm-10">
                            <form:checkbox class="form-control" path="isAdmin" id="isAdmin"/>
                        </div>

                        <label for="createdDate" class="col-sm-2 control-label">Date of creation</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="createdDate" id="createdDate"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" name="action" value="save" class="btn btn-default">Save</button>
                            <button type="submit" name="action" value="search" class="btn btn-default">Search by name</button>
                            <a class="btn btn-primary" href="/admin/user/cancel<custom:allParams/>">Cancel</a>
                        </div>
                    </div>
                   </form:form>
            </div>
        </div>
        <div class="row">

        <div class="row">
            <div class="col-md-2 col-xs-2"><h3>Name</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Age</h3></div>
            <div class="col-md-2 col-xs-2"><h3>isAdmin</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Date of creation</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Update</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
        </div>
        <c:forEach items="${users.content}" var="user">
            <div class="row">
                <div class="col-md-2 col-xs-2">${user.name}</div>
                <div class="col-md-2 col-xs-2">${user.age}</div>
                <div class="col-md-2 col-xs-2">${user.isAdmin}</div>
                <div class="col-md-2 col-xs-2">${user.createdDate}</div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/user/update/${user.id}<custom:allParams/>">update</a></div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/user/delete/${user.id}<custom:allParams/>">delete</a></div>
            </div>
        </c:forEach>
        </div>
        <div class="col-md-2 col-xs-12">
            <div class="row">
                <div class="col-md-6 col-xs-6 text-center">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <custom:sort innerHtml="Name asc" paramValue="name"/>
                            <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
                            <custom:sort innerHtml="Age asc" paramValue="age"/>
                            <custom:sort innerHtml="Age desc" paramValue="age,desc"/>
                            <custom:sort innerHtml="Date asc" paramValue="createdDate"/>
                            <custom:sort innerHtml="Date desc" paramValue="createdDate,desc"/>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6 col-xs-6 text-center">
                    <custom:size posibleSizes="1,2,3,4,5,10" size="${users.size}" />
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-xs-12 text-center">
            <custom:pageable page="${users}" cell="<li></li>" container="<ul class='pagination'></ul>" />
        </div>
    </div>
</div>
</body>
</html>