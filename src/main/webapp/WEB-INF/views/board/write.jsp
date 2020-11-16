<%--
  Created by IntelliJ IDEA.
  User: bitcamp
  Date: 2020-11-12(012)
  Time: 오전 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>미니 프로젝트</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>


<!-- 상단 메뉴 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card shadow">
                <div class="card-body">
                    <form action="${root}board/read" method="get">
                        <div class="form-group">
                            <label for="board_subject">제목</label>
                            <input type="text" id="board_subject" name="board_subject" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="board_content">내용</label>
                            <textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize:none"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="board_file">첨부 이미지</label>
                            <input type="file" id="board_file" name="board_file" class="form-control" accept="image/*"/>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <button type="submit" class="btn btn-primary">작성하기</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>
