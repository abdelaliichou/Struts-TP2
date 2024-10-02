<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: abdelali
  Date: 13/02/2024
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ICHOU ABDELALI CONNEXION PAGE</h1>
<br/>

<s:actionerror/>

<s:form action="connect">
    <s:textfield name="login"/>
    <s:password name="password"/>
    <s:submit/>
</s:form>

</body>
</html>
