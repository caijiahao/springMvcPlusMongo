package light.mvc.pageModel.sys;

import java.util.Date;

public class Resource implements java.io.Serializable {

    private Long pid;
    private String pname;

    private Long id;
    private Date createDate; // 创建时间
    private String name; // 名称
    private String url; // 菜单路径
    private String description; // 描述
    private String iconCls; // 图标
    private Integer seq; // 排序号
    private Integer resourcetype; // 资源类型, 0菜单 1功能
    private Integer cstate; // 状态 0启用 1停用
    private String icon;

    public Resource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Date getCreatedatetime() {
        return createDate;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createDate = createdatetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(Integer resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}