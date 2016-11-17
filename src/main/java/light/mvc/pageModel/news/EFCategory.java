package light.mvc.pageModel.news;

public class EFCategory implements java.io.Serializable {
    private Long autoID;
    private String categoryName;
    private String categoryDescription;
    private Long type;
    private String typeDesc;
    private String shortCategoryName;


    public Long getAutoID() {
        return autoID;
    }

    public void setAutoID(Long autoID) {
        this.autoID = autoID;
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

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getShortCategoryName() {
        return shortCategoryName;
    }

    public void setShortCategoryName(String shortCategoryName) {
        this.shortCategoryName = shortCategoryName;
    }

}
