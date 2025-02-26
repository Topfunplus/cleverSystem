package com.ruoyi.common.core.domain.model;

/**
 * 用户注册对象
 *
 * @author ruoyi
 */
public class RegisterBody extends LoginBody {

    private String phonenumber;

    private String idCard;

    public RegisterBody() {
    }

    public RegisterBody(String phonenumber, String idCard) {
        this.phonenumber = phonenumber;
        this.idCard = idCard;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
