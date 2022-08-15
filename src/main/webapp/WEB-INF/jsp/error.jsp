<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Error</title>
    </head>
    <body>
        
        <c:choose>
            <c:when test = "${not empty emptyFile}">
                <h1>Error: you didn't choose a file</h1>
            </c:when>
            <c:otherwise>
                <h1>Server error</h1>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>