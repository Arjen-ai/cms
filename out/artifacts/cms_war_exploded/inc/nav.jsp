<%--
  Created by IntelliJ IDEA.
  User: aijian
  Date: 2020/2/10
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/backend/common/common.jsp"%>
<c:forEach items="${list}" var="n">
    <a href="/mainServlet?method=topic&channelId=${n[0]}">${n[1]}</a>
</c:forEach>
