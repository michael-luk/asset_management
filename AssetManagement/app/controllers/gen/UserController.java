package controllers.gen;

import controllers.*;
import controllers.biz.*;
import play.mvc.WebSocket;
import util.*;
import views.html.*;
import views.html.gen.*;
import LyLib.Interfaces.IConst;
import LyLib.Utils.DateUtil;
import LyLib.Utils.PageInfo;
import LyLib.Utils.StrUtil;
import LyLib.Utils.Msg;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.avaje.ebean.Transaction;
import java.util.ArrayList;
import models.User;
import models.Auth;
import models.Depart;
import models.Member;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.File;
import java.io.FileOutputStream;
import play.Play;

import javax.persistence.PersistenceException;

import static controllers.Application.nameChannels;
import static controllers.Application.sendWebSocketByChannelTag;
import static play.data.Form.form;

public class UserController extends Controller implements IConst {
    
    public static Result userPage(Integer status, Integer notStatus,
                                     String fieldOn, String fieldValue, boolean isAnd,
                                     String searchOn, String kw,
                                     String startTime, String endTime,
                                     String order, String sort,
                                     Integer page, Integer size) {
        Msg<List<User>> msg = BaseController.doGetAll("User",
                status, notStatus,
                fieldOn, fieldValue, isAnd,
                searchOn, kw,
                startTime, endTime,
                order, sort,
                page, size);
        
        if (msg.flag) {
            return ok(user.render(msg.data));
        } else {
            msg.data = new ArrayList<>();
            return ok(msg.message);
        }
    }
    
    @Security.Authenticated(SecuredAdmin.class)
    public static Result userBackendPage() {
        return ok(user_backend.render());
    }
    
    public static Result getUserAuths(Long refId, Integer page, Integer size) {
        if (size == 0)
            size = PAGE_SIZE;
        if (page <= 0)
            page = 1;

        Msg<List<Auth>> msg = new Msg<>();

        User found = User.find.byId(refId);
        if (found != null) {
            if (found.auths.size() > 0) {
                Page<Auth> records;
                records = Auth.find.where().eq("users.id", refId).orderBy("id desc").findPagingList(size)
                        .setFetchAhead(false).getPage(page - 1);

                if (records.getTotalRowCount() > 0) {
                    msg.flag = true;

                    PageInfo pageInfo = new PageInfo();
                    pageInfo.current = page;
                    pageInfo.total = records.getTotalRowCount();
                    pageInfo.size = size;
                    if (records.hasPrev())
                        pageInfo.hasPrev = true;
                    if (records.hasNext())
                        pageInfo.hasNext = true;

                    msg.data = records.getList();
                    msg.page = pageInfo;
                    play.Logger.info("result: " + msg.data.size());
                } else {
                    msg.message = NO_FOUND;
                    play.Logger.info("auths row result: " + NO_FOUND);
                }
            } else {
                msg.message = NO_FOUND;
                play.Logger.info("auths result: " + NO_FOUND);
            }
        } else {
            msg.message = NO_FOUND;
            play.Logger.info("user result: " + NO_FOUND);
        }
        return ok(Json.toJson(msg));
    }
    public static Result getUserDeparts(Long refId, Integer page, Integer size) {
        if (size == 0)
            size = PAGE_SIZE;
        if (page <= 0)
            page = 1;

        Msg<List<Depart>> msg = new Msg<>();

        User found = User.find.byId(refId);
        if (found != null) {
            if (found.departs.size() > 0) {
                Page<Depart> records;
                records = Depart.find.where().eq("users.id", refId).orderBy("id desc").findPagingList(size)
                        .setFetchAhead(false).getPage(page - 1);

                if (records.getTotalRowCount() > 0) {
                    msg.flag = true;

                    PageInfo pageInfo = new PageInfo();
                    pageInfo.current = page;
                    pageInfo.total = records.getTotalRowCount();
                    pageInfo.size = size;
                    if (records.hasPrev())
                        pageInfo.hasPrev = true;
                    if (records.hasNext())
                        pageInfo.hasNext = true;

                    msg.data = records.getList();
                    msg.page = pageInfo;
                    play.Logger.info("result: " + msg.data.size());
                } else {
                    msg.message = NO_FOUND;
                    play.Logger.info("departs row result: " + NO_FOUND);
                }
            } else {
                msg.message = NO_FOUND;
                play.Logger.info("departs result: " + NO_FOUND);
            }
        } else {
            msg.message = NO_FOUND;
            play.Logger.info("user result: " + NO_FOUND);
        }
        return ok(Json.toJson(msg));
    }
    public static Result getUserMembers(Long refId, Integer page, Integer size) {
        if (size == 0)
            size = PAGE_SIZE;
        if (page <= 0)
            page = 1;

        Msg<List<Member>> msg = new Msg<>();

        User found = User.find.byId(refId);
        if (found != null) {
            if (found.members.size() > 0) {
                Page<Member> records;
                records = Member.find.where().eq("users.id", refId).orderBy("id desc").findPagingList(size)
                        .setFetchAhead(false).getPage(page - 1);

                if (records.getTotalRowCount() > 0) {
                    msg.flag = true;

                    PageInfo pageInfo = new PageInfo();
                    pageInfo.current = page;
                    pageInfo.total = records.getTotalRowCount();
                    pageInfo.size = size;
                    if (records.hasPrev())
                        pageInfo.hasPrev = true;
                    if (records.hasNext())
                        pageInfo.hasNext = true;

                    msg.data = records.getList();
                    msg.page = pageInfo;
                    play.Logger.info("result: " + msg.data.size());
                } else {
                    msg.message = NO_FOUND;
                    play.Logger.info("members row result: " + NO_FOUND);
                }
            } else {
                msg.message = NO_FOUND;
                play.Logger.info("members result: " + NO_FOUND);
            }
        } else {
            msg.message = NO_FOUND;
            play.Logger.info("user result: " + NO_FOUND);
        }
        return ok(Json.toJson(msg));
    }

