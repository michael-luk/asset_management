package models;

import LyLib.Interfaces.IConst;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonFormat;
import play.db.ebean.Model;
import util.Comment;
import util.SearchField;
import util.TableComment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
//@Table(name = "a")
@TableComment("资产卡片")
public class AssetCard extends Model implements IConst {

	@Id
	public Long id;

	@NotNull
	@SearchField
	@Comment("卡片名称")
	public String name; //

	@Comment("扫描码")
	@Column(columnDefinition = "varchar(400)")
	public String images;

	@Comment("修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date lastUpdateTime;

	@Comment("创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date createdAt;

	@Comment("备注")
	public String comment;

	public AssetCard() {
		createdAt = new Date();
	}

	public void save() {
		lastUpdateTime = new Date();
		Ebean.save(this);
	}

	public static Finder<Long, AssetCard> find = new Finder(Long.class, AssetCard.class);

	@Override
	public String toString() {
		return "Barcode [name:" + name + "]";
	}
}