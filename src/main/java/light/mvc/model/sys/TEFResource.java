package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */

@Entity
@Table(name = "resource")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFResource extends baseEntity implements java.io.Serializable {


    private String resourceName; // 名称
    private String url; // 菜单路径
    private String description; // 描述
    private String icon; // 图标
    private Integer seq; // 排序号

    private Integer resourceType; // 资源类型, 0菜单 1功能
    private TEFResource parentResource; // 父级

    private Set<TEFRole> roles = new HashSet<TEFRole>(0);
    private Set<TEFResource> resources = new HashSet<TEFResource>(0);

    public TEFResource() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TEFResource(String resourceName, String url, String description,
                       String icon, Integer seq, Integer resourceType,
                       TEFResource parentResource) {
        super();
        this.resourceName = resourceName;
        this.url = url;
        this.description = description;
        this.icon = icon;
        this.seq = seq;
        this.resourceType = resourceType;
        this.parentResource = parentResource;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ParentID")
    public TEFResource getParentResource() {
        return parentResource;
    }

    public void setParentResource(TEFResource parentResource) {
        this.parentResource = parentResource;
    }

    // @yeyaowen problem
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roleresource", joinColumns = {@JoinColumn(name = "ResourceID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "RoleID", nullable = false, updatable = false)})
    @OrderBy("id ASC")
    public Set<TEFRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<TEFRole> roles) {
        this.roles = roles;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentResource")
    public Set<TEFResource> getResources() {
        return resources;
    }

    public void setResources(Set<TEFResource> resources) {
        this.resources = resources;
    }


}
