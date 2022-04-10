<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ include file="/resources/bootstrap/index.html"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardLogin</title>
<style>
	.table-light {
		margin: auto;
		margin-top: 300px;
	}
</style>
</head>
<body>
	<!-- 로그인 실패 :: 아이디가 맞지 않을 경우 -->
	<c:if test="${result eq 'userId'}">
		<script type="text/javascript">
			alert("존재하지 않는 아이디 입니다.")
		</script>
	</c:if>
	
	<!-- 로그인 실패 :: 비밀번호가 맞지 않을 경우 -->
	<c:if test="${result eq 'userPw'}">
		<script type="text/javascript">
			alert("비밀번호가 틀렸습니다.")
		</script>
	</c:if>
	
	<form action="/board/boardLoginAction.do" id="form" onsubmit="return checks()">
		<table class="table-light">
			<tr>
				<td>
					<table border=1>
						<tr>
							<td align="center" width="100px">
								아이디
							</td>
							<td>
								<input type="text" maxlength="15" name="userId" id="id">
							</td>
						</tr>
						<tr>
							<td align="center">
								비밀번호
							</td>
							<td>
								<input type="password" maxlength="15" name="userPw" id="pw">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td>
					<a href="/board/boardJoin.do">
						join
					</a>
					<a id="submit">
						login
					</a>
				</td>
			</tr>
		</table>
	</form>
	
<script type="text/javascript">
	$j("#submit").click(function() {
		$j("#form").submit();
	});
		
	/* 비밀번호 입력후 엔터시 로그인버튼 눌리게 */
	$j("#pw").on("keypress", function(){
		if(event.keyCode == 13){
			$j("#submit").click();
			return false;
		}
	});
	
	// 아이디에 keyup 될 경우, idCheck 함수 실행
	$j(function() {
		$j("#id").on("keyup", idCheck);
	});
	
	// idCheck Func
	function idCheck() {
		// 아이디 : 영문 + 숫자 정규식
		var getIdCheck = RegExp(/^[a-zA-Z0-9]*$/);
		
		if (!getIdCheck.test($j("#id").val())) {
			// user가 입력되는 값만 입력 할 수 있도록
			$j("#id").val('');
			$j("#id").focus();
			return false;
		}
		return true;
	}
	
	// checks Func
	function checks() {
		// 아이디 : 영문 + 숫자 정규식
		var getIdCheck = RegExp(/^[a-zA-Z0-9]*$/);
		
		// 비밀번호 : 영문 + 숫자 6~12자 정규식
		var getPwCheck = RegExp(/^[a-zA-Z0-9]{6,12}$/);
		
		if ($j("#id").val() == "") {
			alert("아이디를 입력해주세요.");
			$j("#id").focus();
			return false;
		}
			
		if ($j("#pw").val() == "") {
			alert("패스워드를 입력해주세요.");
			$j("#pw").focus();
			return false;
		}
		return true;
	}
</script>
</body>
</html>