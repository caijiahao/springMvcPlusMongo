package light.mvc.model.sensor;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sensor")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tsensor extends baseEntity implements java.io.Serializable{

	private Long monitoringNodeId;
	
	private Long serialNum;
	
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
	

	

	public Tsensor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	


	public Tsensor(Long monitoringNodeId, Long serialNum, Integer sensor_ch,
			Double sensor1, Double sensor2, Double sensor3, Double sensor4,
			Double sensor5, Double sensor6, Double sensor7, Double sensor8,
			Double sensor9, Double sensor10, Double sensor11, Double sensor12,
			Double sensor13, Double sensor14, Double sensor15, Double sensor16,
			Double sensor17, Double sensor18, Double sensor19, Double sensor20) {
		super();
		this.monitoringNodeId = monitoringNodeId;
		this.serialNum = serialNum;
		this.sensor_ch = sensor_ch;
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		this.sensor3 = sensor3;
		this.sensor4 = sensor4;
		this.sensor5 = sensor5;
		this.sensor6 = sensor6;
		this.sensor7 = sensor7;
		this.sensor8 = sensor8;
		this.sensor9 = sensor9;
		this.sensor10 = sensor10;
		this.sensor11 = sensor11;
		this.sensor12 = sensor12;
		this.sensor13 = sensor13;
		this.sensor14 = sensor14;
		this.sensor15 = sensor15;
		this.sensor16 = sensor16;
		this.sensor17 = sensor17;
		this.sensor18 = sensor18;
		this.sensor19 = sensor19;
		this.sensor20 = sensor20;
	}






	public Long getSerialNum() {
		return serialNum;
	}






	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}






	public Integer getSensor_ch() {
		return sensor_ch;
	}






	public void setSensor_ch(Integer sensor_ch) {
		this.sensor_ch = sensor_ch;
	}






	public Long getMonitoringNodeId() {
		return monitoringNodeId;
	}

	public void setMonitoringNodeId(Long monitoringNodeId) {
		this.monitoringNodeId = monitoringNodeId;
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


	
	
	
	
}
