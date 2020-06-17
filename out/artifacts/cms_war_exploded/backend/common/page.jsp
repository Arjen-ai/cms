<%@include file="common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<pg:pager url="${param.url}" export="currentPageNumber=pageNumber" items="${pg.total}" maxPageItems="${pg.pageNumber}">
    <%--传递参数--%>
    <pg:param name="method" value="${param.method}"></pg:param>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="33%">
                <div align="left"><span
                        class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>${pg.total}</strong> 条记录，当前第<strong> ${pg.curPage}</strong> 页，共 <strong>${pg.totalPage}</strong> 页</span>
                </div>
            </td>
            <td width="67%" align=right vAlign="center" noWrap>
                    <%--<a href="/backend/ArticleServlet?method=list&offest=${pg.first}">首页</a>--%>
                    <%--<a href="/backend/ArticleServlet?method=list&offest=${pg.next}">下页</a>--%>
                    <%--<a href="/backend/ArticleServlet?method=list&offest=${pg.pre}">上页</a>--%>
                    <%--<a href="/backend/ArticleServlet?method=list&offest=${pg.last}">尾页</a>--%>
                <pg:first>
                    <a href="${pageUrl}">首页</a>
                </pg:first>
                <pg:next>
                    <a href="${pageUrl}">下页</a>
                </pg:next>
                <pg:pages>
                    <c:if test="${currentPageNumber eq pageNumber}">
                        ${pageNumber}
                    </c:if>
                    <c:if test="${currentPageNumber != pageNumber}">
                        <a href="${pageUrl}">${pageNumber}</a>
                    </c:if>
                </pg:pages>
                <pg:prev>
                    <a href="${pageUrl}">上页</a>
                </pg:prev>
                <pg:last>
                    <a href="${pageUrl}">尾页</a>
                </pg:last>
                <select name="pagesize" onchange="selectPagesize(this)">
                    <option value="---请选择---" selected="selected">---请选择---</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                </select>
            </td>
        </tr>
    </table>
</pg:pager>