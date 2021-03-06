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
	<h6><mark>๐ฃ ์ด ๊ฒ์๊ธ ์ : ${totalCnt}</mark></h6><br>

	<!-- ๋ก๊ทธ์ธ ์ํ์ ๋ -->
	<c:if test = "${session eq null}">
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardLogin.do'">๋ก๊ทธ์ธ</button>
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardJoin.do'">ํ์๊ฐ์</button>
	</c:if>
			
	<!-- ๋ก๊ทธ์ธ ํ์ ๋ -->
	<c:if test = "${session ne null}">
		<b>${session.userId}๋ ํ์ํฉ๋๋ค.</b><br>
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardWrite.do'">๊ธ์ฐ๊ธฐ</button>
		<button type="submit" class="btn btn-secondary" onclick="location.href='/board/boardLogout.do'; logoutBtn();">๋ก๊ทธ์์</button>
	</c:if>
	
	<table class="table table-hover" align="center">
		<thead>
			<tr>
				<th width="100" align="center">
					Type
				</th>
				<th width="100" align="center">
					No
				</th>
				<th width="300" align="center">
					Title
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${boardList}">
			<tr>
				<td align="center">
					<!-- ์ปฌ๋ ์์ ์๋ property๋ฅผ ์จ์ฃผ๊ณ  ์จ์ค๋ค -->
					${list.comCodeVo.codeName}
				</td>
				<td align="center">
					${list.boardNum}
				</td>
				<td align="center">
					<a href="/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageVo.pageNo}">${list.boardTitle}</a>
				</td>
			</tr>	
			</c:forEach>
		</tbody>
	</table>

	<form action="/board/boardList.do" method="post">
		<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
		<div class="input-group">
			<div class="input-group-text">
				<input type="checkbox" id="checkAll" value="all" />์ ์ฒด
				<c:forEach var="data" items="${list}">
					<input type="checkbox" name="checkNo" value="${data.codeId}" />${data.codeName}
				</c:forEach>
				<button type="submit" class="btn btn-secondary">์กฐํ</button>
			</div>
		</div>
	</form>
	
	<div id="pagingWrap">
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<li class="page-item">
				    <a class="page-link" href="/board/boardList.do?pageNo=1" aria-label="Previous">
				   		<span aria-hidden="true">&laquo;</span>
				    </a>
			    </li>
			
				<c:forEach var="i" begin="${pb.startPcount}" end="${pb.endPcount}" step="1">
				<c:choose>
					<c:when test="${pageNo eq i}">
						<li class="page-item" aria-current="page">
							<a class="page-link" href="/board/boardList.do?pageNo=${i}">
								${i}
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link" href="/board/boardList.do?pageNo=${i}">
								${i}
							</a>
						</li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
					
				<li class="page-item">
				    <a class="page-link" href="/board/boardList.do?pageNo=${pb.maxPcount}" aria-label="Next">
				    	<span aria-hidden="true">
				    		&raquo;
				    	</span>
				    </a>
			    </li>
			</ul>
		</nav>
	</div>
	
<script type="text/javascript">
	$j(document).ready(function() {
		
		$j("#checkAll").on("click", function() {
			if($j("#checkAll").is(":checked")) {
				$j("input[name=checkNo]").prop("checked", true);
			} else {
				$j("input[name=checkNo]").prop("checked", false);
			}
		});
		
		$j("input[name=checkNo]").click(function() {
			var total = $j("input[name=checkNo]").length;				// checkNo ์ ์ฒด ๊ฐฏ์
			var checked = $j("input[name=checkNo]:checked").length;		// checkNo์์ ์ฒดํฌ๋ ํญ๋ชฉ์ ๊ฐฏ์
			
			if(total != checked) {							// checkNo ํญ๋ชฉ ๋ชจ๋๊ฐ ์ฒดํฌ๋์ง ์์๋ค๋ฉด
				$j("#checkAll").prop("checked", false);		// ์ ์ฒด ํญ๋ชฉ์ ์ฒดํฌ๋ฐ์ค๋ ์ฒดํฌ๋์ง ์๋๋ค
			} else {										// checkNo ํญ๋ชฉ ๋ชจ๋๊ฐ ์ฒดํฌ๋์๋ค๋ฉด
				$j("#checkAll").prop("checked", true);		// ์ ์ฒด ํญ๋ชฉ์ ์ฒดํฌ๋ฐ์ค๋ ์ฒดํฌ๋๋ค.
			}
		})
	});	// doc end
		
	// ๋ก๊ทธ์์ ์๋ฆผ
	function logoutBtn() {
		alert("๋ก๊ทธ์์ ๋์์ต๋๋ค.");
	};
	
</script>
</body>
</html>