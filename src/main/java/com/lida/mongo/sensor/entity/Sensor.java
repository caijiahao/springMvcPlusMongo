package com.lida.mongo.sensor.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by lenovo on 2016/12/6.
 */
@Document(collection = "sensor")
public class Sensor {
    @Id
    private ObjectId id;
    /*
     *传感器是否活跃
     */
    private Integer Active;
    private String CreateBy;
    private Date createDate;
    private String UpdateBy;
    private Date updateDate;
    private Integer Deleted;
    private Long timeLine;
    private BigInteger MonitoringNodeId;
    private BigInteger serialNum;
    private Integer sensor_ch;
    private Double sensor1;
    private Double sensor2;
    private Double sensor3;
    private Double sensor4;
    private Double sensor5;
    private Double sensor6;
    private Double sensor7;
    private Double sensor8;
    private Double sensor9;
    private Double sensor10;
    private Double sensor11;
    private Double sensor12;
    private Double sensor13;
    private Double sensor14;
    private Double sensor15;
    private Double sensor16;
    private Double sensor17;
    private Double sensor18;
    private Double sensor19;
    private Double sensor20;
    private Double sensor21;
    private Double sensor22;
    private Double sensor23;
    private Double sensor24;
    private Double sensor25;
    private Double sensor26;
    private Double sensor27;
    private Double sensor28;
    private Double sensor29;
    private Double sensor30;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getActive() {
        return Active;
    }

    public void setActive(Integer active) {
        Active = active;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return UpdateBy;
    }

    public void setUpdateBy(String updateBy) {
        UpdateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDeleted() {
        return Deleted;
    }

    public void setDeleted(Integer deleted) {
        Deleted = deleted;
    }

    public Long getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(Long timeLine) {
        this.timeLine = timeLine;
    }

    public BigInteger getMonitoringNodeId() {
        return MonitoringNodeId;
    }

    public void setMonitoringNodeId(BigInteger monitoringNodeId) {
        MonitoringNodeId = monitoringNodeId;
    }

    public BigInteger getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(BigInteger serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getSensor_ch() {
        return sensor_ch;
    }

    public void setSensor_ch(Integer sensor_ch) {
        this.sensor_ch = sensor_ch;
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

    public Double getSensor7() {
        return sensor7;
    }

    public void setSensor7(Double sensor7) {
        this.sensor7 = sensor7;
    }

    public Double getSensor8() {
        return sensor8;
    }

    public void setSensor8(Double sensor8) {
        this.sensor8 = sensor8;
    }

    public Double getSensor9() {
        return sensor9;
    }

    public void setSensor9(Double sensor9) {
        this.sensor9 = sensor9;
    }

    public Double getSensor10() {
        return sensor10;
    }

    public void setSensor10(Double sensor10) {
        this.sensor10 = sensor10;
    }

    public Double getSensor11() {
        return sensor11;
    }

    public void setSensor11(Double sensor11) {
        this.sensor11 = sensor11;
    }

    public Double getSensor12() {
        return sensor12;
    }

    public void setSensor12(Double sensor12) {
        this.sensor12 = sensor12;
    }

    public Double getSensor13() {
        return sensor13;
    }

    public void setSensor13(Double sensor13) {
        this.sensor13 = sensor13;
    }

    public Double getSensor14() {
        return sensor14;
    }

    public void setSensor14(Double sensor14) {
        this.sensor14 = sensor14;
    }

    public Double getSensor15() {
        return sensor15;
    }

    public void setSensor15(Double sensor15) {
        this.sensor15 = sensor15;
    }

    public Double getSensor16() {
        return sensor16;
    }

    public void setSensor16(Double sensor16) {
        this.sensor16 = sensor16;
    }

    public Double getSensor17() {
        return sensor17;
    }

    public void setSensor17(Double sensor17) {
        this.sensor17 = sensor17;
    }

    public Double getSensor18() {
        return sensor18;
    }

    public void setSensor18(Double sensor18) {
        this.sensor18 = sensor18;
    }

    public Double getSensor19() {
        return sensor19;
    }

    public void setSensor19(Double sensor19) {
        this.sensor19 = sensor19;
    }

    public Double getSensor20() {
        return sensor20;
    }

    public void setSensor20(Double sensor20) {
        this.sensor20 = sensor20;
    }

    public Double getSensor21() {
        return sensor21;
    }

    public void setSensor21(Double sensor21) {
        this.sensor21 = sensor21;
    }

    public Double getSensor22() {
        return sensor22;
    }

    public void setSensor22(Double sensor22) {
        this.sensor22 = sensor22;
    }

    public Double getSensor23() {
        return sensor23;
    }

    public void setSensor23(Double sensor23) {
        this.sensor23 = sensor23;
    }

    public Double getSensor24() {
        return sensor24;
    }

    public void setSensor24(Double sensor24) {
        this.sensor24 = sensor24;
    }

    public Double getSensor25() {
        return sensor25;
    }

    public void setSensor25(Double sensor25) {
        this.sensor25 = sensor25;
    }

    public Double getSensor26() {
        return sensor26;
    }

    public void setSensor26(Double sensor26) {
        this.sensor26 = sensor26;
    }

    public Double getSensor27() {
        return sensor27;
    }

    public void setSensor27(Double sensor27) {
        this.sensor27 = sensor27;
    }

    public Double getSensor28() {
        return sensor28;
    }

    public void setSensor28(Double sensor28) {
        this.sensor28 = sensor28;
    }

    public Double getSensor29() {
        return sensor29;
    }

    public void setSensor29(Double sensor29) {
        this.sensor29 = sensor29;
    }

    public Double getSensor30() {
        return sensor30;
    }

    public void setSensor30(Double sensor30) {
        this.sensor30 = sensor30;
    }
}
