package cn.ljpc.servicebase.handler;

import cn.ljpc.commonutils.CommonResult;
import cn.ljpc.servicebase.exception.MyException;
import cn.ljpc.servicebase.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jacker
 * @Description 统一的异常处理
 * @create 2021-06-15 11:52
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 处理所有异常
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult catchException(Exception e) {
        e.printStackTrace();
        //日志
        log.error(ExceptionUtil.getMessage(e));
        return CommonResult.error().message("统一的异常处理");
    }

    //处理特定异常
    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public CommonResult catchException(ArithmeticException e) {
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return CommonResult.error().message("ArithmeticException异常处理");
    }

    //自定义异常
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public CommonResult catchException(MyException e) {
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return CommonResult.error().code(e.getCode()).message(e.getMsg());
    }
}
