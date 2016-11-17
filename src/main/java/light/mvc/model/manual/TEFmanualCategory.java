package light.mvc.model.manual;

import light.mvc.model.base.baseEntity;
import light.mvc.model.sys.TEFPersonalInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "manualcategory")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TEFmanualCategory extends baseEntity implements java.io.Serializable {


    private String categoryName;
    private String categoryCode;
    private String categoryDescription;
    private Set<TEFPersonalInfo> users = new HashSet<TEFPersonalInfo>(0);

    private TEFmanualCategory parent;

    public TEFmanualCategory(String categoryName, String categoryCode,
                             String categoryDescription, TEFmanualCategory parent) {
        super();
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.categoryDescription = categoryDescription;
        this.parent = parent;
    }


    public TEFmanualCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ParentID")
    public TEFmanualCategory getParent() {
        return parent;
    }

    public void setParent(TEFmanualCategory parent) {
        this.parent = parent;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personalmanual", joinColumns = {@JoinColumn(name = "ManualCategoryID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "PersonalID", nullable = false, updatable = false)})
    @OrderBy("AutoID ASC")
    public Set<TEFPersonalInfo> getUsers() {
        return users;
    }


    public void setUsers(Set<TEFPersonalInfo> users) {
        this.users = users;
    }


}