    @Security.Authenticated(SecuredSuperAdmin.class)
    @MethodName("新增_User")
    @Role("create_user")
    public static Result add() {
        Msg<User> msg = new Msg<>();

        Form<UserParser> httpForm = form(UserParser.class).bindFromRequest();
        if (!httpForm.hasErrors()) {
            UserParser formObj = httpForm.get();            
            User newObj = new User();
            
            String uniqueFieldIssue = BaseController.checkFieldUnique("User", formObj);
            if (StrUtil.isNotNull(uniqueFieldIssue)) {
                msg.message = "字段[" + TableInfoReader.getFieldComment("User", uniqueFieldIssue)
                        + "]存在同名数据";
                return ok(Json.toJson(msg));
            }

            newObj.name = formObj.name;
            newObj.loginName = formObj.loginName;
            newObj.password = formObj.password;
            newObj.headImage = formObj.headImage;
            newObj.email = formObj.email;
            newObj.isEmailVerified = formObj.isEmailVerified;
            newObj.emailKey = formObj.emailKey;
            newObj.emailOverTime = formObj.emailOverTime;
            newObj.images = formObj.images;
            newObj.sexEnum = formObj.sexEnum;
            newObj.phone = formObj.phone;
            newObj.cardNumber = formObj.cardNumber;
            newObj.country = formObj.country;
            newObj.province = formObj.province;
            newObj.city = formObj.city;
            newObj.zone = formObj.zone;
            newObj.address = formObj.address;
            newObj.lastLoginIp = formObj.lastLoginIp;
            newObj.lastLoginTime = formObj.lastLoginTime;
            newObj.lastLoginIpArea = formObj.lastLoginIpArea;
            newObj.status = formObj.status;
            newObj.comment = formObj.comment;

            if (formObj.auths == null) {
                formObj.auths = new ArrayList<>();
            }
            newObj.auths = formObj.auths;
        
            if (formObj.departs == null) {
                formObj.departs = new ArrayList<>();
            }
            newObj.departs = formObj.departs;
        
            if (formObj.members == null) {
                formObj.members = new ArrayList<>();
            }
            newObj.members = formObj.members;
        
            Transaction txn = Ebean.beginTransaction();
            try{
                SaveBiz.beforeSave(newObj);
                Ebean.save(newObj);
                
                for (Auth jsonRefObj : formObj.auths){
                    Auth dbRefObj = Auth.find.byId(jsonRefObj.id);
                    dbRefObj.users.add(newObj);
                    dbRefObj.save();
                }
                for (Depart jsonRefObj : formObj.departs){
                    Depart dbRefObj = Depart.find.byId(jsonRefObj.id);
                    dbRefObj.users.add(newObj);
                    dbRefObj.save();
                }
                for (Member jsonRefObj : formObj.members){
                    Member dbRefObj = Member.find.byId(jsonRefObj.id);
                    dbRefObj.users.add(newObj);
                    dbRefObj.save();
                }
                
                txn.commit();
                msg.flag = true;
                msg.data = newObj;
                play.Logger.info("result: " + CREATE_SUCCESS);
                sendWebSocketByChannelTag("user_backend", "new");
            } catch (PersistenceException ex){
                msg.message = CREATE_ISSUE + ", ex: " + ex.getMessage();
                play.Logger.error(msg.message);
                return ok(Json.toJson(msg));
            } finally {
                txn.end();
            }
            return ok(Json.toJson(msg));
        } else {        
            if (httpForm.hasGlobalErrors())
                msg.message = httpForm.globalError().message();
            else {
                if (httpForm.hasErrors())
                    msg.message = "输入数据不正确, 请重试";
            }
            play.Logger.error("result: " + msg.message);
        }
        return ok(Json.toJson(msg));
    }

