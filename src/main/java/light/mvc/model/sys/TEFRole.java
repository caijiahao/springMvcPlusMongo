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
@Table(name = "role")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFRole extends baseEntity implements java.io.Serializable {

	private String roleName; // 角色名称
	private String description; // 备注
	private Integer isDefault;
	private Set<TEFResource> resources = new HashSet<TEFResource>(0);
	private Set<TEFPersonalInfo> users = new HashSet<TEFPersonalInfo>(0);

	
	public TEFRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TEFRole(String roleName, String description, Integer isDefault) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.isDefault = isDefault;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roleresource", joinColumns = { @JoinColumn(name = "RoleID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ResourceID", nullable = false, updatable = false) })
	@OrderBy("id ASC")
	public Set<TEFResource> getResources() {
		return resources;
	}
	public void setResources(Set<TEFResource> resources) {
		this.resources = resources;
	}
	
	@OneToMany
	@JoinColumn(name="RoleID")
	public Set<TEFPersonalInfo> getUsers() {
		return users;
	}
	public void setUsers(Set<TEFPersonalInfo> users) {
		this.users = users;
	}
	
	
}
