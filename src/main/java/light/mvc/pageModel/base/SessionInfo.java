package light.mvc.pageModel.base;

import java.util.List;

public class SessionInfo implements java.io.Serializable {

    private Long id;// 用户personalID
    private String loginname;// 登录名
    private String name;// 姓名
    private String roleType; //用户角色类型，例如管理员分department和final等。

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    private String ip;// 用户IP
    private String sessionId;//服务器传送给客户端cookie的JSESSIONID

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private List<String> resourceList;// 用户可以访问的资源地址列表

    private List<String> resourceAllList;

    public List<String> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<String> resourceList) {
        this.resourceList = resourceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public List<String> getResourceAllList() {
        return resourceAllList;
    }

    public void setResourceAllList(List<String> resourceAllList) {
        this.resourceAllList = resourceAllList;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