    @Security.Authenticated(SecuredSuperAdmin.class)
    @MethodName("修改_User")
    @Role("update_user")
    public static Result update(long id) {
        Msg<User> msg = new Msg<>();

        User found = User.find.byId(id);
        if (found == null) {
            msg.message = NO_FOUND;
            play.Logger.info("result: " + msg.message);
            return ok(Json.toJson(msg));
        }

        Form<UserParser> httpForm = form(UserParser.class).bindFromRequest();

        if (!httpForm.hasErrors()) {
            UserParser formObj = httpForm.get();        
            
            String uniqueFieldIssue = BaseController.checkFieldUnique("User", formObj, 1);
            if (StrUtil.isNotNull(uniqueFieldIssue)) {
                msg.message = "字段[" + TableInfoReader.getFieldComment("User", uniqueFieldIssue)
                        + "]存在同名数据";
                return ok(Json.toJson(msg));
            }
            
            Transaction txn = Ebean.beginTransaction();
            try{
                found = User.find.byId(id);
                            
                found.name = formObj.name;
                found.loginName = formObj.loginName;
                found.password = formObj.password;
                found.headImage = formObj.headImage;
                found.email = formObj.email;
                found.isEmailVerified = formObj.isEmailVerified;
                found.emailKey = formObj.emailKey;
                found.emailOverTime = formObj.emailOverTime;
                found.images = formObj.images;
                found.sexEnum = formObj.sexEnum;
                found.phone = formObj.phone;
                found.cardNumber = formObj.cardNumber;
                found.country = formObj.country;
                found.province = formObj.province;
                found.city = formObj.city;
                found.zone = formObj.zone;
                found.address = formObj.address;
                found.lastLoginIp = formObj.lastLoginIp;
                found.lastLoginTime = formObj.lastLoginTime;
                found.lastLoginIpArea = formObj.lastLoginIpArea;
                found.status = formObj.status;
                found.comment = formObj.comment;

                // 处理多对多 user <-> Auth, 先清掉对面的
                for (Auth refObj : found.auths) {
                    if (refObj.users.contains(found)) {
                        refObj.users.remove(found);
                        refObj.save();
                    }
                }

                // 清掉自己这边的
                found.auths = new ArrayList<>();
                found.save();

                // 两边加回
                List<Auth> allRefAuths = Auth.find.all();
                if (formObj.auths != null) {
                    for (Auth jsonRefObj : formObj.auths) {
                        for (Auth dbRefObj : allRefAuths) {
                            if (dbRefObj.id == jsonRefObj.id) {
                                if (!found.auths.contains(dbRefObj)) {
                                    found.auths.add(dbRefObj);
                                }
                                if (!dbRefObj.users.contains(found)) {
                                    dbRefObj.users.add(found);
                                    dbRefObj.save();
                                }
                            }

                        }
                    }
                }
                // 处理多对多 user <-> Depart, 先清掉对面的
                for (Depart refObj : found.departs) {
                    if (refObj.users.contains(found)) {
                        refObj.users.remove(found);
                        refObj.save();
                    }
                }

                // 清掉自己这边的
                found.departs = new ArrayList<>();
                found.save();

                // 两边加回
                List<Depart> allRefDeparts = Depart.find.all();
                if (formObj.departs != null) {
                    for (Depart jsonRefObj : formObj.departs) {
                        for (Depart dbRefObj : allRefDeparts) {
                            if (dbRefObj.id == jsonRefObj.id) {
                                if (!found.departs.contains(dbRefObj)) {
                                    found.departs.add(dbRefObj);
                                }
                                if (!dbRefObj.users.contains(found)) {
                                    dbRefObj.users.add(found);
                                    dbRefObj.save();
                                }
                            }

                        }
                    }
                }
                // 处理多对多 user <-> Member, 先清掉对面的
                for (Member refObj : found.members) {
                    if (refObj.users.contains(found)) {
                        refObj.users.remove(found);
                        refObj.save();
                    }
                }

                // 清掉自己这边的
                found.members = new ArrayList<>();
                found.save();

                // 两边加回
                List<Member> allRefMembers = Member.find.all();
                if (formObj.members != null) {
                    for (Member jsonRefObj : formObj.members) {
                        for (Member dbRefObj : allRefMembers) {
                            if (dbRefObj.id == jsonRefObj.id) {
                                if (!found.members.contains(dbRefObj)) {
                                    found.members.add(dbRefObj);
                                }
                                if (!dbRefObj.users.contains(found)) {
                                    dbRefObj.users.add(found);
                                    dbRefObj.save();
                                }
                            }

                        }
                    }
                }
                SaveBiz.beforeUpdate(found);
                Ebean.update(found);
                txn.commit();
                msg.flag = true;
                msg.data = found;
                play.Logger.info("result: " + UPDATE_SUCCESS);
                sendWebSocketByChannelTag("user_backend", "new");
            } catch (Exception ex){
                msg.message = UPDATE_ISSUE + ", ex: " + ex.getMessage();
                play.Logger.error(msg.message);
            } finally {
                txn.end();
            }
            return ok(Json.toJson(msg));
        } else {     
            if (httpForm.hasGlobalErrors())
                msg.message = httpForm.globalError().message();
            else {
                if (httpForm.hasErrors())
                    msg.message = "输入数据不正确, 请重试";
            }
            play.Logger.error("result: " + msg.message);
        }
        return ok(Json.toJson(msg));
    }
    
