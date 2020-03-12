package com.demo.qywx.wx;

public enum WxApi {
    GET_TOKEN("/cgi-bin/gettoken");

    private String path;

    WxApi(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
