package light.mvc.pageModel.manual;

import java.util.Date;

public class EFmanualKeyword {


    private Long autoID;
    private Integer active; // 状态
    private Date createDate; // 创建时间
    private Long createBy;
    private Date updateDate;
    private Long updateBy;
    private Integer Deleted;
    private Date TS;

    private Long manualContentID;
    private Long categoryID;
    private String categoryCode;
    private String keyword;

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

    public Long getManualContentID() {
        return manualContentID;
    }

    public void setManualContentID(Long manualContentID) {
        this.manualContentID = manualContentID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