    public static class UserParser {

        public String name;
        public String loginName;
        public String password;
        public String headImage;
        public String thirdId;
        public String email;
        public boolean isEmailVerified;
        public String emailKey;
        public Date emailOverTime;
        public String images;
        public int sexEnum;
        public String phone;
        public String cardNumber;
        public String country;
        public String province;
        public String city;
        public String zone;
        public String address;
        public String lastLoginIp;
        public Date lastLoginTime;
        public String lastLoginIpArea;
        public int status;
        public String comment;
        public List<Auth> auths;        
        public List<Depart> departs;        
        public List<Member> members;        

        public String validate() {
            if (StrUtil.isNull(loginName)) {
                return TableInfoReader.getFieldComment(User.class, "loginName") + "不能为空";
            }
            if (StrUtil.isNull(password)) {
                return TableInfoReader.getFieldComment(User.class, "password") + "不能为空";
            }

            return null;
        }
    }
    
    @Security.Authenticated(SecuredSuperAdmin.class)
    @MethodName("删除_User")
    @Role("dalete_user")
    public static Result delete(long id) {
        Msg<User> msg = new Msg<>();

        User found = User.find.byId(id);
        if (found != null) {
            Transaction txn = Ebean.beginTransaction();
            try{
                // 解除多对多的关联
                for (Auth auth : found.auths) {
                    auth.users.remove(found);
                    auth.save();
                }
                found.auths = new ArrayList<>();
                for (Depart depart : found.departs) {
                    depart.users.remove(found);
                    depart.save();
                }
                found.departs = new ArrayList<>();
                for (Member member : found.members) {
                    member.users.remove(found);
                    member.save();
                }
                found.members = new ArrayList<>();
                
                found.save();
                Ebean.delete(found);
                txn.commit();
                
                msg.flag = true;
                play.Logger.info("result: " + DELETE_SUCCESS);
                sendWebSocketByChannelTag("user_backend", "delete");
            } catch (PersistenceException ex){
                msg.message = DELETE_ISSUE + ", ex: " + ex.getMessage();
                play.Logger.error(msg.message);
            } finally {
                txn.end();
            }
        } else {
            msg.message = NO_FOUND;
            play.Logger.info("result: " + NO_FOUND);
        }
        return ok(Json.toJson(msg));
    }

