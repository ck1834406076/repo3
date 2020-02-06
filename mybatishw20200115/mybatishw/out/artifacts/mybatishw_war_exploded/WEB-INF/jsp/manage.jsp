<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/8
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div id="w0" align="center">
    <div class="heading clearfix">
   <a type="button"  href="${pageContext.servletContext.contextPath}/create.jsp">添加员工</a>
    </div>
    <div >
        <form action="${pageContext.servletContext.contextPath}/StudentServlet?method=query" method="post">
            <div class="btn-group">
                <span>学号：</span> <input type="text" name="id" value="">
                <span>姓名：</span> <input type="text" name="sname" value="${stuInfo.sname}">
                <button type="submit" class="btn btn-success">
                    <i class="glyphicon glyphicon-search"></i> 搜索
                </button>
            </div>
        </form>
    </div>
        <table class="table table-hover" border="1px" cellspacing="0">
            <thead>
            <tr>
                <th nowrap>学号</th>
                <th nowrap>姓名</th>
                <th nowrap>年龄</th>
                <th nowrap>性别</th>
                <th nowrap>地址</th>
                <th class="action-column">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty students}">
                <c:forEach items="${students}" var="stu">
                    <tr>
                        <td>${stu.id}</td>
                        <td>${stu.sname}</td>
                        <td>${stu.age}</td>
                        <td>
                            <c:if test="${stu.gender==1}">男</c:if>
                            <c:if test="${stu.gender==0}">女</c:if>
                        </td>
                        <td>${stu.address}</td>
                        <td>
                                <button type="button" id="forbidbtn" name="forbidbtn"
                                        class="btn btn-danger btn-sm"
                                        onclick="deleteById(${stu.id})">删除</button>
                                <button type="button" id="updatebtn" name="updatebtn"
                                        class="btn btn-info btn-sm"
                                        onclick="updateStu(${stu.id})">修改用户信息</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

            </tbody>
        </table>
    <div>
        <button onclick="prePage(${pageInfo.pageNum})">上一页</button>
        <strong>当前页:<label name="currentPage">${pageInfo.pageNum}</label>&nbsp</strong>
        <strong>总页数:<label name="totalPages">${pageInfo.pages}</label>&nbsp</strong>
        <strong>总记录数: <label name="totalRecords">${pageInfo.total}</label>&nbsp</strong>
        <strong>跳转至第: <input type="text" name="jump">页&nbsp</strong>
        <button onclick="go($('[name=jump]').val())">go</button>
        <button onclick="nextPage(${pageInfo.pageNum})">下一页</button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
<script>
    function deleteById(id){
        location.href="${pageContext.servletContext.contextPath}/StudentServlet?method=delete&id="+id;
    }
    function updateStu(id) {
        location.href="${pageContext.servletContext.contextPath}/StudentServlet?method=SelectById&id="+id;
    }
    function prePage(page) {
        if(${pageInfo.hasPreviousPage}){
            location.href="${pageContext.servletContext.contextPath}/StudentServlet?method=query&pageNo="+(page-1)+"&sname="+$("[name=sname]").val();
        }else {
            alert("已经是第一页了")
        }
    }
    function nextPage(page) {
        if(${pageInfo.hasNextPage}){
            location.href="${pageContext.servletContext.contextPath}/StudentServlet?method=query&pageNo="+(page+1)+"&sname="+$("[name=sname]").val();
        }else {
            alert("已经最后一页了")
        }
    }
    function go(page) {
        if(page>=1&&page<=${pageInfo.pages}&&page%1==0){
            location.href="${pageContext.servletContext.contextPath}/StudentServlet?method=query&pageNo="+(page)+"&sname="+$("[name=sname]").val();
        }else {
            alert("请输入有效的页码")
        }
    }
</script>
</body>
</html>
