package org.xls.reader;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.xls.entity.PriceData;

import java.io.InputStream;

public class XlsHandler {
    public static void handle(InputStream is, ReadListener listener) {
        try(ExcelReader er = EasyExcel.read(is).excelType(ExcelTypeEnum.XLS).build()) {
            ReadSheet readSheet1 = EasyExcel.readSheet(0).head(PriceData.class).registerReadListener(listener).build();
            er.read(readSheet1);
        }
    }
}
