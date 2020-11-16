<%--
  Created by IntelliJ IDEA.
  User: bitcamp
  Date: 2020-11-12(012)
  Time: 오전 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<script>
    alert("로그아웃 되었습니다.")
    location.href = '${root}main'
</script>
