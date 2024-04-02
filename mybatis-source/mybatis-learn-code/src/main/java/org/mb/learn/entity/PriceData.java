package org.mb.learn.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode
public class PriceData {
    @ExcelProperty("编码")
    private String code;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("物价编码")
    private String innerCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    @Override
    public String toString() {
        return "PriceData{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", innerCode='" + innerCode + '\'' +
                '}';
    }
}
