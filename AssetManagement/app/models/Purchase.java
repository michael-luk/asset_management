package models;

import LyLib.Interfaces.IConst;
import LyLib.Utils.StrUtil;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;
import util.*;
import util.EnumMap;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@TableComment("耗材采购")
public class Purchase extends Model implements IConst {

    @Id
    public Long id;

    @Comment("标题")
    @NotNull
    @SearchField
    public String name; // 名称

    @Comment("内容")
    public String content;

    @Comment("变更时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date lastUpdateTime;

    @Comment("申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date createdAt; // 创建日期

    @Comment("备注")
    public String comment;

    @Comment("所属耗材ID")
    public Long refMaterialId;

    @JsonIgnore
    @ManyToOne
    @Comment("采购耗材")
    public Material material;

    @Comment("所属用户ID")
    public Long refUserId;

    @JsonIgnore
    @ManyToOne
    @Comment("所属用户")
    public User user;

    public Purchase() {
        createdAt = new Date();
    }

    public void save() {
        lastUpdateTime = new Date();
        Ebean.save(this);
    }

    public static Finder<Long, Purchase> find = new Finder(Long.class, Purchase.class);

    @Override
    public String toString() {
        return "Order [No:" + name + "]";
    }
}

