package light.mvc.pageModel.sys;

import java.util.Date;

public class EFNotice {


    private Long autoID;
    private Integer active; // 状态
    private Date createDate; // 创建时间
    private Long createBy;
    private Date updateDate;
    private Long updateBy;
    private Integer deleted;
    private Date ts;


    private Long type;
    private String typeName;
    private Long metaID;
    private Long personalID;
    private String personalName;
    private String content;
    private Integer isNotice;

    public EFNotice() {
        super();
        // TODO Auto-generated constructor stub
    }

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
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getTS() {
        return ts;
    }

    public void setTS(Date tS) {
        this.ts = tS;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getMetaID() {
        return metaID;
    }

    public void setMetaID(Long metaID) {
        this.metaID = metaID;
    }

    public Long getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Long personalID) {
        this.personalID = personalID;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(Integer isNotice) {
        this.isNotice = isNotice;
    }


}
