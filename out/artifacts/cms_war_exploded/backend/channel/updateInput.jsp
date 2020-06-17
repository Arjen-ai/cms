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
<div style="color: red">编辑频道</div>
<form action="/backend/ChannelServlet" method="post" style="padding-left: auto">

    <%--要一个id不然都别编辑了--%>
    <input type="hidden" name="id" value="${channel.cid}">
    <%--隐藏域给服务器提交--%>
    <input type="hidden" name="method" value="update">
    <table border="2" bgcolor="aqua" cellspacing="0">
        <tr>
            <td>
                名字：<input type="text" name="cname" value="${channel.cname}">
            </td>
        </tr>
        <tr>
            <td>
                描述：<textarea name="description" id="" cols="30" rows="10">${channel.description}</textarea>
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
