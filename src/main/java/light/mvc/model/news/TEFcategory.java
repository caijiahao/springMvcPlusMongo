package light.mvc.model.news;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "newscategory")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFcategory extends baseEntity implements java.io.Serializable{
	
	private String categoryName;
	private String categoryDescription;
	private Long type;

	
	
	public TEFcategory()
	{
		super();
	}



	public TEFcategory(String categoryName, String categoryDescription,
			Long type) {
		super();
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.type = type;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getCategoryDescription() {
		return categoryDescription;
	}



	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}



	public Long getType() {
		return type;
	}



	public void setType(Long type) {
		this.type = type;
	}
	

	

}
