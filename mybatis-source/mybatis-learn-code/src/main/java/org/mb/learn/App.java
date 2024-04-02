package org.mb.learn;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.ibatis.io.Resources;
import org.mb.learn.entity.PriceData;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class App {
    static int counter = 1;
    public static void main(String[] args) throws IOException {
        Map<String, Integer> count = new HashMap<>();

        InputStream is = Resources.getResourceAsStream("t.xlsx");
        try(ExcelReader er = EasyExcel.read(is).excelType(ExcelTypeEnum.XLSX).build()) {
            ReadSheet readSheet1 = EasyExcel.readSheet(0).head(PriceData.class).registerReadListener(new ReadListener<PriceData>() {
                @Override
                public void invoke(PriceData priceData, AnalysisContext analysisContext) {
                    System.out.println(analysisContext.readRowHolder().getRowIndex());
                    System.out.println(priceData);
                    System.out.println(counter++);
                    count.put(priceData.getInnerCode(), count.getOrDefault(priceData.getInnerCode(), 0) + 1);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                }
            }).build();
            er.read(readSheet1);
        }

//
//        EasyExcel.read(is, PriceData.class, new ReadListener<PriceData>() {
//            public static final int BATCH_COUNT = 100;
//            /**
//             *临时存储
//             */
//            private List<PriceData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//
//            @Override
//            public void invoke(PriceData o, AnalysisContext analysisContext) {
//                System.out.println(o);
//                System.out.println(counter++);
//                count.put(o.getInnerCode(), count.getOrDefault(o.getInnerCode(), 0) + 1);
//                cachedDataList.add(o);
//                if (cachedDataList.size() >= BATCH_COUNT) {
//                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//                }
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//
//            }
//
//        }).sheet().doRead();
        System.out.println(count);
    }
}
