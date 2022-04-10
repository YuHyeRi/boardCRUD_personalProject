<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>  
<%@ include file="/resources/bootstrap/index.html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardWrite</title>
<style type="text/css">
	#boardType {
		margin: auto;
		width: 100px;
	}
</style>
</head>
<body>
	<form class="boardWrite">
		<table class="table">
			<tr>
				<th>
					Type
				</th>
				<td>
					<select id="boardType" name="boardType" class="form-select">
						<c:forEach var="data" items="${list}">
							<option value="${data.codeId}">${data.codeName}</option>						
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					Title
				</th>
				<td >
					<input name="boardTitle" type="text" size="98" value="${board.boardTitle}"> 
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
					<c:if test="${session ne null}">
						${session.userId}
						<input name="creator" type="hidden" value="${session.userId}"> 
					</c:if>
				</td>
			</tr>
		</table>
		
		<button type="button" class="btn btn-secondary" onclick="history.back();">취소</button>
		<input id="submit" type="button" value="작성" class="btn btn-secondary">
	</form>	
	
<script type="text/javascript">
	$j(document).ready(function(){
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성에 성공하였습니다.");
					/* alert("메세지:"+data.success);	// Y or N */
					location.href="/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("작성에 실패하였습니다.");
			    }
			});	// ajax end
		});	// btn end
	});	// doc end
</script>
</body>
</html>