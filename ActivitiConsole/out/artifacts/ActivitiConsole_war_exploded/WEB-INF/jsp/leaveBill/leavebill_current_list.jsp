<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/31 0031
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>当前请假流程详情</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty approveinfo}">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                                        <td width="94%" valign="bottom"><span class="STYLE1">显示请假申请的批注信息</span></td>
                                    </tr>
                                </table></td>
                                <td><div align="right"><span class="STYLE1">
			              </span></div></td>
                            </tr>
                        </table></td>
                    </tr>
                </table></td>
            </tr>
            <tr>
                <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
                    <tr>
                        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注人</span></div></td>
                        <td width="75%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注信息</span></div></td>
                    </tr>
                    <c:forEach items="${approveinfo}" var="currentapprove">
                        <tr>
                            <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${currentapprove.name}</div></td>
                            <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${currentapprove.comment}</div></td>
                        </tr>
                    </c:forEach>
                </table></td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td height="24" bgcolor="#F7F7F7"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath }/images/tb.gif" width="14" height="14" /></div></td>
                                        <td width="94%" valign="bottom"><span><b>暂时没有批注信息</b></span></td>
                                    </tr>
                                </table></td>
                            </tr>
                        </table></td>
                    </tr>
                </table></td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>
<a id="back" name="back" href="/showLeaveBills">返回请假流程页面</a>
</body>
</html>
