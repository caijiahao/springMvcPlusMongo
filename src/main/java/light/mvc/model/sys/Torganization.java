package light.mvc.model.sys;

import light.mvc.model.base.IdEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_organization", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Torganization extends IdEntity implements java.io.Serializable {

    private Date createdatetime;
    private String name;
    private String address;
    private String code;
    private String icon;
    private Integer seq;
    private Torganization organization;
    private Set<Torganization> organizations = new HashSet<Torganization>(0);

    public Torganization() {
        super();
    }

    public Torganization(Date createdatetime, String name, String address, String code, String icon, Integer seq,
                         Torganization organization, Set<Torganization> organizations) {
        super();
        this.createdatetime = createdatetime;
        this.name = name;
        this.address = address;
        this.code = code;
        this.icon = icon;
        this.seq = seq;
        this.organization = organization;
        this.organizations = organizations;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    public Torganization getOrganization() {
        return organization;
    }

    public void setOrganization(Torganization organization) {
        this.organization = organization;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdatetime", length = 19)
    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    @NotBlank
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    public Set<Torganization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Torganization> organizations) {
        this.organizations = organizations;
    }

}
