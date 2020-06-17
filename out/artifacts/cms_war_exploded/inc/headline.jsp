<%--
  Created by IntelliJ IDEA.
  User: aijian
  Date: 2020/2/10
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/backend/common/common.jsp"%>
<c:forEach items="${list}" var="row">
    <div style="color: red">${row[1]}</div>
    <p>
        ${row[2]}要知道为什么需要工作流引擎，我们首先要知道如果没有工作流引擎，那么究竟会发生什么事情？

        设想一个典型的业务流程场景：公文流转。假设有一个请假流程，需要经过张三、李四、王五三个人的审批之后，才能结束，那么你作为程序员，你会如何做呢？

        毫无疑问，首先第一步，你需要整理一下思路，看一看在这个请假模块中，你需要做哪些最基本的功能：

        第一、   创建请假单

        第二、   将请假单提交给张三

    </p>
</c:forEach>
