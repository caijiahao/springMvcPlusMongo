<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div style="padding: 3px;">
	<div class="table">
		<table class="grid">
			<tr>
				<td>标题</td>
				<td align="center">${manualContent.title}</td>
			</tr>
			<tr>
				<td>所属栏目</td>
				<td align="center">${categoryList}</td>
			</tr>
			<tr>
				<td>关键字</td>
				<td align="center">${keywordList}</td>
			</tr>
			<c:if test="${not empty attachmentList}">
				<tr>
					<td>附件</td>
					<td><c:forEach items="${attachmentList}" var="attachment">
							<div style="display:block;line-height:2em;">
								<a href="${ctx}/front/news/base/downloadAttachment?attachmentID=${attachment.autoID}"
									target="_blank">${attachment.metaDescription}</a>
							</div>
						</c:forEach></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2">
					<div class="table" id="manualContent">${manualContent.content}</div></td>
			</tr>
		</table>
	</div>
</div>