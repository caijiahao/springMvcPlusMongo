package light.mvc.model.manual;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "manualkeyword")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFmanualKeyword extends baseEntity implements java.io.Serializable {
    private Long manualContentID;
    private Long categoryID;
    private String categoryCode;
    private String keyword;

    public TEFmanualKeyword() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TEFmanualKeyword(Long manualCategoryID, Long categoryID, String categoryCode,
                            String keyword) {
        super();
        this.manualContentID = manualCategoryID;
        this.categoryID = categoryID;
        this.categoryCode = categoryCode;
        this.keyword = keyword;
    }

    public Long getManualContentID() {
        return manualContentID;
    }

    public void setManualContentID(Long manualContentID) {
        this.manualContentID = manualContentID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
