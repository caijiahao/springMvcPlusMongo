package light.mvc.pageModel.news;

import light.mvc.pageModel.sys.EFResourceMeta;

import java.util.Date;
import java.util.List;

public class EFNews implements java.io.Serializable {

	private Long autoID;
	private Long personalID;
	private String author;
	private String title;
	private String content;
	private String webPath;
	private Date publishDate;
	private Integer readCount;
	private Long status;//17：编辑|18：发布
	private String statusDesc;
	private Long categoryID;
	private String categoryName;
	private String pageContent; //临时存储网页的内容
	private Date searchStartDate;
	private Date searchEndDate;
	private String attachmentContent;
	private Long categoryTypeID;
	private String categoryTypeDesc;
	private String shortTitle;
	
	List<EFResourceMeta> resourceList;
	
	public Long getAutoID() {
		return autoID;
	}
	public void setAutoID(Long autoID) {
		this.autoID = autoID;
	}
	public Long getPersonalID() {
		return personalID;
	}
	public void setPersonalID(Long personalID) {
		this.personalID = personalID;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWebPath() {
		return webPath;
	}
	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPageContent() {
		return pageContent;
	}
	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
	public Date getSearchStartDate() {
		return searchStartDate;
	}
	public void setSearchStartDate(Date searchStartDate) {
		this.searchStartDate = searchStartDate;
	}
	public Date getSearchEndDate() {
		return searchEndDate;
	}
	public void setSearchEndDate(Date searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	public String getAttachmentContent() {
		return attachmentContent;
	}
	public void setAttachmentContent(String attachmentContent) {
		this.attachmentContent = attachmentContent;
	}
	
	public Long getCategoryTypeID() {
		return categoryTypeID;
	}
	public void setCategoryTypeID(Long categoryTypeID) {
		this.categoryTypeID = categoryTypeID;
	}
	public String getCategoryTypeDesc() {
		return categoryTypeDesc;
	}
	public void setCategoryTypeDesc(String categoryTypeDesc) {
		this.categoryTypeDesc = categoryTypeDesc;
	}
	
	public String getShortTitle(){
		return shortTitle;
	}
	public void setShortTitle(String shortTitle){
		this.shortTitle = shortTitle;
	}
	public List<EFResourceMeta> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<EFResourceMeta> resourceList) {
		this.resourceList = resourceList;
	}
	
	
}
