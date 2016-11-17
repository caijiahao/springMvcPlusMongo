package light.mvc.pageModel.sys;

import java.util.Date;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */
public class User implements java.io.Serializable {
    //autoID为personalinfo表的AutoID
    private Long autoID;
    //loginAutoID为loginuser表的AutoID
    private Long loginAutoID;

    protected Integer active; // 状态
    protected Date createDate; // 创建时间
    protected Long createBy;
    protected Date updateDate;
    protected Long updateBy;
    protected Integer Deleted;
    protected Date TS;


    private String loginName; // 登录名
    private String password; // 密码
    private Date lastLoginTime; // 上次登录时间
    private Integer states; // 状态
    private Integer isdefault;

    private String realName;
    private String phoneNumber;
    private Integer sex;
    private Integer age;
    private String email;
    private String address;

    private String techType;
    private String description;
    private String techTitle;
    private Integer needPublish;

    private Long organizationId;
    private String organizationName;

    private Long roleIds;
    private String roleNames;

    private String manualIDs;
    private String manualNames;

    private String searchKey;
    private String searchValue;

    private boolean auditor;

    private String imagePath;    //用户图像路径

    public User() {
        super();
    }


    public Long getAutoID() {
        return autoID;
    }


    public void setAutoID(Long autoID) {
        this.autoID = autoID;
    }

    public Long getLoginAutoID() {
        return loginAutoID;
    }


    public void setLoginAutoID(Long loginAutoID) {
        this.loginAutoID = loginAutoID;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }


    public String getManualIDs() {
        return manualIDs;
    }


    public void setManualIDs(String manualIDs) {
        this.manualIDs = manualIDs;
    }


    public String getManualNames() {
        return manualNames;
    }


    public void setManualNames(String manualNames) {
        this.manualNames = manualNames;
    }

    public String getTechTitle() {
        return techTitle;
    }

    public void setTechTitle(String techTitle) {
        this.techTitle = techTitle;
    }

    public Integer getNeedPublish() {
        return needPublish;
    }

    public void setNeedPublish(Integer needPublish) {
        this.needPublish = needPublish;
    }


    public String getSearchKey() {
        return searchKey;
    }


    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }


    public String getSearchValue() {
        return searchValue;
    }


    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }


    public boolean isAuditor() {
        return auditor;
    }


    public void setAuditor(boolean auditor) {
        this.auditor = auditor;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}