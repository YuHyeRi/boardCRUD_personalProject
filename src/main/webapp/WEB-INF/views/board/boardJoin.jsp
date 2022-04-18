<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>  
<%@ include file="/resources/bootstrap/index.html"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardJoin</title>
<style type="text/css">
	.table {
		display: flex;
		justify-content: center;
	}
</style>
</head>
<body>
	<form id="joinForm">
		<button type="button" id="listBtn" class="btn btn-secondary" onclick="location.href='/board/boardList.do'">목록</button><br>
				
		<table class="table">
			<tbody>
				<tr>
					<!-- 여러줄 동시 수정 : alt + shift + A -->
					<th style="width: 200px;">아이디</th>
					<td>
						<input type="text" id="userId" name="userId" maxlength="15" placeholder="영문, 숫자만 입력해주세요."  required />
						<td style="width: 200px;"><span id="idCheck"></span></td>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" id="userPw1" name="userPw" maxlength="12" placeholder="6~12자리로 입력" required />
					</td>
					<td></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" id="userPw2" maxlength="12" required />
					</td>
					<td>
						<span id="passO" style="color: blue; font-size: 1em">비밀번호 일치</span>
						<span id="passX" style="color: red; font-size: 1em">비밀번호 불일치</span>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" id="userName" name="userName" maxlength="4" required />
					</td>
					<td></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<select name="userPhone1">
							<c:forEach var="cList" items="${comCodeList}">
								<option value="${cList.codeId}">${cList.codeName}</option>
							</c:forEach>
						</select>
						<!-- input type : number는 maxlength 사용불가 -->
						<input type="number" id="userPhone2" name="userPhone2" maxlength="4" oninput="maxLengthCheck(this)" style="width: 60px" required />
						<input type="number" id="userPhone3" name="userPhone3" maxlength="4" oninput="maxLengthCheck(this)" style="width: 60px" required />
					</td>
					<td></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
						<input type="text" id="userPostNo" name="userPostNo" maxlength="7" oninput="maxLengthCheck(this)" placeholder="6자리 숫자 입력" required />
					</td>
					<td></td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" name="userAddress" />
					</td>
					<td></td>
				</tr>
				<tr>
					<th>회사이름</th>
					<td>
						<input type="text" name="userCompany" />
					</td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<input id="submitBtn" type="button" value="확인" class="btn btn-secondary">
	</form>

<script type="text/javascript">
	$j(document).ready(function() {
		
		// 아이디 중복검사
		$j("#userId").on("change", function() {
			var params = $j("#joinForm").serialize();
				
			$j.ajax({
				url: "/board/idCheckAjax.do",
				type: "POST",
				dataType: "json",
				data: params,
				success: function(res) {
					if(res.cnt > 0){
						$j("#idCheck").html("중복된 아이디");
						$j("#idCheck").css("color", "red");
						// $j("#joinForm")[0].reset();		// 첫번째 폼 내용 리셋
					} else {
						$j("#idCheck").html("사용가능한 아이디");
						$j("#idCheck").css("color", "green");
					}
				},
				error: function(request, status, error) {
					console.log(error);
				}
			}); // ajax end
		}); // btn end
			
		// userId : 영문+숫자
		$j("input[name=userId]").on("keyup", function() {
			var NumEng = /[^A-Za-z|x20^0-9]/gi;			// g:발생할 모든 pattern에 대한 전역검색, i:대소문자구분X
			
			 if(NumEng.test($j("#userId").val())) {
				  alert("영문과 숫자만 입력 가능합니다.");
				  $j("#userId").val("");				// input 값(value) 비우기
				  $j("#userId").focus();
			 	 return false;
			 } else {
				 return true;
			 }
		});
		
		// userName : 한글
		$j("input[name=userName]").on("keyup", function() {
			var kor = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
			
			 if(kor.test($j("#userName").val())) {
				  alert("한글만 입력 가능합니다.");
				  $j("#userName").val("");
				  $j("#userName").focus();
			 	 return false;
			 } else {
				 return true;
			 }
		});
		
		// 우편번호 (3자리마다 하이픈)
		$j("#userPostNo").on("keyup", function() {
			$j(this).val($j(this).val().replace(/[^0-9]/g,"").replace(/([0-9]{3})+([0-9]{3})/, "$1-$2").replace("--","-"));
		});
			
		// 비밀번호 일치
		$j("#passO").hide();
		$j("#passX").hide();
		
		$j("input[type=password]").on("keyup", function() {
				
			var pwd1 = $j("#userPw1").val();
			var pwd2 = $j("#userPw2").val();
				
			if(pwd1 != "" || pwd2 != ""){
				if(pwd1 == pwd2){
					$j("#passO").show();
					$j("#passX").hide();
					$j("#submit").removeAttr("disabled");			// .removeAttr("disabled") : 버튼 비활성화 속성을 제거
				} else {
					$j("#passO").hide();
					$j("#passX").show();
					$j("#submit").attr("disabled", "disabled");		// .attr("disabled", "disabled") : 버튼 비활성화
				}
			}
		});
			
		// 작성 후 확인 버튼 눌렀을 경우
		$j("#submitBtn").on("click", function() {
			if(checkVal("#userId")) {
				alert("아이디를 입력하세요.");
				$j("#userId").focus();
			} else if(checkVal("#userPw1")) {
				alert("비밀번호를 입력하세요.");
				$j("#userPw1").focus();
			} else if(checkVal("#userPw2")) {
				alert("비밀번호 확인을 입력하세요.");
				$j("#userPw2").focus();
			} else if(checkVal("#userName")) {
				alert("이름 입력하세요.");
				$j("#userName").focus();
			} else if(checkVal("#userPhone2")) {
				alert("가운데 연락처를 입력하세요.");
				$j("#userPhone2").focus();
			} else if(checkVal("#userPhone3")) {
				alert("마지막 연락처를 입력하세요.");
				$j("#userPhone3").focus();
			} else if(checkVal("#userPostNo")) {
				alert("우편번호를 입력하세요.");
				$j("#userPostNo").focus();
			} else {
					
				var $frm = $j("#joinForm :input");
				var param = $frm.serialize();
				
				$j.ajax({
					url: "/board/boardJoinAction.do",
					dataType: "json",
					type: "POST",
					data: param,
					success: function(data, textStatus, jqXHR) {
						alert("회원가입이 완료 되었습니다.");
						/* alert("메시지 : " + data.success); */
						location.href = "/board/boardList.do";
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("회원가입이 처리되지 않았습니다.")
					}
				});	// ajax end
			}	// else end
		}); // submit end
	});	// doc end
		
	// 필수입력 값 관련 Func
	function checkVal(sel) {
		if($j.trim($j(sel).val()) == "") {
			return true;
		} else {
			return false;
		};
	};
		
	// number type maxlength Func (+html:maxlength,oninput)
	function maxLengthCheck(object){
		if (object.value.length > object.maxLength) {				// 입력된 값(value)이 maxlength보다 크면
			object.value = object.value.slice(0, object.maxLength);	// slice(start,end) => index0 ~ html에서 지정한 maxlength 까지 자름
		};
	};
		
</script>
</body>
</html>