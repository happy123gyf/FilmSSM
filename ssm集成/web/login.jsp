<%--
  Created by IntelliJ IDEA.
  User: GYF
  Date: 2020/6/17
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<html>
<head>
    <title>登录页面</title>
</head>
<body>

<form action="${path}/login" method="post" >
    用户名  <input type="text" name="username" > <br>
    密码 <input type="password" name="password" > <br>
    <input type="submit" value="提交">


</form>




</body>
</html>
