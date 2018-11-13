package com.glg.baselib.net;

/**
 * Created by glg on 16/3/10.
 *
 * 自定义异常，根据错误码，抛出错误信息
 *
 *
 */
public class ApiException extends RuntimeException {


//    public static final int INVALIDACTION = 1001;
//    public static final int INVALID_OPERATING = 1002;
//    public static final int ERRORSERVER = 1003;
//    public static final int ERRORCODES = 1004;
//
//    public static final int PWD = 2001;
//    public static final int USER = 2002;
//
//    public static final int NO_SUCH_DID = 9003;
//    public static final int DID_MUST_NEED = 9005;
//    public static final int NO_SUCH_UID = 9006;
//    public static final int USER_UNREGISTERED = 9007;
//    public static final int UID_MUST_NEED = 9008;
//    public static final int USER_STATE_ERROR = 9009;


    public String code;
    public String message;

    public ApiException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }


//    public ApiException(int resultCode) {
//        this(getApiExceptionMessage(resultCode));
//    }
//
//    public ApiException(String detailMessage) {
//        super(detailMessage);
//    }
//
//    public ApiException() {
//        super();
//    }
//    /**
//     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
//     * 需要根据错误码对错误信息进行一个转换，在显示给用户
//     * @param code
//     * @return
//     */
//    private static String getApiExceptionMessage(int code){
//        String message = "";
//        switch (code) {
//            case INVALIDACTION:
//                message = "非法接口";
//                break;
//            case INVALID_OPERATING:
//                message = "参数错误";
//                break;
//
//            case ERRORSERVER:
//                message = "服务器错误";
//                break;
//
//            case ERRORCODES:
//                message = "错误验证码";
//                break;
//
//            case PWD:
//                message = "密码错误";
//                break;
//
//            case USER:
//                message = "用户名错误";
//                break;
//
//            case NO_SUCH_DID:
//                message = "无该设备ID";
//                break;
//
//            case DID_MUST_NEED:
//                message = "设备ID是必须的";
//                break;
//
//            case NO_SUCH_UID:
//                message = "无该用户ID";
//                break;
//
//            case UID_MUST_NEED:
//                message = "用户ID是必须的";
//                break;
//
//            case USER_UNREGISTERED:
//                message = "用户未注册";
//                break;
//
//            case USER_STATE_ERROR:
//                message = "用户状态有误";
//                break;
//            default:
//                message = "未知错误";
//
//        }
//        return message;
//    }
}

