package com.bysj.common.exception;

import com.bysj.common.response.RespBasicCode;

/**
 * 自定义异常类，默认情况下状态吗为401
 */
public class BussinessException extends RuntimeException{

    private String code = RespBasicCode.BUSSINESS_EXCETION.getResultCode();


    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
