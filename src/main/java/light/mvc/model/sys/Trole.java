package light.mvc.model.sys;

import light.mvc.model.base.IdEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Trole extends IdEntity implements java.io.Serializable {

    private String name; // 角色名称
    private Integer seq; // 排序号
    private Integer isdefault; // 是否默认
    private String description; // 备注
    private Set<Tresource> resources = new HashSet<Tresource>(0);
    private Set<Tuser> users = new HashSet<Tuser>(0);

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_resource", joinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "resource_id", nullable = false, updatable = false)})
    @OrderBy("id ASC")
    public Set<Tresource> getResources() {
        return resources;
    }

    public void setResources(Set<Tresource> resources) {
        this.resources = resources;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
    @OrderBy("id ASC")
    public Set<Tuser> getUsers() {
        return users;
    }

    public void setUsers(Set<Tuser> users) {
        this.users = users;
    }

}
