<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Files</title>
    </head>
    <body>
        
        <table>
            <c:forEach var="file" items="${files}">
                <tr>
                    <td>
                        <form method="get" action="data">
                            <input type="hidden" value="${file.getName()}" name="fileName" />
                            <input type="submit" value="${file.getName()}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>