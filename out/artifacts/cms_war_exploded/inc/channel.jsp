<%--
  Created by IntelliJ IDEA.
  User: aijian
  Date: 2020/2/10
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/backend/common/common.jsp"%>

    <div class="index_topic">
        <img src="images/jiantou.gif" style="float:left">
        <span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">${channel.cname}</span>
        <a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
        <c:forEach items="${article}" var="row">
            <div class="index_topic_list">
                <img src="images/h_article.gif" >
                <a href="/mainServlet?method=seeArticle&id=${row[0]}">${row[1]}</a>
                <span>[${row[4]}]</span>
            </div>
        </c:forEach>
    </div>

