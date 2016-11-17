package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "notice")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFNotice extends baseEntity implements java.io.Serializable {

    private TEFDictionary type;
    private Long metaID;
    private TEFPersonalInfo personal;
    private String content;
    private Integer isNotice;

    public TEFNotice() {
        super();
        // TODO Auto-generated constructor stub
    }


    public TEFNotice(TEFDictionary type, Long metaID, TEFPersonalInfo personal,
                     String content, Integer isNotice) {
        super();
        this.type = type;
        this.metaID = metaID;
        this.personal = personal;
        this.content = content;
        this.isNotice = isNotice;
    }


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type")
    public TEFDictionary getDictionary() {
        return type;
    }

    public void setDictionary(TEFDictionary dictionary) {
        this.type = dictionary;
    }

    public Long getMetaID() {
        return metaID;
    }

    public void setMetaID(Long metaID) {
        this.metaID = metaID;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonalID")
    public TEFPersonalInfo getPersonal() {
        return personal;
    }

    public void setPersonal(TEFPersonalInfo personal) {
        this.personal = personal;
    }

    public Integer getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(Integer isNotice) {
        this.isNotice = isNotice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
