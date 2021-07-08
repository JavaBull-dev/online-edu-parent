package cn.ljpc.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jacker
 * @Description 自定义异常
 * @create 2021-06-15 14:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyException extends RuntimeException {

    private Integer code;//异常状态码
    private String msg;//异常信息
}