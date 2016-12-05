package light.mvc.pageModel.sys;

import java.util.Date;

public class EFResourceMeta  implements java.io.Serializable{
	
	private Long autoID;
	private Long type;
	private Long metaID;
	private String typeName;
	private String metaPath;
	private String thumbMetaPath;
	private String metaDescription;
	private Date createDate;
	private Date updateDate;
	private String createDateDesc;
	private Integer count;
	
	private String address;
	private String description;

	public Long getAutoID() {
		return autoID;
	}
	
	public void setAutoID(Long autoID) {
		this.autoID = autoID;
	}
	
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	public Long getMetaID() {
		return metaID;
	}

	public void setMetaID(Long metaID) {
		this.metaID = metaID;
	}

	public String getMetaPath() {
		return metaPath;
	}

	public void setMetaPath(String metaPath) {
		this.metaPath = metaPath;
	}
	
	public String getThumbMetaPath() {
		return thumbMetaPath;
	}

	public void setThumbMetaPath(String thumbMetaPath) {
		this.thumbMetaPath = thumbMetaPath;
	}
	
	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public String getCreateDateDesc(){
		return createDateDesc;
	}
	
	public void setCreateDateDesc(String createDateDesc){
		this.createDateDesc = createDateDesc;
	}
	
	public Integer getCount(){
		return count;
	}
	
	public void setCount(Integer count){
		this.count = count;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
