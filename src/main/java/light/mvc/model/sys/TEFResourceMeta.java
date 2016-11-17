package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "resourceMeta")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFResourceMeta extends baseEntity implements java.io.Serializable {

    private TEFDictionary dictionary;
    private Long metaID;
    private String metaPath;
    private String thumbMetaPath;
    private String metaDescription;

    public TEFResourceMeta() {
        super();
    }


    public TEFResourceMeta(TEFDictionary dictionary, Long metaID,
                           String metaPath, String thumbMetaPath, String metaDescription) {
        super();
        this.dictionary = dictionary;
        this.metaID = metaID;
        this.metaPath = metaPath;
        this.thumbMetaPath = thumbMetaPath;
        this.metaDescription = metaDescription;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type")
    public TEFDictionary getDictionary() {
        return dictionary;
    }


    public void setDictionary(TEFDictionary dictionary) {
        this.dictionary = dictionary;
    }


    public Long getMetaID() {
        return metaID;
    }

    public void setMetaID(Long metaID) {
        this.metaID = metaID;
    }

    public String getMetaPath() {
        return metaPath;
    }

    public void setMetaPath(String metaPath) {
        this.metaPath = metaPath;
    }

    public String getThumbMetaPath() {
        return thumbMetaPath;
    }

    public void setThumbMetaPath(String thumbMetaPath) {
        this.thumbMetaPath = thumbMetaPath;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }
}
