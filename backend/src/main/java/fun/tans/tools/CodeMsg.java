package fun.tans.tools;

/**
 * @author tyf
 * @description 通用信息码
 * @date 8:33 2022/5/17
 **/

@Deprecated
public class CodeMsg {

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "成功！");
    public static CodeMsg REGISTER_SUCCESS = new CodeMsg(200001, "注册成功！");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    /**
     * 用户未登录或者用户Session中不存在相应的Token。
     */
    //登录模块 5002XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "登陆已过期，请重新登陆");

    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg LOGOUT_SUCCESS = new CodeMsg(500214, "登出成功！");
    public static CodeMsg MOBILE_HAS_REGISTER = new CodeMsg(510213, "该手机号码已被注册");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");

    public static CodeMsg NO_LOGIN = new CodeMsg(500216, "用户未登陆");

    // 数据模块 5003XX
    public static CodeMsg UPDATE_BSR_SUCCESSFUL = new CodeMsg(500300, "添加血糖数据成功");
    public static CodeMsg UPDATE_IR_SUCCESSFUL = new CodeMsg(500300, "添加胰岛素数据成功");

    // 数据模块 5003XX
    public static CodeMsg JOIN_ACTIVITY_SUCCESSFUL = new CodeMsg(500400, "参加活动成功");
    public static CodeMsg ACTIVITY_HAVE_JOINED = new CodeMsg(500401, "已经参加过该活动");


    private int code;
    private String msg;

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回带参数的返回码
     *
     * @param args
     * @return
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }


}