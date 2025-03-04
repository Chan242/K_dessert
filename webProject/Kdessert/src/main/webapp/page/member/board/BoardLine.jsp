<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	#boardLine a{
		border-bottom: 1px solid lightgray;

		display: flex;/* a 태그 안에 flex 적용 */
		justify-content: space-between;
		align-items: center; /* 수직 정렬 */
		padding-top: 10px;
		padding-bottom: 10px;
		
		
	}
	
	.writer{
		
		margin-left: 20px;
	}
</style>

<div id="boardLine">
	<a>
		<span style="margin-left:40px;">타이틀</span>
		<span style="margin-right:40px;">
			<span class="writer">작성자</span>
			<span class="writer">작성일</span>
			<span class="writer">조회수</span>
		</span>
	</a>

</div>