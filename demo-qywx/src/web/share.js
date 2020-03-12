var signUrl = "https://www.kcoder.top/share/";
var ticket = "";
/*
 * 注意：
 * 所有的JS接口只能在应用配置的安全域名下面使用。
 *
 */
var jsApiList = [
    "checkJsApi",
    "onMenuShareAppMessage",
    "onMenuShareWechat",
    "onMenuShareTimeline",
    "shareAppMessage",
    "shareWechatMessage",
    "startRecord",
    "stopRecord",
    "onVoiceRecordEnd",
    "playVoice",
    "pauseVoice",
    "stopVoice",
    "uploadVoice",
    "downloadVoice",
    "chooseImage",
    "previewImage",
    "uploadImage",
    "downloadImage",
    "getNetworkType",
    "openLocation",
    "getLocation",
    "hideOptionMenu",
    "showOptionMenu",
    "hideMenuItems",
    "showMenuItems",
    "hideAllNonBaseMenuItem",
    "showAllNonBaseMenuItem",
    "closeWindow",
    "scanQRCode",
    "previewFile",
    "openEnterpriseChat",
    "selectEnterpriseContact",
    "onHistoryBack",
    "openDefaultBrowser"
]
wx.config({
    debug: true,
    beta: true,
    // TODO 这部分信息需要重新配置 获取企业的jsapi_ticket
    // appId: 'ww81b13801f9314220',
    // timestamp: 1563762025,
    // nonceStr: 'Pb81ARGbnZAvLIC',
    // signature: 'e5407d1327200c59177f16d0b887471e69915194',
    appId: "ww81b13801f9314220",
    timestamp: 1563762025,
    nonceStr: "Pb81ARGbnZAvLIC",
    signature: "e5407d1327200c59177f16d0b887471e69915194",
    jsApiList: jsApiList,
    success: function (res) {
        alert("config success:", JSON.stringify(res))
    },
    fail: function (res) {
        alert("config err:", JSON.stringify(res))
    }
})
