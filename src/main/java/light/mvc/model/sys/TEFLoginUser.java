package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */

@Entity
@Table(name = "loginuser")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFLoginUser extends baseEntity implements java.io.Serializable {

    private String loginName; // 登录名
    private String password; // 密码
    private Date lastLoginTime; // 创建时间

    private Integer status; // 状态

    private TEFPersonalInfo personalInfo;

    public TEFLoginUser() {
        super();
        // TODO Auto-generated constructor stub
    }


    public TEFLoginUser(String loginName, String password, Date lastLoginTime,
                        Integer status, TEFPersonalInfo personalInfo) {
        super();
        this.loginName = loginName;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.status = status;
        this.personalInfo = personalInfo;
    }


    @NotBlank
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


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer states) {
        this.status = states;
    }


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonalID")
    public TEFPersonalInfo getPersonalInfo() {
        return personalInfo;
    }


    public void setPersonalInfo(TEFPersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }


}