    public static Result getNew() {
        Msg<User> msg = new Msg<>();
        msg.flag = true;
        msg.data = new User();
        return ok(Json.toJson(msg));
    }
    
    public static File getReportFile(String fileName,
                                     Integer status, Integer notStatus,
                                     String fieldOn, String fieldValue, boolean isAnd,
                                     String searchOn, String kw,
                                     String startTime, String endTime,
                                     String order, String sort) {
    
		// 创建工作薄对象
		HSSFWorkbook workbook2007 = new HSSFWorkbook();
		// 数据
                
        Msg<List<User>> msg = BaseController.doGetAll("User",
                status, notStatus,
                fieldOn, fieldValue, isAnd,
                searchOn, kw,
                startTime, endTime,
                "createdAt", "desc",
                1, User.find.findRowCount());
		List<User> list = msg.data;        

        if (list == null || list.size() == 0) return null;

		// 创建单元格样式
		HSSFCellStyle cellStyle = workbook2007.createCellStyle();
		// 设置边框属性
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// // 设置单元格字体
		HSSFFont font = workbook2007.createFont();
		font.setFontName("宋体");
		// 大小
		font.setFontHeightInPoints((short) 10);
		// 加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		HSSFCellStyle style = workbook2007.createCellStyle();
		// 指定单元格居中对齐
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font1 = workbook2007.createFont();
		font1.setFontName("宋体");
		font1.setFontHeightInPoints((short) 10);
		// 加粗
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font1);

