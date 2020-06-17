<%--
  Created by IntelliJ IDEA.
  User: aijian
  Date: 2020/2/7
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="color: red">编辑文章</div>
<form action="/backend/ArticleServlet" method="post" style="padding-left: auto">

    <%--要一个id不然都别编辑了--%>
    <input type="hidden" name="id" value="${article.a_id}">
    <%--隐藏域给服务器提交--%>
    <input type="hidden" name="method" value="update">
    <table border="2" bgcolor="aqua" cellspacing="0">
        <tr>
            <td>
                标题：<input type="text" name="title" value="${article.title}">
            </td>
        </tr>
        <tr>
            <td>
                内容：<textarea name="content" id="" cols="30" rows="10">${article.content}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                作者：<input type="text" name="author" value="${article.author}">
            </td>
        </tr>
        <tr>
            <td>
                来源：<input type="text" name="source" value="${article.source}">
            </td>
        </tr>
        <tr>
            <td>
                头条：<input type="text" name="headline" value="${article.headline}">
            </td>
        </tr>
        <tr>
            <td>
                话题：<input type="text" name="topic" value="${article.topic}">
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <input type="submit" value="保存">
                    <input type="reset" value="重置">
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
