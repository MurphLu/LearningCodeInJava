package org.xls.xlsReadListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.xls.entity.PriceData;

import java.util.*;

public class PriceDataReadListener implements ReadListener<PriceData> {
    Map<String, List<PriceData>> innerCodeCount = new HashMap<>();

    @Override
    public void invoke(PriceData data, AnalysisContext context) {
        if(data.getInnerCode()!=null) {
            data.setLineNo(context.readRowHolder().getRowIndex());
            List<PriceData> list = innerCodeCount.getOrDefault(data.getInnerCode(), new ArrayList<>());
            list.add(data);
            innerCodeCount.put(data.getInnerCode(), list);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public void printInfo() {
        System.out.println(innerCodeCount);
    }

    public Set<String> getDuplicated() {
        Set<String> set = new HashSet<>();
        for(Map.Entry<String, List<PriceData>> entry:innerCodeCount.entrySet()) {
            if(entry.getValue().size() > 1) {
                set.add(entry.getKey());
            }
        }
        return set;
    }
}
