package cn.ljpc.demo.execel;

import cn.ljpc.demo.entity.DemoData;
import cn.ljpc.demo.entity.ReadData;
import cn.ljpc.demo.listener.ExcelListener;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 22:07
 */
public class EasyExecelTest {

    @Test
    public void testRead() {
        String path = "C:\\Users\\Jacker\\Desktop\\student.xlsx";
        EasyExcel.read(path, ReadData.class, new ExcelListener()).sheet().doRead();
    }

    @Test
    public void testWrite() {
        String path = "C:\\Users\\Jacker\\Desktop\\student.xlsx";
        EasyExcel.write(path, DemoData.class).sheet("学生信息表格").doWrite(getList());
    }

    public List<DemoData> getList() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setAge(i + 20);
            demoData.setSname("jacker" + i);
            list.add(demoData);
        }

        return list;
    }
}
