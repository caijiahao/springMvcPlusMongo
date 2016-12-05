package light.mvc.model.base;

import javax.persistence.*;
import java.util.Date;

/**
 * 统一定义各表的基本属性的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类而改为实现一个Idable的接口。
 * 
 * createDate
 * createBy
 * updateDate
 * updateBy
 * Active
 * Deleted
 * TS
 * 等公共字段的基本属性名称、数据类型、咧映射及生成策略
 * 
 * 编写日期：2016-8-8
 * 
 * 特别注意：尚未完善
 * 
 * @author yeyaowen
 */
@MappedSuperclass
public abstract class baseEntity {

	protected Long autoID;
	protected Integer active; // 状态
	protected Date createDate; // 创建时间
	protected Long createBy;
	protected Date updateDate;
	protected Long updateBy;
	protected Integer deleted;
	protected Date TS;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAutoID() {
		return autoID;
	}

	public void setAutoID(Long id) {
		this.autoID = id;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
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
		return TS;
	}

	public void setTS(Date tS) {
		TS = tS;
	}
	
}
