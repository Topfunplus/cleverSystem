package com.ruoyi.common.core.domain.model;

/**
 * 用户登录对象
 *
 * @author ruoyi
 */
public class LoginBody {
    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;
    /**
     * 用户密码
     */
    private String password;


    // 需要添加 手机号
    private String phonenumber;
    private String username;

    // 身份证号
    private String idCard;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public LoginBody(String phonenumber, String idCard) {
        this.phonenumber = phonenumber;
        this.idCard = idCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 构造函数
    public LoginBody() {
    }

    public LoginBody(String code, String uuid, String password, String phonenumber, String username, String idCard) {
        this.code = code;
        this.uuid = uuid;
        this.password = password;
        this.phonenumber = phonenumber;
        this.username = username;
        this.idCard = idCard;
    }
}
