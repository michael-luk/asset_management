package models;

import LyLib.Interfaces.IConst;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonFormat;
import play.db.ebean.Model;
import util.Comment;
import util.SearchField;
import util.TableComment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auth")
@TableComment("权限")
public class Auth extends Model implements IConst {

	@Id
	public Long id;

	@Comment("所属用户")
	@ManyToMany(targetEntity = User.class)
	public List<User> users;

	@Comment("是否默认")
	public boolean isDefault = false;//

	@NotNull
	@SearchField
	@Comment("权限名称")
	public String name; //

	@Comment("权限编码")
	public String code; //

	@Comment("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date lastUpdateTime;

	@Comment("创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date createdAt;

	@Comment("备注")
	public String comment;

	public Auth() {
		createdAt = new Date();
	}

	public void save() {
		lastUpdateTime = new Date();
		Ebean.save(this);
	}

	public static Finder<Long, Auth> find = new Finder(Long.class, Auth.class);

	@Override
	public String toString() {
		return "Auth [name:" + name + "]";
	}
}