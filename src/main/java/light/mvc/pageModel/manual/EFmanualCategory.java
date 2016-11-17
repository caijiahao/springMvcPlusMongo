package light.mvc.pageModel.manual;

import java.util.Date;

public class EFmanualCategory implements java.io.Serializable {

    protected Long id;
    protected Long autoID;
    protected Integer active; // 状态
    protected Date createDate; // 创建时间
    protected Long createBy;
    protected Date updateDate;
    protected Long updateBy;
    protected Integer Deleted;
    protected Date TS;

    private String categoryName;
    private String categoryCode;
    private String categoryDescription;

    private Integer isParent;
    private String childIds;
    private String childNames;

    private Long parentID;

    private String expertIds;
    private String expertNames;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public String getExpertIds() {
        return expertIds;
    }

    public void setExpertIds(String expertIds) {
        this.expertIds = expertIds;
    }

    public String getExpertNames() {
        return expertNames;
    }

    public void setExpertNames(String expertNames) {
        this.expertNames = expertNames;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public String getChildIds() {
        return childIds;
    }

    public void setChildIds(String childIds) {
        this.childIds = childIds;
    }

    public String getChildNames() {
        return childNames;
    }

    public void setChildNames(String childNames) {
        this.childNames = childNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
