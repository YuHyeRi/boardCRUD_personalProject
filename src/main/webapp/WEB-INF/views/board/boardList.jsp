<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<%@ include file="/resources/bootstrap/index.html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardList</title>
<style>
	.input-group, .input-group-text {
		display: inline-block; 
		width: 100%;
		height: 100%;
		margin:0 auto; 
	}
	
	#pagingWrap {
		display: flex;
		justify-content: center;
		cursor: pointer;
	}
</style>
</head>
<body>
	<h6><mark>🟣 총 게시글 수 : ${totalCnt}</mark></h6><br>

	<!-- login, logout, join button -->
	<!-- 로그인 안했을 때 -->
	<c:if test = "${session eq null}">
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardLogin.do'">로그인</button>
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardJoin.do'">회원가입</button>
	</c:if>
			
	<!-- 로그인 했을 때 -->
	<c:if test = "${session ne null}">
		<!-- <script type="text/javascript">
			alert("로그인 정상작동");
		</script> -->
		<b>${session.userId}님 환영합니다 :)</b><br>
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardWrite.do'">글쓰기</button>
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardLogout.do'; logoutBtn();">로그아웃</button>
	</c:if>
	
	<!-- list table -->
	<table class="table table-hover" align="center">
		<thead>
			<tr>
				<td width="100" align="center">
					Type
				</td>
				<td width="100" align="center">
					No
				</td>
				<td width="300" align="center">
					Title
				</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${boardList}">
			<tr>
				<td align="center">
					<!-- 컬렉션에 있는 property를 써주고 써준다 -->
					${list.comCodeVo.codeName}	
				</td>
				<td align="center">
					${list.boardNum}
				</td>
				<td align="center">
					<a href="/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
				</td>
			</tr>	
			</c:forEach>
		</tbody>
	</table>

	<!-- checkbox search -->
	<form action="/board/boardList.do" method="post">
		<div class="input-group">
			<div class="input-group-text">
				<input type="checkbox" id="checkAll" value="all" />전체
				<c:forEach var="data" items="${list}">
					<input type="checkbox" name="checkNo" value="${data.codeId}" />${data.codeName}
				</c:forEach>
				<button type="submit" class="btn btn-secondary">조회</button>
			</div>
		</div>
	</form>
	
	<!-- Paging -->
	<div id="pagingWrap">
		<nav aria-label="...">
			<ul class="pagination">
				<c:choose>
					<c:when test="${page eq 1}">
						 <li class="page-item disabled">
						 	<a class="page-link"><span page="1">Previous</span></a>
						 </li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link"><span page="${page - 1}">Previous</span></a>
						</li>
					</c:otherwise>
				</c:choose>
							
				<c:forEach var="i" begin="${pb.startPcount}" end="${pb.endPcount}" step="1">
				<c:choose>
					<c:when test="${page eq i}">
						<li class="page-item" aria-current="page">
							<a class="page-link"><span page="${page - 1}"><span page="${i}"><b>${i}</b></span></a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link"><span page="${page - 1}"><span page="${i}">${i}</span></a>
						</li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
							
				<c:choose>
					<c:when test="${page eq pb.maxPcount}">
						<li class="page-item">
							<a class="page-link"><span page="${page - 1}"><span page="${pb.maxPcount}">Next</span></a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link"><span page="${page - 1}"><span page="${page + 1}">Next</span></a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
	
<script type="text/javascript">
	$j(document).ready(function() {
		
		/* $j("#checkAll").on("click", function() {
			if($j("#checkAll").prop("checked")){					// 만약 전체 체크박스가 체크된 상태이면
				$j("input[type=checkbox]").prop("checked", true);	// 모든 input type이 checkbox에 체크한다 
			} else {					
				$j("input[type=checkbox]").prop("checked", false);
			}
		}); */
		
		$j("#checkAll").on("click", function() {
			if($j("#checkAll").is(":checked")) {
				$j("input[name=checkNo]").prop("checked", true);
			} else {
				$j("input[name=checkNo]").prop("checked", false);
			}
		});
		
		$j("input[name=checkNo]").click(function() {
			var total = $j("input[name=checkNo]").length;				// checkNo 전체 갯수
			var checked = $j("input[name=checkNo]:checked").length;		// checkNo에서 체크된 항목의 갯수
			
			if(total != checked) {							// checkNo 항목 모두가 체크되지 않았다면
				$j("#checkAll").prop("checked", false);		// 전체 항목의 체크박스는 체크되지 않는다
			} else {										// checkNo 항목 모두가 체크되었다면
				$j("#checkAll").prop("checked", true);		// 전체 항목의 체크박스는 체크된다.
			}
		})
	});	// doc end
		
	// 로그아웃 알림
	function logoutBtn() {
		alert("로그아웃 되었습니다.");
	}
</script>
</body>
</html>