		// 创建工作表对象，并命名
		HSSFSheet sheet2 = workbook2007.createSheet(TableInfoReader.getTableComment(User.class) + "报表");
		// 设置列
        sheet2.setColumnWidth(0, 4000);
        sheet2.setDefaultColumnStyle(0, cellStyle);//name
        sheet2.setColumnWidth(1, 4000);
        sheet2.setDefaultColumnStyle(1, cellStyle);//login_name
        sheet2.setColumnWidth(2, 4000);
        sheet2.setDefaultColumnStyle(2, cellStyle);//password
        sheet2.setColumnWidth(3, 4000);
        sheet2.setDefaultColumnStyle(3, cellStyle);//email
        sheet2.setColumnWidth(4, 4000);
        sheet2.setDefaultColumnStyle(4, cellStyle);//is_email_verified
        sheet2.setColumnWidth(5, 4000);
        sheet2.setDefaultColumnStyle(5, cellStyle);//email_key
        sheet2.setColumnWidth(6, 4000);
        sheet2.setDefaultColumnStyle(6, cellStyle);//email_over_time
        sheet2.setColumnWidth(7, 4000);
        sheet2.setDefaultColumnStyle(7, cellStyle);//sex_enum
        sheet2.setColumnWidth(8, 4000);
        sheet2.setDefaultColumnStyle(8, cellStyle);//phone
        sheet2.setColumnWidth(9, 4000);
        sheet2.setDefaultColumnStyle(9, cellStyle);//card_number
        sheet2.setColumnWidth(10, 4000);
        sheet2.setDefaultColumnStyle(10, cellStyle);//country
        sheet2.setColumnWidth(11, 4000);
        sheet2.setDefaultColumnStyle(11, cellStyle);//province
        sheet2.setColumnWidth(12, 4000);
        sheet2.setDefaultColumnStyle(12, cellStyle);//city
        sheet2.setColumnWidth(13, 4000);
        sheet2.setDefaultColumnStyle(13, cellStyle);//zone
        sheet2.setColumnWidth(14, 4000);
        sheet2.setDefaultColumnStyle(14, cellStyle);//address
        sheet2.setColumnWidth(15, 4000);
        sheet2.setDefaultColumnStyle(15, cellStyle);//last_update_time
        sheet2.setColumnWidth(16, 4000);
        sheet2.setDefaultColumnStyle(16, cellStyle);//created_at
        sheet2.setColumnWidth(17, 4000);
        sheet2.setDefaultColumnStyle(17, cellStyle);//last_login_ip
        sheet2.setColumnWidth(18, 4000);
        sheet2.setDefaultColumnStyle(18, cellStyle);//last_login_time
        sheet2.setColumnWidth(19, 4000);
        sheet2.setDefaultColumnStyle(19, cellStyle);//last_login_ip_area
        sheet2.setColumnWidth(20, 4000);
        sheet2.setDefaultColumnStyle(20, cellStyle);//status
        sheet2.setColumnWidth(21, 4000);
        sheet2.setDefaultColumnStyle(21, cellStyle);//comment


