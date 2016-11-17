package light.mvc.model.manual;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "manualcontent")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFmanualContent extends baseEntity implements java.io.Serializable {

    private Long manualCategoryID;
    private String categoryCode;
    private String content;
    private String title;
    private String filePath;

    public TEFmanualContent() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TEFmanualContent(Long manualCategoryID, String categoryCode,
                            String content, String title, String filePath) {
        super();
        this.manualCategoryID = manualCategoryID;
        this.categoryCode = categoryCode;
        this.content = content;
        this.title = title;
        this.filePath = filePath;
    }

    public Long getManualCategoryID() {
        return manualCategoryID;
    }

    public void setManualCategoryID(Long manualCategoryID) {
        this.manualCategoryID = manualCategoryID;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
