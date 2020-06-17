<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/backend/common/common.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>酒店 后台管理工作平台</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 3px;
            margin-top: 0px;
            margin-right: 3px;
            margin-bottom: 0px;
        }

        .STYLE1 {
            color: #e1e2e3;
            font-size: 12px;
        }

        .STYLE6 {
            color: #000000;
            font-size: 12px;
        }

        .STYLE10 {
            color: #000000;
            font-size: 12px;
        }

        .STYLE19 {
            color: #344b50;
            font-size: 12px;
        }

        .STYLE21 {
            font-size: 12px;
            color: #3b6375;
        }

        .STYLE22 {
            font-size: 12px;
            color: #295568;
        }

        A:active, A:visited, A:link {
            COLOR: #0629FD;
            TEXT-DECORATION: none
        }

        A:hover {
            COLOR: #FF6600;
            TEXT-DECORATION: none
        }

        A.relatelink:active, A.relatelink:visited, A.relatelink:link {
            color: white;
            TEXT-DECORATION: none
        }

        A.relatelink:hover {
            COLOR: #FF6600;
            TEXT-DECORATION: none
        }

        td {
            font-size: 12px;
            color: #003366;
            height: 24px
        }

        .STYLE1 a {
            COLOR: white;
        }

        .STYLE1 A:active, .STYLE1 A:visited, .STYLE1 A:link {
            COLOR: white;
            TEXT-DECORATION: none
        }

        .STYLE1 a:hover {
            COLOR: red;
        }
        -->
    </style>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="24" bgcolor="#353c44">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="6%" height="19" valign="bottom">
                                                <div align="center"><img src="../images/tb.gif" width="14" height="14"/>
                                                </div>
                                            </td>
                                            <td width="94%" valign="bottom"><span class="STYLE1"> 网站文章信息列表</span></td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <div align="right"><span class="STYLE1">
             &nbsp;&nbsp;<img src="../images/add.gif" width="10" height="10"/> <a href="/backend/channel/addInput.jsp">添加</a>
             &nbsp;&nbsp;<img src="../images/edit.gif" width="10" height="10"/> <a href="#">发布</a>
             &nbsp; <img src="../images/del.gif" width="10" height="10"/> <a href="javascript:del()">删除</a>    &nbsp;&nbsp;   &nbsp;
             </span><span class="STYLE1"> &nbsp;</span></div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
                <tr>
                    <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10">
                        <div align="center">
                            <input type="checkbox" name="checkbox" id="checkbox" onclick="selAll(this)"/>
                        </div>
                    </td>
                    <td width="100" height="20" bgcolor="d3eaef" class="STYLE6">
                        <div align="center"><span class="STYLE10">ID</span></div>
                    </td>
                    <td width="100" height="20" bgcolor="d3eaef" class="STYLE6">
                        <div align="center"><span class="STYLE10">频道标题</span></div>
                    </td>
                    <td width="200" height="20" bgcolor="d3eaef" class="STYLE6">
                        <div align="center"><span class="STYLE10">文章内容</span></div>
                    </td>
                </tr>
                <c:if test="${empty table.list}">
                    <tr>
                        <td colspan="9" align="center">请点击查询文章看频道情况！</td>
                    </tr>
                </c:if>
                <c:if test="${not empty table.list}">
                    <c:forEach items="${table.list}" var="row">
                        <tr>
                            <td height="20" bgcolor="#FFFFFF">
                                <div align="center">
                                    <input type="checkbox" name="checkbox2" id="checkbox2" value="${row[0]}"/>
                                </div>
                            </td>
                            <td height="20" bgcolor="#FFFFFF" class="STYLE19">
                                <div align="center">${row[0]}</div>
                            </td>
                            <td height="20" bgcolor="#FFFFFF" class="STYLE19">
                                <div align="center"><a href="/backend/ChannelServlet?method=selUpdate&id=${row[0]}"
                                                       title="点击查看和编辑频道">${row[1]}</a></div>
                            </td>
                            <td height="20" bgcolor="#FFFFFF" class="STYLE19">
                                <div align="center">${row[2]}</div>
                            </td>
                            <td height="20" bgcolor="#FFFFFF">
                                <div align="center" class="STYLE21">
                                    <a href="/backend/ChannelServlet?method=del&id=${row[0]}" title="点击删除频道">删除</a> |
                                    <a href="/backend/ChannelServlet?method=selUpdate&id=${row[0]}" title="点击编辑频道">编辑</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </td>
    </tr>
    <tr>
        <td height="30">
            <%--<jsp:include page="/backend/common/page.jsp">--%>
                <%--<jsp:param name="method" value="list"/>--%>
                <%--<jsp:param name="url" value="/backend/ArticleServlet"/>--%>
            <%--</jsp:include>--%>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="33%">
                            <div align="left">
                                <span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;
                                    共有<strong>${table.total}</strong> 条记录，
                                    当前第<strong> ${table.curPage}</strong> 页，
                                    共 <strong>${table.totalPage}</strong> 页</span>
                            </div>
                        </td>
                        <td width="67%" align=right vAlign="center" noWrap>
                            <a href="/backend/ChannelServlet?method=list&offset=${table.first}">首页</a>
                            <a href="/backend/ChannelServlet?method=list&offset=${table.next}">下页</a>
                            <a href="/backend/ChannelServlet?method=list&offset=${table.pre}">上页</a>
                            <a href="/backend/ChannelServlet?method=list&offset=${table.last}">尾页</a>

                            <select name="pagesize" onchange="selectPagesize(this)">
                                <option value="---请选择---" selected="selected">---请选择每页文章数---</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="25">25</option>
                            </select>
                        </td>
                    </tr>
                </table>
        </td>
    </tr>
</table>
</body>
<script type="text/javascript">
    function selAll(cbk) {
        var checkbox2 = document.getElementsByName("checkbox2");
        for (var i = 0; i < checkbox2.length; i++) {
            checkbox2[i].checked = cbk.checked;
        }
    }

    function del() {
        var v = "";
        var cbk = document.getElementsByName("checkbox2");
        var flag = false;
        for (var i = 0; i < cbk.length; i++) {
            if (cbk[i].checked) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            alert("至少要选择一条删除的记录");
            return;
        }
        for (var i = 0; i < cbk.length; i++) {
            if (cbk[i].checked) {
                v += "id=" + cbk[i].value + "&";
            }
        }
        location = "/backend/ChannelServlet?method=del&" + v;
    }

    function selectPagesize(sel) {
        var options = sel.getElementsByTagName("option");
        for (var i = 1; i < options.length; i++) {
            if (options[i].selected) {
                var val = options[i].value;
            }
        }
        location = "/backend/ChannelServlet?method=list&pgNumber=" + val;
    }
</script>
</html>

