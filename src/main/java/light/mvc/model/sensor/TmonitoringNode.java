package light.mvc.model.sensor;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "monitoringnode")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TmonitoringNode extends baseEntity implements java.io.Serializable {


    private String name;

    private String code;

    private Double locationX;

    private Double locationY;

    private Long type;

    private Long station;

    private Integer fps;

    private String address;//保留字段

    private String description;


    public TmonitoringNode() {
        super();
    }


    public TmonitoringNode(String name, String code, Double locationX,
                           Double locationY, Long type, Long station, Integer fps,
                           String address, String description) {
        super();
        this.name = name;
        this.code = code;
        this.locationX = locationX;
        this.locationY = locationY;
        this.type = type;
        this.station = station;
        this.fps = fps;
        this.address = address;
        this.description = description;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public Double getLocationX() {
        return locationX;
    }


    public void setLocationX(Double locationX) {
        this.locationX = locationX;
    }


    public Double getLocationY() {
        return locationY;
    }


    public void setLocationY(Double locationY) {
        this.locationY = locationY;
    }


    public Long getType() {
        return type;
    }


    public void setType(Long type) {
        this.type = type;
    }


    public Long getStation() {
        return station;
    }


    public void setStation(Long station) {
        this.station = station;
    }


    public Integer getFps() {
        return fps;
    }


    public void setFps(Integer fps) {
        this.fps = fps;
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
