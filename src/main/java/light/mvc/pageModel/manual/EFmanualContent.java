package light.mvc.pageModel.manual;

import light.mvc.pageModel.sys.EFResourceMeta;

import java.util.Date;
import java.util.List;

public class EFmanualContent implements java.io.Serializable {


    private Long autoID;
    private Integer active; // 状态
    private Date createDate; // 创建时间
    private Long createBy;
    private Date updateDate;
    private Long updateBy;
    private Integer Deleted;
    private Date TS;

    private Long manualCategoryID;
    private String categoryCode;
    private String content;
    private String shortContent;
    private String title;
    private String filePath;
    private String attachmentContent;
    private String pageContent; //临时存储网页的内容
    private String keywordList;
    private String imgContent; //预览图字符串信息
    private List<EFResourceMeta> resourceList;

    public Long getAutoID() {
        return autoID;
    }

    public void setAutoID(Long autoID) {
        this.autoID = autoID;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleted() {
        return Deleted;
    }

    public void setDeleted(Integer deleted) {
        Deleted = deleted;
    }

    public Date getTS() {
        return TS;
    }

    public void setTS(Date tS) {
        TS = tS;
    }

    public Long getManualCategoryID() {
        return manualCategoryID;
    }

    public void setManualCategoryID(Long manualCategoryID) {
        this.manualCategoryID = manualCategoryID;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(String attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(String keywordList) {
        this.keywordList = keywordList;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public List<EFResourceMeta> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<EFResourceMeta> resourceList) {
        this.resourceList = resourceList;
    }
}
