package com.ruoyi.common.core.domain.model;

/**
 * 用户注册对象
 *
 * @author ruoyi
 */
public class RegisterBody extends LoginBody {


    public RegisterBody() {
    }

    public RegisterBody(String phonenumber, String idCard) {
        super(phonenumber, idCard);
    }


}
