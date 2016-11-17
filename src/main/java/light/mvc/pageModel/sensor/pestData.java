package light.mvc.pageModel.sensor;

import java.util.Date;

public class pestData {
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


}
