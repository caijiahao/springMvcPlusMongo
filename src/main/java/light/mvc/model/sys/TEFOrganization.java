package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */

@Entity
@Table(name = "organization", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFOrganization  extends baseEntity implements java.io.Serializable{

	private String departmentName;
	private String address;
	private String departmentCode;
	private String icon;
	private Integer seq;
	private TEFOrganization organization;
	private Set<TEFOrganization> organizations = new HashSet<TEFOrganization>(0);

	public TEFOrganization() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public TEFOrganization(String departmentName, String address,
			String departmentCode, String icon, Integer seq,
			TEFOrganization organization, Set<TEFOrganization> organizations) {
		super();
		this.departmentName = departmentName;
		this.address = address;
		this.departmentCode = departmentCode;
		this.icon = icon;
		this.seq = seq;
		this.organization = organization;
		this.organizations = organizations;
	}


	@NotBlank
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
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


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentID")
	public TEFOrganization getOrganization() {
		return organization;
	}



	public void setOrganization(TEFOrganization organization) {
		this.organization = organization;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<TEFOrganization> getOrganizations() {
		return organizations;
	}



	public void setOrganizations(Set<TEFOrganization> organizations) {
		this.organizations = organizations;
	}
	

	
	
	
}
