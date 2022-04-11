<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>  
<%@ include file="/resources/bootstrap/index.html"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardView</title>
<style type="text/css">
</style>
</head>
<body>
	<form id="actionForm">
		<input type="hidden" name="boardNum" value="${board.boardNum}">
		<input type="hidden" name="boardType" value="${board.boardType}">
		<button type="button" class="btn btn-secondary" onclick="history.back();">목록</button>
		
		<table class="table">
			<tr>
				<th width="150px" align="center">
					Title
				</th>
				<td align="center">
					${board.boardTitle}
				</td>
			</tr>
			<tr>
				<th height="300" align="center">
					Comment
				</th>
				<td align="center">
					${board.boardComment}
				</td>
			</tr>
			<tr>
				<th align="center">
					Writer
				</th>
				<td>
					${board.creator}
				</td>
			</tr>
		</table>
		
		<!-- 수정, 삭제버튼 -->
		<c:if test="${session.userId eq board.creator}">
			<button type="button" class="btn btn-secondary" onclick="location.href='/board/${board.boardType}/${board.boardNum}/boardUpdate.do'">수정</button>
			<button type="button" id="delBtn" class="btn btn-secondary">삭제</button>
		</c:if>
	</form>
	
<script type="text/javascript">
	$j(document).ready(function() {
		
		/* 삭제버튼 클릭시 */
		$j("#delBtn").on("click", function() {
			if(confirm("삭제하시겠습니까?")) {
				
				var $frm = $j("#actionForm :input");
				var param = $frm.serialize();
				
				$j.ajax({
					url : "/board/boardDel.do",
					data : param,
					dataType : "text",
					type : "POST",
					success : function(data, textStatus, jqXHR) {
						// alert(JSON.stringify(data));
						if(data == "success"){
							alert("삭제가 정상적으로 되었습니다.");
							location.href = "/board/boardList.do";
						} else if(data == "failed"){
							alert("삭제에 실패하였습니다.");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("삭제 중 에러가 발생하였습니다.");
					}
				});	// ajax end
			}	// if end
		});	// btn end
	});	// doc end
</script>
</body>
</html>