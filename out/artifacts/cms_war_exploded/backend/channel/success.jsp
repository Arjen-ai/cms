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
    <title>成功页面</title>
</head>
<body>
    <div style="color: red">操作成功</div>
    <table border="2" bgcolor="aqua" cellspacing="0">
        <tr>
            <td>
                <input type="button" value="返回列表页" onclick="location='/backend/ChannelServlet?method=list'">
            </td>
        </tr>
    </table>

</body>
</html>
