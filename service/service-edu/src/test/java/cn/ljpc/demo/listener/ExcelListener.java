package cn.ljpc.demo.listener;

import cn.ljpc.demo.entity.ReadData;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author Jacker
 * @Description
 * @create 2021-06-17 22:16
 */
public class ExcelListener extends AnalysisEventListener<ReadData> {

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息"+headMap);
    }

    //一行一行去读取excel内容
    @Override
    public void invoke(ReadData readData, AnalysisContext analysisContext) {
        System.out.println("============"+ readData);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完成后执行");
    }
}
