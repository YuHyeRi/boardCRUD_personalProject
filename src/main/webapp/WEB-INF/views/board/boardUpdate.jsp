<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ include file="/resources/bootstrap/index.html"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardUpdate</title>
</head>
<body>
	<form class="updateForm">
		<!-- name=키, value=값 이기때문에 맵핑이 되려면 무조건 저 두개는 가지고 있어야함 -->
		<input type="hidden" name="boardNum" value="${board.boardNum}" />
		
		<table class="table">
			<tr>
				<th>
					Title
				</th>
				<td>
					<input name="boardTitle" type="text" size="98" value="${board.boardTitle}"/>
				</td>
			</tr>
			<tr>
				<th>
					Comment
				</th>
				<td>
					<textarea name="boardComment" rows="20" cols="100">${board.boardComment}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					Writer
				</th>
				<!-- 수정필요!!!! -->
				<td>
					${board.creator}
				</td>
			</tr>
		</table>
	
		<button type="button" class="btn btn-secondary" onclick="history.back();">취소</button>
		<input id="submit" type="button" value="수정" class="btn btn-secondary" />
	</form>
	
<script type="text/javascript">
	$j(document).ready(function(){
		
		$j("#submit").on("click", function() {
			
			var $frm = $j('.updateForm :input');
			var param = $frm.serialize();
			
			$j.ajax({
				url: "/board/boardUpdateAction.do",
				dataType: "json",
				type: "POST",
				data: param,
				success: function(data, textStatus, jqXHR) {
					alert("수정에 성공하였습니다.");
					/* alert("메시지 : " + data.success); */
					location.href = "/board/boardList.do";
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("수정에 실패하였습니다.");
				}
			});	// ajax end
		});	// btn end
	});	// doc end
</script>
</body>
</html>