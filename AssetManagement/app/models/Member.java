package models;

import LyLib.Interfaces.IConst;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonFormat;
import play.db.ebean.Model;
import util.Comment;
import util.SearchField;
import util.TableComment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@TableComment("角色")
public class Member extends Model implements IConst {

	@Id
	public Long id;

	@Comment("所属用户")
	@ManyToMany(targetEntity = User.class)
	public List<User> users;

	@Comment("是否默认")
	public boolean isDefault = false;//

	@NotNull
	@SearchField
	@Comment("角色名称")
	public String name; //

	@Comment("角色编码")
	public String code; //

	@Comment("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date lastUpdateTime;

	@Comment("创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date createdAt;

	@Comment("备注")
	public String comment;

	public Member() {
		createdAt = new Date();
	}

	public void save() {
		lastUpdateTime = new Date();
		Ebean.save(this);
	}

	public static Finder<Long, Member> find = new Finder(Long.class, Member.class);

	@Override
	public String toString() {
		return "Member [name:" + name + "]";
	}
}