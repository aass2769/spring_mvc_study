<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<section class="vh-100">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col">
        <div class="card" id="list1" style="border-radius: .75rem; background-color: #eff1f2;">
          <div class="card-body py-4 px-4 px-md-5">

            <p class="h1 text-center mt-3 mb-4 pb-3 text-primary">
              <i class="fas fa-check-square me-1"></i>
              <u>혬 todolist</u>
            </p>

            <div class="pb-2">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-row align-items-center">
                    <input type="text" class="form-control form-control-lg" id="exampleFormControlInput1"
                      placeholder="Add new...">
                    <input type="date">
                    <div>
                      <button type="button" class="btn btn-primary">Add</button>
                    </div>
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
	                <option value="2">진행중인</option>
	                <option value="3">완료된</option>
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
						<th class="col-5 course_th" scope="col">할일</th>
						<th class="col-2 course_th" scope="col">시작시간</th>
						<th class="col-2 course_th" scope="col">마감시간</th>
						<th class="col-1 course_th" scope="col">변경</th>
						<th class="col-1 course_th" scope="col">삭제</th>
					</tr>
				 </thead>
		          <tbody>
			            <tr>
			              <td class="col-1 center-text listcontent"><input readonly="true" type="text" value="1"></td>
			              <td class="col-5 center-text listcontent"><input type="text" value="공부"></td>
			              <td class="col-2 center-text listcontent"><input type="text" value="2023-11-14"></td>
			              <td class="col-2 center-text listcontent"><input type="text" value="2023-11-20"></td>
			              <td class="col-1 center-text">
			              	<button type="button" class="btn btn-sm btn-secondary" onclick="modify()">변경</button>
			              </td>
			              <td class="col-1 center-text">
			              	<button type="button" class="btn btn-sm btn-secondary">삭제</button>
			              </td>
			              <!-- Modal -->
			            </tr>
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

   <div id="container">
  <p id="myParagraph">이것은 단락입니다.</p>
  <button id="myButton" type="button" onclick="changeButtonType()">변경</button>
</div>

<script>
  function changeToInput() {
    // <p> 태그 요소 가져오기
    var paragraph = document.getElementById("myParagraph");

    // 새로운 <input> 태그 생성
    var inputElement = document.createElement("input");

    // 기존 <p> 태그의 속성 복사
    inputElement.setAttribute("id", paragraph.id);
    inputElement.setAttribute("value", paragraph.textContent);

    // <p> 태그를 <input> 태그로 교체
    paragraph.parentNode.replaceChild(inputElement, paragraph);

    // 취소 버튼 생성
    var cancelButton = document.createElement("button");
    cancelButton.textContent = "취소";
    cancelButton.onclick = function() {
      // 취소 버튼을 누르면 다시 <p> 태그로 변경
      inputElement.parentNode.replaceChild(paragraph, inputElement);
      cancelButton.parentNode.removeChild(cancelButton);
      // 변경된 inputElement의 타입을 'button'으로 설정
      document.getElementById("myButton").type = 'button';
    };

    // 취소 버튼을 컨테이너에 추가
    document.getElementById("container").appendChild(cancelButton);

    // 변경된 inputElement의 타입을 'submit'으로 설정
    document.getElementById("myButton").type = 'submit';
  }

  function changeButtonType() {
    changeToInput(); // 버튼의 타입 변경은 changeToInput 함수 내에서 처리
  }
</script>
</body>
</html>