		// 创建表头
		HSSFRow title = sheet2.createRow(0);
		title.setHeightInPoints(50);
		title.createCell(0).setCellValue(TableInfoReader.getTableComment(User.class) + "报表");
        title.createCell(1).setCellValue("");
        title.createCell(2).setCellValue("");
        title.createCell(3).setCellValue("");
        title.createCell(4).setCellValue("");
        title.createCell(5).setCellValue("");
        title.createCell(6).setCellValue("");
        title.createCell(7).setCellValue("");
        title.createCell(8).setCellValue("");
        title.createCell(9).setCellValue("");
        title.createCell(10).setCellValue("");
        title.createCell(11).setCellValue("");
        title.createCell(12).setCellValue("");
        title.createCell(13).setCellValue("");
        title.createCell(14).setCellValue("");
        title.createCell(15).setCellValue("");
        title.createCell(16).setCellValue("");
        title.createCell(17).setCellValue("");
        title.createCell(18).setCellValue("");
        title.createCell(19).setCellValue("");
        title.createCell(20).setCellValue("");
        title.createCell(21).setCellValue("");
        sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 21));
		HSSFCell ce = title.createCell((short) 1);

		HSSFRow titleRow = sheet2.createRow(1);
        
		// 设置行高
		titleRow.setHeightInPoints(30);
        titleRow.createCell(0).setCellValue(TableInfoReader.getFieldComment(User.class, "name"));//name
        titleRow.createCell(1).setCellValue(TableInfoReader.getFieldComment(User.class, "loginName"));//login_name
        titleRow.createCell(2).setCellValue(TableInfoReader.getFieldComment(User.class, "password"));//password
        titleRow.createCell(3).setCellValue(TableInfoReader.getFieldComment(User.class, "email"));//email
        titleRow.createCell(4).setCellValue(TableInfoReader.getFieldComment(User.class, "isEmailVerified"));//is_email_verified
        titleRow.createCell(5).setCellValue(TableInfoReader.getFieldComment(User.class, "emailKey"));//email_key
        titleRow.createCell(6).setCellValue(TableInfoReader.getFieldComment(User.class, "emailOverTime"));//email_over_time
        titleRow.createCell(7).setCellValue(TableInfoReader.getFieldComment(User.class, "sexEnum"));//sex_enum
        titleRow.createCell(8).setCellValue(TableInfoReader.getFieldComment(User.class, "phone"));//phone
        titleRow.createCell(9).setCellValue(TableInfoReader.getFieldComment(User.class, "cardNumber"));//card_number
        titleRow.createCell(10).setCellValue(TableInfoReader.getFieldComment(User.class, "country"));//country
        titleRow.createCell(11).setCellValue(TableInfoReader.getFieldComment(User.class, "province"));//province
        titleRow.createCell(12).setCellValue(TableInfoReader.getFieldComment(User.class, "city"));//city
        titleRow.createCell(13).setCellValue(TableInfoReader.getFieldComment(User.class, "zone"));//zone
        titleRow.createCell(14).setCellValue(TableInfoReader.getFieldComment(User.class, "address"));//address
        titleRow.createCell(15).setCellValue(TableInfoReader.getFieldComment(User.class, "lastUpdateTime"));//last_update_time
        titleRow.createCell(16).setCellValue(TableInfoReader.getFieldComment(User.class, "createdAt"));//created_at
        titleRow.createCell(17).setCellValue(TableInfoReader.getFieldComment(User.class, "lastLoginIp"));//last_login_ip
        titleRow.createCell(18).setCellValue(TableInfoReader.getFieldComment(User.class, "lastLoginTime"));//last_login_time
        titleRow.createCell(19).setCellValue(TableInfoReader.getFieldComment(User.class, "lastLoginIpArea"));//last_login_ip_area
        titleRow.createCell(20).setCellValue(TableInfoReader.getFieldComment(User.class, "status"));//status
        titleRow.createCell(21).setCellValue(TableInfoReader.getFieldComment(User.class, "comment"));//comment
		HSSFCell ce2 = title.createCell((short) 2);
		ce2.setCellStyle(cellStyle); // 样式，居中

		// 遍历集合对象创建行和单元格
        int i;
		for (i = 0; i < list.size(); i++) {
			// 取出对象
			User item = list.get(i);
			// 创建行
			HSSFRow row = sheet2.createRow(i + 2);
			// 创建单元格并赋值
            HSSFCell cell0 = row.createCell(0);
            if (item.name == null) {
                cell0.setCellValue("");
            } else {
                cell0.setCellValue(item.name);
            }
            HSSFCell cell1 = row.createCell(1);
            if (item.loginName == null) {
                cell1.setCellValue("");
            } else {
                cell1.setCellValue(item.loginName);
            }
            HSSFCell cell2 = row.createCell(2);
            if (item.password == null) {
                cell2.setCellValue("");
            } else {
                cell2.setCellValue(item.password);
            }
            HSSFCell cell3 = row.createCell(3);
            if (item.email == null) {
                cell3.setCellValue("");
            } else {
                cell3.setCellValue(item.email);
            }
            HSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(item.isEmailVerified ? "是" : "否");
            HSSFCell cell5 = row.createCell(5);
            if (item.emailKey == null) {
                cell5.setCellValue("");
            } else {
                cell5.setCellValue(item.emailKey);
            }
            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(DateUtil.Date2Str(item.emailOverTime));
            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(EnumInfoReader.getEnumName(User.class, "sexEnum", item.sexEnum));
            HSSFCell cell8 = row.createCell(8);
            if (item.phone == null) {
                cell8.setCellValue("");
            } else {
                cell8.setCellValue(item.phone);
            }
            HSSFCell cell9 = row.createCell(9);
            if (item.cardNumber == null) {
                cell9.setCellValue("");
            } else {
                cell9.setCellValue(item.cardNumber);
            }
            HSSFCell cell10 = row.createCell(10);
            if (item.country == null) {
                cell10.setCellValue("");
            } else {
                cell10.setCellValue(item.country);
            }
            HSSFCell cell11 = row.createCell(11);
            if (item.province == null) {
                cell11.setCellValue("");
            } else {
                cell11.setCellValue(item.province);
            }
            HSSFCell cell12 = row.createCell(12);
            if (item.city == null) {
                cell12.setCellValue("");
            } else {
                cell12.setCellValue(item.city);
            }
            HSSFCell cell13 = row.createCell(13);
            if (item.zone == null) {
                cell13.setCellValue("");
            } else {
                cell13.setCellValue(item.zone);
            }
            HSSFCell cell14 = row.createCell(14);
            if (item.address == null) {
                cell14.setCellValue("");
            } else {
                cell14.setCellValue(item.address);
            }
            HSSFCell cell15 = row.createCell(15);
            cell15.setCellValue(DateUtil.Date2Str(item.lastUpdateTime));
            HSSFCell cell16 = row.createCell(16);
            cell16.setCellValue(DateUtil.Date2Str(item.createdAt));
            HSSFCell cell17 = row.createCell(17);
            if (item.lastLoginIp == null) {
                cell17.setCellValue("");
            } else {
                cell17.setCellValue(item.lastLoginIp);
            }
            HSSFCell cell18 = row.createCell(18);
            cell18.setCellValue(DateUtil.Date2Str(item.lastLoginTime));
            HSSFCell cell19 = row.createCell(19);
            if (item.lastLoginIpArea == null) {
                cell19.setCellValue("");
            } else {
                cell19.setCellValue(item.lastLoginIpArea);
            }
            HSSFCell cell20 = row.createCell(20);
            cell20.setCellValue(EnumInfoReader.getEnumName(User.class, "status", item.status));
            HSSFCell cell21 = row.createCell(21);
            if (item.comment == null) {
                cell21.setCellValue("");
            } else {
                cell21.setCellValue(item.comment);
            }
		}

		// 生成文件
		String path = Play.application().path().getPath() + "/public/report/" + fileName;
		File file = new File(path);
        
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			workbook2007.write(fos);
		} catch (Exception e) {
            play.Logger.error("生成报表出错: " + e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
                    play.Logger.error("生成报表出错, 关闭流出错: " + e.getMessage());
				}
			}
		}
        return file;
    }

    @Security.Authenticated(SecuredAdmin.class)
    @MethodName("导出报表_User")
    @Role("report_user")
	public static Result report(Integer status, Integer notStatus,
                                String fieldOn, String fieldValue, boolean isAnd,
                                String searchOn, String kw,
                                String startTime, String endTime,
                                String order, String sort) {
                                
		String fileName = TableInfoReader.getTableComment(User.class) + "报表_" + DateUtil.NowString("yyyy_MM_dd_HH_mm_ss") + ".xls";
        File file = getReportFile(fileName, status, notStatus, fieldOn, fieldValue, isAnd, searchOn, kw,
                startTime, endTime, order, sort);

        if (file == null) {
            if (StrUtil.isNotNull(startTime) && StrUtil.isNotNull(endTime)) {
                return ok("日期: " + startTime + " 至 " + endTime + ", 报表" + NO_FOUND + ", 请返回重试!");
            }
            return ok(NO_FOUND);
        }
        
        // 处理中文报表名
        String agent = request().getHeader("USER-AGENT");
        String downLoadName = null;
        try {
            if (null != agent && -1 != agent.indexOf("MSIE"))   //IE
            {
                downLoadName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) //Firefox
            {
                downLoadName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            } else {
                downLoadName = java.net.URLEncoder.encode(fileName, "UTF-8");
            }
        } catch (UnsupportedEncodingException ex) {
            play.Logger.error("导出报表处理中文报表名出错: " + ex.getMessage());
        }
        if (downLoadName != null) {
            response().setHeader("Content-disposition", "attachment;filename="
                    + downLoadName);
            response().setContentType("application/vnd.ms-excel;charset=UTF-8");
        }
        
		return ok(file);
	}
}
