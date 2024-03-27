package cn.demo.model.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@TableName(value ="Bexcel")
@Data
public class Bexcel implements Serializable {
    @ExcelProperty
    @TableId
    private String A;

    @ExcelProperty
    private String B;

    @ExcelProperty
    private String C;

    @ExcelProperty
    private String D;

    @ExcelProperty
    private String E;

    @ExcelProperty
    private String F;

    @ExcelProperty
    private Integer aa;

    @ExcelProperty
    private Integer Trueaa;

    @ExcelProperty
    private Integer bb;

    @ExcelProperty
    private Integer Truebb;

    @ExcelProperty
    private Integer cc;

    @ExcelProperty
    private Integer Truecc;

    @ExcelProperty
    private Integer dd;

    @ExcelProperty
    private Integer Truedd;

    @ExcelProperty
    private Integer ee;

    @ExcelProperty
    private Integer Trueee;

    private static final long serialVersionUID = 1L;
}