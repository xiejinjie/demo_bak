package com.demo.qywx.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信响应体
 */
public abstract class WxResponse {

    @JsonProperty("errcode")
    protected String errCode;
    @JsonProperty("errmsg")
    protected String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
