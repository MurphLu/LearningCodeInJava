package org.xls.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PriceData {
    @ExcelProperty("编码")
    private String code;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("物价编码")
    private String innerCode;
    private int lineNo;
}
