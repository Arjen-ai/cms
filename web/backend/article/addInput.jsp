<%--
  Created by IntelliJ IDEA.
  User: aijian
  Date: 2020/2/5
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加文章</title>
</head>
<body>
    <div style="color: red">添加文章</div>
    <form action="/backend/ArticleServlet" method="post" style="padding-left: auto">
        <%--隐藏域给服务器提交--%>
        <input type="hidden" name="method" value="add">
        <table border="2" bgcolor="aqua" cellspacing="0">
            <tr>
                <td>
                    标题：<input type="text" name="title">
                </td>
            </tr>
            <tr>
                <td>
                    内容：<textarea name="content" id="" cols="30" rows="10"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    作者：<input type="text" name="author">
                </td>
            </tr>
            <tr>
                <td>
                    来源：<input type="text" name="source">
                </td>
            </tr>
            <tr>
                <td>
                    头条：<input type="text" name="headline">
                </td>
            </tr>
            <tr>
                <td>
                    话题：<input type="text" name="topic">
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <input type="submit" value="提交">
                        <input type="reset" value="重置">
                    </div>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
