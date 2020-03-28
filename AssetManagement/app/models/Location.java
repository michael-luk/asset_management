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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "location")
@TableComment("存放地点")
public class Location extends Model implements IConst {

	@Id
	public Long id;

	@NotNull
	@SearchField
	@Comment("名称")
	public String name; //

	@Comment("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date lastUpdateTime;

	@Comment("创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date createdAt;

	@Comment("备注")
	public String comment;

	public Location() {
		createdAt = new Date();
	}

	public void save() {
		lastUpdateTime = new Date();
		Ebean.save(this);
	}

	public static Finder<Long, Location> find = new Finder(Long.class, Location.class);

	@Override
	public String toString() {
		return "Location [name:" + name + "]";
	}
}