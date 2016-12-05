package light.mvc.pageModel.sensor;

import java.util.Date;

public class pestAndSensorDate {
	protected Long autoID;
	protected Integer active; // 状态
	protected Date createDate; // 创建时间
	protected Long createBy;
	protected Date updateDate;
	protected Long updateBy;
	protected Integer deleted;
	protected Date TS;
	private String name;
	private Long type;
	private Long monitoringNodeId;
	private Long sensorId;
	private Long imgId;
	private Integer count;
	private Integer realCount;
	private String description;
	
	private Double sensor1;
	private Double sensor2;
	private Double sensor3;
	private Double sensor4;
	private Double sensor5;
	private Double sensor6;
	
	public Long getAutoID() {
		return autoID;
	}
	public void setAutoID(Long autoID) {
		this.autoID = autoID;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Long getMonitoringNodeId() {
		return monitoringNodeId;
	}
	public void setMonitoringNodeId(Long monitoringNodeId) {
		this.monitoringNodeId = monitoringNodeId;
	}
	public Long getSensorId() {
		return sensorId;
	}
	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getRealCount() {
		return realCount;
	}
	public void setRealCount(Integer realCount) {
		this.realCount = realCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getSensor1() {
		return sensor1;
	}
	public void setSensor1(Double sensor1) {
		this.sensor1 = sensor1;
	}
	public Double getSensor2() {
		return sensor2;
	}
	public void setSensor2(Double sensor2) {
		this.sensor2 = sensor2;
	}
	public Double getSensor3() {
		return sensor3;
	}
	public void setSensor3(Double sensor3) {
		this.sensor3 = sensor3;
	}
	public Double getSensor4() {
		return sensor4;
	}
	public void setSensor4(Double sensor4) {
		this.sensor4 = sensor4;
	}
	public Double getSensor5() {
		return sensor5;
	}
	public void setSensor5(Double sensor5) {
		this.sensor5 = sensor5;
	}
	public Double getSensor6() {
		return sensor6;
	}
	public void setSensor6(Double sensor6) {
		this.sensor6 = sensor6;
	}
	
	
}
