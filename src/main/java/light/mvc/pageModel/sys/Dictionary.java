package light.mvc.pageModel.sys;


public class Dictionary implements java.io.Serializable {

    private Long id;
    private String code;
    private String text;
    private Long dictionarytypeId;
    private String dictionarytypeName;
    private Integer seq;
    private Integer active; // 状态 0启用 1停用
    private Integer isDefault; // 是否默认


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    public Long getDictionarytypeId() {
        return dictionarytypeId;
    }

    public void setDictionarytypeId(Long dictionarytypeId) {
        this.dictionarytypeId = dictionarytypeId;
    }

    public String getDictionarytypeName() {
        return dictionarytypeName;
    }

    public void setDictionarytypeName(String dictionarytypeName) {
        this.dictionarytypeName = dictionarytypeName;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }


}
