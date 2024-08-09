package com.jingli.inter.entity;

import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表;
 * @author : Abdulla
 * @date : 2023-8-25
 */
@ApiModel(value = "用户表",description = "")
public class TUser implements Serializable,Cloneable{
    /** 用户表主键id */
    @ApiModelProperty(name = "用户表主键id",notes = "")
    @JsonIgnore
    private Integer ID ;
    /** 微信openID */
    @ApiModelProperty(name = "微信openID",notes = "")
    private String weixinOpenid ;
    /** 手机号 */
    @ApiModelProperty(name = "手机号",notes = "")
    private String mobile ;
    /** 角色id */
    @ApiModelProperty(name = "角色id",notes = "")
    private Double roleId ;
    /** 用户头像 */
    @ApiModelProperty(name = "用户头像",notes = "")
    private String headImage ;
    /** 用户昵称 */
    @ApiModelProperty(name = "用户昵称",notes = "")
    private String userName ;
    /** 密码 */
    @ApiModelProperty(name = "密码",notes = "")
    private String password ;
    /** 真实姓名 */
    @ApiModelProperty(name = "真实姓名",notes = "")
    private String realName ;
    /** 卡号 */
    @ApiModelProperty(name = "卡号",notes = "")
    private String cardId ;
    /** 部门ID */
    @ApiModelProperty(name = "部门ID",notes = "")
    private String departid ;
    /** 用户账户 */
    @ApiModelProperty(name = "用户账户",notes = "")
    private String useraccount ;
    /**  */
    @ApiModelProperty(name = "userorder",notes = "")
    private Double userorder ;
    /** 是否有效  默认Y有效  N无效 */
    @ApiModelProperty(name = "是否有效  默认Y有效  N无效",notes = "")
    private String isvalid ;
    /**  */
    @ApiModelProperty(name = "swryDm",notes = "")
    private String swryDm ;
    /**  */
    @ApiModelProperty(name = "czryDm",notes = "")
    private String czryDm ;
    /**  */
    @ApiModelProperty(name = "czryMc",notes = "")
    private String czryMc ;
    /**  */
    @ApiModelProperty(name = "provincecode",notes = "")
    private String provincecode ;
    /**  */
    @ApiModelProperty(name = "regioncode",notes = "")
    private String regioncode ;
    /**  */
    @ApiModelProperty(name = "zd",notes = "")
    private String zd ;
    /** 是否管理员 N不是 Y 是 */
    @ApiModelProperty(name = "是否管理员 N不是 Y 是",notes = "")
    private String admin ;
    /**  */
    @ApiModelProperty(name = "zdmobile",notes = "")
    private String zdmobile ;
    /**  */
    @ApiModelProperty(name = "zdkssj",notes = "")
    private String zdkssj ;
    /**  */
    @ApiModelProperty(name = "zdjssj",notes = "")
    private String zdjssj ;
    /**  */
    @ApiModelProperty(name = "je",notes = "")
    private String je ;
    /** Y审核通过，N审核未通过 */
    @ApiModelProperty(name = "Y审核通过，N审核未通过",notes = "")
    private String shzt ;
    /**  */
    @ApiModelProperty(name = "createtime",notes = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime ;
    /**  */
    @ApiModelProperty(name = "parkid",notes = "")
    private String parkid ;

    private String openId;

    private String unionId;

    private String appOpenId;

    public TUser(String userOpenId, String mobile, String password) {
        this.mobile = mobile;
        this.weixinOpenid = userOpenId;
        this.userName = ("GT"+ RandomUtil.randomString(8));
        this.password = password;
        this.departid = "1001";
        this.userorder = 0.0;
        this.isvalid = "Y";
        this.admin = "N";
        this.je = "0";
        this.createtime = new Date();

    }

    public TUser() {

    }

    /** 用户表主键id */
    public Integer getId(){
        return this.ID;
    }
    /** 用户表主键id */
    public void setId(Integer ID){
        this.ID=ID;
    }
    /** 微信openID */
    public String getWeixinOpenid(){
        return this.weixinOpenid;
    }
    /** 微信openID */
    public void setWeixinOpenid(String weixinOpenid){
        this.weixinOpenid=weixinOpenid;
    }
    /** 手机号 */
    public String getMobile(){
        return this.mobile;
    }
    /** 手机号 */
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    /** 角色id */
    public Double getRoleId(){
        return this.roleId;
    }
    /** 角色id */
    public void setRoleId(Double roleId){
        this.roleId=roleId;
    }
    /** 用户头像 */
    public String getHeadImage(){
        return this.headImage;
    }
    /** 用户头像 */
    public void setHeadImage(String headImage){
        this.headImage=headImage;
    }
    /** 用户昵称 */
    public String getUserName(){
        return this.userName;
    }
    /** 用户昵称 */
    public void setUserName(String userName){
        this.userName=userName;
    }
    /** 密码 */
    public String getPassword(){
        return this.password;
    }
    /** 密码 */
    public void setPassword(String password){
        this.password=password;
    }
    /** 真实姓名 */
    public String getRealName(){
        return this.realName;
    }
    /** 真实姓名 */
    public void setRealName(String realName){
        this.realName=realName;
    }
    /** 卡号 */
    public String getCardId(){
        return this.cardId;
    }
    /** 卡号 */
    public void setCardId(String cardId){
        this.cardId=cardId;
    }
    /** 部门ID */
    public String getDepartid(){
        return this.departid;
    }
    /** 部门ID */
    public void setDepartid(String departid){
        this.departid=departid;
    }
    /** 用户账户 */
    public String getUseraccount(){
        return this.useraccount;
    }
    /** 用户账户 */
    public void setUseraccount(String useraccount){
        this.useraccount=useraccount;
    }
    /**  */
    public Double getUserorder(){
        return this.userorder;
    }
    /**  */
    public void setUserorder(Double userorder){
        this.userorder=userorder;
    }
    /** 是否有效  默认Y有效  N无效 */
    public String getIsvalid(){
        return this.isvalid;
    }
    /** 是否有效  默认Y有效  N无效 */
    public void setIsvalid(String isvalid){
        this.isvalid=isvalid;
    }
    /**  */
    public String getSwryDm(){
        return this.swryDm;
    }
    /**  */
    public void setSwryDm(String swryDm){
        this.swryDm=swryDm;
    }
    /**  */
    public String getCzryDm(){
        return this.czryDm;
    }
    /**  */
    public void setCzryDm(String czryDm){
        this.czryDm=czryDm;
    }
    /**  */
    public String getCzryMc(){
        return this.czryMc;
    }
    /**  */
    public void setCzryMc(String czryMc){
        this.czryMc=czryMc;
    }
    /**  */
    public String getProvincecode(){
        return this.provincecode;
    }
    /**  */
    public void setProvincecode(String provincecode){
        this.provincecode=provincecode;
    }
    /**  */
    public String getRegioncode(){
        return this.regioncode;
    }
    /**  */
    public void setRegioncode(String regioncode){
        this.regioncode=regioncode;
    }
    /**  */
    public String getZd(){
        return this.zd;
    }
    /**  */
    public void setZd(String zd){
        this.zd=zd;
    }
    /** 是否管理员 N不是 Y 是 */
    public String getAdmin(){
        return this.admin;
    }
    /** 是否管理员 N不是 Y 是 */
    public void setAdmin(String admin){
        this.admin=admin;
    }
    /**  */
    public String getZdmobile(){
        return this.zdmobile;
    }
    /**  */
    public void setZdmobile(String zdmobile){
        this.zdmobile=zdmobile;
    }
    /**  */
    public String getZdkssj(){
        return this.zdkssj;
    }
    /**  */
    public void setZdkssj(String zdkssj){
        this.zdkssj=zdkssj;
    }
    /**  */
    public String getZdjssj(){
        return this.zdjssj;
    }
    /**  */
    public void setZdjssj(String zdjssj){
        this.zdjssj=zdjssj;
    }
    /**  */
    public String getJe(){
        return this.je;
    }
    /**  */
    public void setJe(String je){
        this.je=je;
    }
    /** Y审核通过，N审核未通过 */
    public String getShzt(){
        return this.shzt;
    }
    /** Y审核通过，N审核未通过 */
    public void setShzt(String shzt){
        this.shzt=shzt;
    }
    /**  */
    public Date getCreatetime(){
        return this.createtime;
    }
    /**  */
    public void setCreatetime(Date createtime){
        this.createtime=createtime;
    }
    /**  */
    public String getParkid(){
        return this.parkid;
    }
    /**  */
    public void setParkid(String parkid){
        this.parkid=parkid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }


    public String getAppOpenId() {
        return appOpenId;
    }

    public void setAppOpenId(String appOpenId) {
        this.appOpenId = appOpenId;
    }
}