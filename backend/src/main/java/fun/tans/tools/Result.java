package fun.tans.tools;

import fun.tans.exception.BaseErrorInfoInterface;
import fun.tans.exception.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(BaseErrorInfoInterface errorInfo) {
        this.code = Integer.parseInt(errorInfo.getResultCode());
        this.msg = errorInfo.getResultMsg();
    }


    private Result(BaseErrorInfoInterface errorInfo, T data) {
        this.code = Integer.parseInt(errorInfo.getResultCode());
        this.msg = errorInfo.getResultMsg();
        if (data.getClass() != ExceptionEnum.class) this.data = data;
    }

    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ExceptionEnum.SUCCESS, data);
    }


    /**
     * 失败
     */
    public static <T> Result<T> error(BaseErrorInfoInterface errorInfo) {
        Result<T> result = new Result<>(errorInfo);
        result.setData(null);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(null);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<T>();
        result.setCode(-1);
        result.setMsg(message);
        result.setData(null);
        return result;
    }
}