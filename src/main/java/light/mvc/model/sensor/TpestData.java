package light.mvc.model.sensor;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pestdata")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TpestData extends baseEntity implements java.io.Serializable {

    private String name;
    private Long type;
    private Long monitoringNodeId;
    private Long sensorId;
    private Long imgId;
    private Integer count;
    private Integer realCount;
    private String description;


    public TpestData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TpestData(String name, Long type, Long monitoringNodeId,
                     Long sensorId, Long imgId, Integer count, Integer realCount,
                     String description) {
        super();
        this.name = name;
        this.type = type;
        this.monitoringNodeId = monitoringNodeId;
        this.sensorId = sensorId;
        this.imgId = imgId;
        this.count = count;
        this.realCount = realCount;
        this.description = description;
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
