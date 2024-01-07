<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#list1 .form-control {
	border-color: transparent;
}

#list1 .form-control:focus {
	border-color: transparent;
	box-shadow: none;
}

#list1 .select-input.form-control[readonly]:not([disabled]) {
	background-color: #fbfbfb;
}

.listcontent{
border: 0px !important;

}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</head>
<body>
<h3> ${user_id}님 반갑습니다.</h3>
<section class="vh-100">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col">
        <div class="card" id="list1" style="border-radius: .75rem; background-color: #eff1f2;">
          <div class="card-body py-4 px-4 px-md-5">
            <p class="h1 text-center mt-3 mb-4 pb-3 text-primary">
              <i class="fas fa-check-square me-1"></i>
              <u>todolist</u>
            </p>

            <div class="pb-2">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-row align-items-center">
                  	<form action="${root}todo/addTodo" method="post">
	                    <input type="text" class="form-control form-control-lg" id="exampleFormControlInput1"
	                      placeholder="Add new..." name="todo_content"/>
	                    <input type="date" name="end_time">
	                    <div>
	                      <button type="submit" class="btn btn-primary">등록</button>
	                    </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>

            <hr class="my-4">

            <div class="d-flex justify-content-between align-items-center mb-4 pt-2 pb-3">
            	<div>
            		<h4>총 ?건</h4>
            	</div>
            	<div>
	              <p class="small mb-0 me-2 text-muted">Filter</p>
	              <select class="select">
	                <option value="1">모두</option>
	                <option value="2">진행중</option>
	                <option value="3">완료</option>
	              </select>
	              <button>검색</button>
              	</div>
            </div>

            <div class="col-lg-12 col-md-8 mx-auto d-flex flex-column justify-content-center align-items-center">
		      <div class="table small" style="width: 100%;">
				<table class="table table-striped table-sm">
		          <thead>
					<tr>
						<th class="col-1 course_th" scope="col">구분</th>
						<th class="col-4 course_th" scope="col">할일</th>
						<th class="col-2 course_th" scope="col">시작시간</th>
						<th class="col-2 course_th" scope="col">마감시간</th>
						<th class="col-1 course_th" scope="col">상태</th>
						<th class="col-1 course_th" scope="col">변경</th>
						<th class="col-1 course_th" scope="col">삭제</th>
					</tr>
				 </thead>
		          <tbody>
		          	<c:forEach var="todo" items="${todoList}">
		          		<form:form action="${root}todo/todoList_modify" method="post" modelAttribute="todoModifyBean">
				            <tr>
				              <td class="col-1 center-text listcontent"><form:input path="todo_key" value="${todo.todo_key}"/></td>
				              <td class="col-4 center-text listcontent"><form:input path="todo_content" value="${todo.todo_content}"/></td>
				              <td class="col-2 center-text listcontent"><form:input path="start_time" value="${todo.start_time}"/></td>
				              <td class="col-2 center-text listcontent"><form:input path="end_time" value="${todo.end_time}"/></td>
				              <td class="col-1 center-text listcontent">
				              	<form:select path="todo_status">
				              		<c:choose>
					              		<c:when test="${todo.todo_status == '진행중'}">
					              			<option value="진행중" selected>진행중</option>
					              			<option value="완료">완료</option>
					              		</c:when>
					              		<c:otherwise>
					              			<option value="완료" selected>완료</option>
					              			<option value="진행중">진행중</option>
					              		</c:otherwise>
				              		</c:choose>
				              	</form:select>
				              </td>
				              <td class="col-1 center-text">
				              	<form:button class="btn btn-sm btn-secondary">수정</form:button>
				              </td>
				              <td class="col-1 center-text">
				              	<form:button class="btn btn-sm btn-secondary">삭제</form:button>
				              </td>
				              <!-- Modal -->
				            </tr>
			            </form:form>
			         </c:forEach>
		          </tbody>
		        </table>
		      </div>
	      </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>