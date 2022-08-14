<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Data</title>
    </head>
    <body>
        
        <table>
            <thead>
                <th colspan="7"><c:out value="с ${balances[0].getFrom()} по ${balances[0].getTo()}"/></th>
            </thead>
            <thead>
                <th rowspan="2">Б/сч</th>
                <th colspan="2">ВХОДЯЩЕЕ САЛЬДО</th>
                <th colspan="2">ОБОРОТЫ</th>
                <th colspan="2">ИСХОДЯЩЕЕ САЛЬДО</th>
            </thead>
            <thead>
                <th></th>
                <th>Актив</th>
                <th>Пассив</th>
                <th>Дебет</th>
                <th>Кредит</th>
                <th>Актив</th>
                <th>Пассив</th>
            </thead>
            <c:set var="turnoverIdx" value="0" scope="page"/>
            <c:set var="balanceIdx" value="0" scope="page"/>
            <c:set var="currentClass" value="-1" scope="page"/>
            <c:forEach var="balance" items="${balances}">
                    <c:choose>
                        <c:when test="${currentClass ne balance.getBankClass().getNumber()}">
                            <c:set var="currentClass" value="${balance.getBankClass().getNumber()}" scope="page"/>
                            <tr><td style="text-align: center" colspan="7"><c:out value="${balance.getBankClass().getNumber()} ${balance.getBankClass().getDescription()}"/></td></tr>
                        </c:when>    
                        <c:otherwise>
                            <tr>
                                <td><c:out value="${balances[balanceIdx].getBank()}"/></td>
                                <td><c:out value="${balances[balanceIdx].getAsset()}"/></td>
                                <td><c:out value="${balances[balanceIdx].getLiability()}"/></td>

                                <td><c:out value="${turnovers[turnoverIdx].getDebit()}"/></td>
                                <td><c:out value="${turnovers[turnoverIdx].getCredit()}"/></td>
                                <c:set var="turnoverIdx" value="${turnoverIdx + 1}" scope="page"/>

                                <td><c:out value="${balances[balanceIdx+1].getAsset()}"/></td>
                                <td><c:out value="${balances[balanceIdx+1].getLiability()}"/></td>
                                <c:set var="balanceIdx" value="${balanceIdx + 2}" scope="page"/>
                            </tr>
                        </c:otherwise>
                    </c:choose>
            </c:forEach>
        </table>
        
    </body>
</html>