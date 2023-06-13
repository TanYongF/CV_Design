package fun.tans.exception;


/**
 * @author tyf
 * @description 务接口类
 * @date 18:26 2023/6/12
 **/
public interface BaseErrorInfoInterface {

    /**
     * 错误码
     *
     * @return
     */
    String getResultCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getResultMsg();
}