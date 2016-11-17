package light.mvc.model.sys;

import light.mvc.model.base.baseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/*
 * @author: yeyaowen 
 * 编写日期：2016-8-9
 * 特别注意：初步编写，尚未测试
 * */


@Entity
@Table(name = "dictionary", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFDictionary extends baseEntity implements java.io.Serializable {

    private String code;
    private String text;
    private TEFDictionaryCategory dictionaryCategory;
    private Integer seq;
    private Integer isDefault; // 是否默认

    public TEFDictionary() {

    }

    public TEFDictionary(String code, String text,
                         TEFDictionaryCategory dictionaryCategory, Integer seq, Integer state,
                         Integer isdefault) {
        super();
        this.code = code;
        this.text = text;
        this.dictionaryCategory = dictionaryCategory;
        this.seq = seq;
        this.isDefault = isdefault;
    }

    @NotBlank
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotBlank
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DictionaryCategoryID")
    public TEFDictionaryCategory getDictionaryCategory() {
        return dictionaryCategory;
    }

    public void setDictionaryCategory(TEFDictionaryCategory dictionaryCategory) {
        this.dictionaryCategory = dictionaryCategory;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


}
