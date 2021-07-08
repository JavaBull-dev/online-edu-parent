package cn.ljpc.servicebase.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-15 19:53
 */
public class ExceptionUtil {

    public static String getMessage(Exception e) {
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)) {
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
