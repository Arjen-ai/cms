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
    <title>查看文章</title>
</head>
<body>
    <div style="color: red">查看文章</div>
    <table border="2" bgcolor="aqua" cellspacing="0">
        <tr>
            <td>
                标题：<input type="text" name="title" value="${art[0][1]}">
            </td>
        </tr>
        <tr>
            <td>
                内容：<textarea name="content" id="" cols="30" rows="10">${art[0][2]}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                作者：<input type="text" name="author" value="${art[0][3]}">
            </td>
        </tr>
        <tr>
            <td>
                来源：<input type="text" name="source" value="${art[0][5]}">
            </td>
        </tr>
    </table>
    <table border="2" bgcolor="aqua" cellspacing="0">
        <tr>
            <td>
                <input type="button" value="返回列表页" onclick="location='/index.jsp'">
            </td>
        </tr>
    </table>
</body>
</html>
