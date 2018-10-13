<%--
  Created by IntelliJ IDEA.
  User: scw
  Date: 2018/1/31 0031
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改请假信息</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/updateleavebill" method="post">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <div align="left" class="STYLE21">
                    <input type="hidden" name="id" value="${currentleavebill.id}">
                    请假天数:<input type="text" name="days" value="${currentleavebill.days}"><br/>
                    请假原因:<input type="text" name="content" value="${currentleavebill.content}"><br/>
                    备&emsp;&emsp;注:<input type="text" name="remark" value="${currentleavebill.remark}"><br/>
                    <input type="submit" value="修改" class="button_ok"/>
                </div>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
