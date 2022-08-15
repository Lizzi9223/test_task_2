<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Data</title>
        <link rel="stylesheet" href="<c:url value="/css/style.css" />">
        <style>
            table{
                border-collapse: collapse;
                border: 3px solid black;
            }
            th,td{
                border: 1px solid black;
                padding: 10px;
            }
            th{
               text-align: center 
            }
            td{
               text-align: right
            }
            tr:nth-child(even) {
              background-color: #F5F5F5;
            }            
        </style>
    </head>
    <body>
        
        <form style="float: left" method="get" action="/test_task_2/">
          <input class="button" type="submit" value="Home"/>
        </form>
        <form style="margin-left: 20px" method="get" action="files">
          <input class="button" type="submit" value="Files"/>
        </form><br><br>
        
        <table>
            <thead>
                <th colspan="7" style="border:0; padding: 20px; padding-bottom: 10px; font-size: 18px"><c:out value="${fileName}"/></th>
            </thead>
            <thead>
                <th colspan="7" style="border:0; padding: 20px; padding-top: 0px"><c:out value="с ${balances[0].getFrom()} по ${balances[0].getTo()}"/></th>
            </thead>
            <tr style="background-color: white">
                <th rowspan="2">Б/сч</th>
                <th colspan="2">ВХОДЯЩЕЕ САЛЬДО</th>
                <th colspan="2">ОБОРОТЫ</th>
                <th colspan="2">ИСХОДЯЩЕЕ САЛЬДО</th>
            </tr>
            <tr style="background-color: white">
                <th>Актив</th>
                <th>Пассив</th>
                <th>Дебет</th>
                <th>Кредит</th>
                <th>Актив</th>
                <th>Пассив</th>
            </tr>
            <c:set var="turnoverIdx" value="0" scope="page"/>
            <c:set var="balanceIdx" value="0" scope="page"/>
            <c:set var="currentClass" value="-1" scope="page"/>
            <c:forEach var="balance" items="${balances}">
                    <c:choose>
                        <c:when test="${currentClass ne balance.getBankClass().getNumber()}">
                            <c:set var="currentClass" value="${balance.getBankClass().getNumber()}" scope="page"/>
                            <tr style="background-color: white"><td style="text-align: center; font-weight: bold; font-style: italic" colspan="7"><c:out value="КЛАСС ${balance.getBankClass().getNumber()} ${balance.getBankClass().getDescription()}"/></td></tr>
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