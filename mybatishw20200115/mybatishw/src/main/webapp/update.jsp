<%--
  Created by IntelliJ IDEA.
  User: ck
  Date: 2020/1/8
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h3>修改信息</h3>
</div>
<br>
<div align="center">
    <form action="${pageContext.servletContext.contextPath}/student/update" method="post">
        <div hidden><span>id</span><input type="text" name="id" value="${stu.id}"></div>
        <div><span>姓名</span><input type="text" name="sname" value="${stu.sname}"></div>
        <div><span>年龄</span><input type="text" name="age" value="${stu.age}"></div>
        <div>
            <span>性别</span>
            <c:if test="${stu.gender==1}">
                <input type="radio" name="gender" value="1" checked="checked"/>男
                <input type="radio" name="gender" value="0" />女
            </c:if>
            <c:if test="${stu.gender==0}">
                <input type="radio" name="gender" value="1" />男
                <input type="radio" name="gender" value="0" checked="checked"/>女
            </c:if>

        </div>
        <div>
            <span>住址</span><input type="text" name="address" value="${stu.address}">
        </div>
        <div>
            <span>班级</span>
            <select name="gradeid" id="selID">
                <c:if test="${not empty gradelist}">
                    <c:forEach items="${gradelist}" var="grade">
                        <option value="${grade.gradeId}"  <c:if test="${grade.gradeId==stu.gradeid}">selected</c:if>>${grade.gradename}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div>
            <button type="submit">修改</button>
            <button type="reset">取消</button>
        </div>
    </form>
</div>

</body>
</html>
