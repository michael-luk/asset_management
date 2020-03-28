package models;

import LyLib.Interfaces.IConst;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;
import util.Comment;
import util.EnumMap;
import util.SearchField;
import util.TableComment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@TableComment("资产领用交回")
public class AssetUse extends Model implements IConst {

    @Id
    public Long id;

    @Comment("标题")
    @NotNull
    @SearchField
    public String name; // 名称

    @Comment("内容")
    public String content;

    @Comment("分类")
    @EnumMap(value = "0,1", name = "领用,交回")
    public int status; // test using

    @Comment("变更时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date lastUpdateTime;

    @Comment("申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date createdAt; // 创建日期

    @Comment("备注")
    public String comment;

    @Comment("所属用户ID")
    public Long refUserId;

    @JsonIgnore
    @ManyToOne
    @Comment("所属用户")
    public User user;

    public AssetUse() {
        createdAt = new Date();
    }

    public void save() {
        lastUpdateTime = new Date();
        Ebean.save(this);
    }

    public static Finder<Long, AssetUse> find = new Finder(Long.class, AssetUse.class);

    @Override
    public String toString() {
        return "Info [name:" + name + "]";
    }
}