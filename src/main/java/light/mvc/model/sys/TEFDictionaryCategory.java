package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */

@Entity
@Table(name = "dictionarycategory", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFDictionaryCategory extends baseEntity implements java.io.Serializable{
	
	private String code;
	private String name;
	private Integer seq;
	private String description;
	private TEFDictionaryCategory dictionaryCategory;
	

	
	public TEFDictionaryCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TEFDictionaryCategory(String code, String name, Integer seq,
			String description) {
		super();
		this.code = code;
		this.name = name;
		this.seq = seq;
		this.description = description;
	}

	@NotBlank
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentID")
	public TEFDictionaryCategory getDictionaryCategory() {
		return dictionaryCategory;
	}

	public void setDictionaryCategory(TEFDictionaryCategory dictionaryCategory) {
		this.dictionaryCategory = dictionaryCategory;
	}


}
