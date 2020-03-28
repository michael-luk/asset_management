package models;

import LyLib.Interfaces.IConst;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonFormat;
import play.db.ebean.Model;
import util.Comment;
import util.EnumMap;
import util.SearchField;
import util.TableComment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@TableComment("耗材")
public class Material extends Model implements IConst {

    @Id
    public Long id;

    @Comment("名称")
    @NotNull
    @SearchField
    public String name; // 名称

    @Comment("规格")
    public String content;

    @Comment("数量")
    public long quantity;

    @Comment("参考单价")
    @Column(columnDefinition = "Decimal(10,2)")
    public double price = 0D;

    @Comment("类型")
    @EnumMap(value = "0,1,2", name = "文具,打印耗材,清洁用品")
    public int status;

    @Comment("所属部门")
    @ManyToMany(targetEntity = Depart.class)
    public List<Depart> departs;

    @Comment("变更时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date lastUpdateTime;

    @Comment("申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date createdAt; // 创建日期

    @Comment("备注")
    public String comment;

    @Comment("关联采购单")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    public List<Purchase> purchases;

    @Comment("关联领用单")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    public List<UseRequest> useRequests;

    public Material() {
        createdAt = new Date();
    }

    public void save() {
        lastUpdateTime = new Date();
        Ebean.save(this);
    }

    public static Finder<Long, Material> find = new Finder(Long.class, Material.class);

    @Override
    public String toString() {
        return "Order [No:" + name + "]";
    }
}

