package light.mvc.model.sensor;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "station")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tstation extends baseEntity implements java.io.Serializable{

	
	private String name;
	private String address;
	private String code;
	private Long type;
	private Tstation station;
	private String linkPeople;
	private String linkPhone;
	private String Description;
	private Long serialNum;
	
	private String loginName;
	private String password;
	
	private Date sensorDateUpdate;
	public Tstation() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Tstation(String name, String address, String code, Long type,
			Tstation station, String linkPeople, String linkPhone,
			String description, Long serialNum, String loginName,
			String password, Date sensorDateUpdate) {
		super();
		this.name = name;
		this.address = address;
		this.code = code;
		this.type = type;
		this.station = station;
		this.linkPeople = linkPeople;
		this.linkPhone = linkPhone;
		Description = description;
		this.serialNum = serialNum;
		this.loginName = loginName;
		this.password = password;
		this.sensorDateUpdate = sensorDateUpdate;
	}



	public Date getSensorDateUpdate() {
		return sensorDateUpdate;
	}



	public void setSensorDateUpdate(Date sensorDateUpdate) {
		this.sensorDateUpdate = sensorDateUpdate;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Tstation getStation() {
		return station;
	}
	public void setStation(Tstation station) {
		this.station = station;
	}
	public String getLinkPeople() {
		return linkPeople;
	}
	public void setLinkPeople(String linkPeople) {
		this.linkPeople = linkPeople;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}



	public Long getSerialNum() {
		return serialNum;
	}



	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
