package com.liangs.test.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 权限用户表
 * </p>
 *
 * @author Liangs
 * @since 2018-02-06
 */
@TableName("PT_QX_USER")
public class PtQxUser extends Model<PtQxUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("USERID")
    private String userid;
    /**
     * 登录账号
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;
    /**
     * 人员类型；1正式；2非正式；3兼职
     */
    @TableField("MTYPE")
    private Integer mtype;
    /**
     * 安全级别
     */
    @TableField("SECLEVEL")
    private Integer seclevel;
    /**
     * 创建人
     */
    @TableField("CREATENAME")
    private String createname;
    /**
     * 修改人
     */
    @TableField("UPDATENAME")
    private String updatename;
    /**
     * 创建时间
     */
    @TableField("CREATETIME")
    private Date createtime;
    /**
     * 修改时间
     */
    @TableField("UPDATETIME")
    private Date updatetime;
    /**
     * 性别（0：男  1：女）
     */
    @TableField("SEX")
    private String sex;
    /**
     * 手机号
     */
    @TableField("PHONE")
    private String phone;
    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;
    /**
     * 登录名
     */
    @TableField("LOGINNAME")
    private String loginname;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public Integer getSeclevel() {
        return seclevel;
    }

    public void setSeclevel(Integer seclevel) {
        this.seclevel = seclevel;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @Override
    protected Serializable pkVal() {
        return this.userid;
    }

    @Override
    public String toString() {
        return "PtQxUser{" +
        ", userid=" + userid +
        ", username=" + username +
        ", password=" + password +
        ", mtype=" + mtype +
        ", seclevel=" + seclevel +
        ", createname=" + createname +
        ", updatename=" + updatename +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        ", sex=" + sex +
        ", phone=" + phone +
        ", email=" + email +
        ", loginname=" + loginname +
        "}";
    }
}
