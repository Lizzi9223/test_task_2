<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Files</title>
        <link rel="stylesheet" href="<c:url value="/css/style.css" />">
    </head>
    <body>
        
        <form method="get" action="/test_task_2/">
          <input class="button" type="submit" value="Home"/>
        </form><br><br>
        
        <h2>Uploaded files:</h2><br>
        <table>
            <c:forEach var="file" items="${files}">
                <tr>
                    <td>
                        <form method="get" action="data">
                            <input type="hidden" value="${file.getName()}" name="fileName" />
                            <input class="btn" type="submit" value="${file.getName()}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>