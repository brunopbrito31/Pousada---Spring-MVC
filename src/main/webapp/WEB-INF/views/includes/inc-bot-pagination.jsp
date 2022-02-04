<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container-paginacao">
    <ul class="botoes-paginacao">
        <c:forEach var="i" begin="${1}" end= "${param.qtPages}">
            <c:choose>  
                <c:when test="${i-1 == param.pageNo}">  
                    <li id="actual"><a href="?pageNo=${i-1}&content=${param.content}">${i}</a></li></li>
                </c:when> 
                <c:otherwise>
                    <li><a href="?pageNo=${i-1}&content=${param.content}">${i}</a></li></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>

<style>
    .container-paginacao{
        display: flex;
        justify-content: center;
        margin-top: 15px;
    }

    .botoes-paginacao{
        display: flex;
        list-style: none;
    }

    .botoes-paginacao li{
        margin: 5px;
        width: 20px;
        height: 20px;
        background-color:red;
        border-radius:${param.borderRadius};
        display:flex;
        justify-content: center;
        align-items:center;
    }

    .botoes-paginacao li a{
        text-decoration: none;
        color:white;
    }

    #actual{
        background-color:${param.colorSelected};
    }
</style>