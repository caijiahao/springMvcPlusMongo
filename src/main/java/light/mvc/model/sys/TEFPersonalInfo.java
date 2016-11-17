package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import light.mvc.model.manual.TEFmanualCategory;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */

@Entity
@Table(name = "personalinfo", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFPersonalInfo extends baseEntity implements java.io.Serializable {

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

    private TEFOrganization organization;
    private TEFRole role;

    private Set<TEFmanualCategory> manualCategorys = new HashSet<TEFmanualCategory>(0);

    public TEFPersonalInfo() {
        super();
        // TODO Auto-generated constructor stub
    }


    public TEFPersonalInfo(String realName, String phoneNumber, Integer sex,
                           Integer age, String email, String address, String techType,
                           String description, String techTitle, Integer needPublish, TEFOrganization organization, TEFRole role) {
        super();
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.address = address;
        this.techType = techType;
        this.description = description;
        this.techTitle = techTitle;
        this.needPublish = needPublish;
        this.organization = organization;
        this.role = role;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrganizationID")
    public TEFOrganization getOrganization() {
        return organization;
    }


    public void setOrganization(TEFOrganization organization) {
        this.organization = organization;
    }


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleID")
    public TEFRole getRole() {
        return role;
    }


    public void setRole(TEFRole role) {
        this.role = role;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personalmanual", joinColumns = {@JoinColumn(name = "PersonalID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "ManualCategoryID", nullable = false, updatable = false)})
    @OrderBy("AutoID ASC")
    public Set<TEFmanualCategory> getManualCategorys() {
        return manualCategorys;
    }


    public void setManualCategorys(Set<TEFmanualCategory> manualCategorys) {
        this.manualCategorys = manualCategorys;
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

}
