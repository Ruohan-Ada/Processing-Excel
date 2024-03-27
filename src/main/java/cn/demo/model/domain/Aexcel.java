package cn.demo.model.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName Aexcel
 */
@TableName(value ="Aexcel")
@Data
public class Aexcel implements Serializable {
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
    private Integer bb;

    @ExcelProperty
    private Integer cc;

    @ExcelProperty
    private Integer dd;

    @ExcelProperty
    private Integer ee;

    private static final long serialVersionUID = 1L;
}