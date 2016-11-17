package light.mvc.pageModel.manual;

import java.util.List;

public class EFmanualCategoryList implements java.io.Serializable {
    List<EFmanualCategory> list;
    private Integer isParent;

    public List<EFmanualCategory> getList() {
        return list;
    }

    public void setList(List<EFmanualCategory> list) {
        this.list = list;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }


}
