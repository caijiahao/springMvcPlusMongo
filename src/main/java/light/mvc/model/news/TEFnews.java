package light.mvc.model.news;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "newscontent")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFnews extends baseEntity implements java.io.Serializable {

    private Long personalID;
    private String author;
    private String title;
    private String content;
    private String webPath;
    private Date publishDate;
    private Integer readCount;
    private Long status;
    private Long categoryID;

    public TEFnews() {
        super();
    }

    public TEFnews(Long personalID, String author, String title, String content, String webPath, Date publishDate,
                   Integer readCount, Long status, Long categoryID) {
        super();
        this.personalID = personalID;
        this.author = author;
        this.title = title;
        this.content = content;
        this.webPath = webPath;
        this.publishDate = publishDate;
        this.readCount = readCount;
        this.status = status;
        this.categoryID = categoryID;
    }

    public Long getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Long personalID) {
        this.personalID = personalID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWebPath() {
        return webPath;
    }

    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

}
