<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {
		var content = $('#pageContent').val();
		var html = decodeURIComponent(content);
		$('#newsContent').append(html);

	});
</script>

<div style="padding: 3px;">
	<div class="table">
		<table class="grid">
			<tr>
				<td colspan="3" align="center">${news.title}</td>
			</tr>

			<tr>
				<td>发布者：${news.author }</td>
				<td>发布时间：${news.publishDate }</td>
				<td>所属栏目：${category.categoryName }</td>
			</tr>
			<c:if test="${!empty attachmentList}">
					<tr>
						<td>附件</td>
						<td colspan="2">
							<c:forEach items="${attachmentList}" var="attachment">
								<div style="display:block;line-height:2em;">
									<a href="${ctx}/front/news/base/downloadAttachment?attachmentID=${attachment.autoID}" target="_blank">${attachment.metaDescription}</a>
								</div>
							</c:forEach>
						</td>
					</tr>
			</c:if>		
			<tr>
				<td colspan="3">
					<div class="table" id="newsContent"></div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" value="${news.pageContent }" id="pageContent"/>
</div>