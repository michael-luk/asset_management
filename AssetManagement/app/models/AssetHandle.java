package models;

import LyLib.Interfaces.IConst;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonFormat;
import play.db.ebean.Model;
import util.Comment;
import util.EnumMap;
import util.SearchField;
import util.TableComment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@TableComment("资产处置")
public class AssetHandle extends Model implements IConst {

    @Id
    public Long id;

    @Comment("标题")
    @NotNull
    @SearchField
    public String name; // 名称

    @Comment("处置方式")
    @EnumMap(value = "0,1,2,3,4,5,6,7", name = "调拨,出售,出让,转让,置换,报损,报废,捐赠")
    public int status; // test using

    @Comment("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date lastUpdateTime;

    @Comment("创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date createdAt; // 创建日期

    @Comment("备注")
    public String comment;

    public AssetHandle() {
        createdAt = new Date();
    }

    public void save() {
        lastUpdateTime = new Date();
        Ebean.save(this);
    }

    public static Finder<Long, AssetHandle> find = new Finder(Long.class, AssetHandle.class);

    @Override
    public String toString() {
        return "Info [name:" + name + "]";
    }
}