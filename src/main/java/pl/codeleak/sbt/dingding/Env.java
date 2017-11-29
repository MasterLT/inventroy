package pl.codeleak.sbt.dingding;


/**
 * 企业应用接入时的常量定义
 */
public class Env {

    /**
     * 企业应用接入秘钥相关
     */
    public static final String CORP_ID = "ding00fa2f34238dfc1d";
    public static final String CORP_SECRET = "BsQMnywGBAjMFQoYcVs0iJRXdK-KncQ-XupwHRsZezajKyX5Uc1Y1juXbpQ9cAGF";
    public static final String AGENT_ID = "134027113";
    public static final String SSO_Secret = "";

    /**
     * DING API地址
     */
	public static final String OAPI_HOST = "https://oapi.dingtalk.com";
    /**
     * 企业应用后台地址，用户管理后台免登使用
     */
	public static final String OA_BACKGROUND_URL = "";


    /**
     * 企业通讯回调加密Token，注册事件回调接口时需要传递给钉钉服务器
     */
	public static final String TOKEN = "";
	public static final String ENCODING_AES_KEY = "";
	
}
