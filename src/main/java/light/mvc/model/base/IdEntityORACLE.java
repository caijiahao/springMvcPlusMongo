package light.mvc.model.base;

import javax.persistence.*;

/**
 * 支持oracle 拿用户来说，这个还没想好，怎么样切换方便一点
 * 建表语句要创建sequence
 * create sequence SEQ_USER start with 100 increment by 20;
 * */
@MappedSuperclass
public abstract class IdEntityORACLE {

	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
	@SequenceGenerator(name = "UserSequence", sequenceName = "SEQ_USER", allocationSize = 20)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
