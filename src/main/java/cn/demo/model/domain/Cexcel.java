package cn.demo.model.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName Cexcel
 */
@TableName(value ="Cexcel")
@Data
public class Cexcel implements Serializable {
    @ExcelProperty
    @TableId
    private String B;

    @ExcelProperty
    private float aaS;

    @ExcelProperty
    private float bbS;

    @ExcelProperty
    private float ccS;

    @ExcelProperty
    private float aaA;

    @ExcelProperty
    private float bbA;

    @ExcelProperty
    private float ccA;

    @ExcelProperty
    private float aaSS;

    @ExcelProperty
    private float aaC;

    @ExcelProperty
    private float ddS;

    private static final long serialVersionUID = 1L;

    public Cexcel(String B, float aaS, float bbS, float ccS, float aaA, float bbA, float ccA, float aaSS, float aaC, float ddS) {
        this.B = B;
        this.aaS = aaS;
        this.bbS = bbS;
        this.ccS = ccS;
        this.aaA = aaA;
        this.bbA = bbA;
        this.ccA = ccA;
        this.aaSS = aaSS;
        this.aaC = aaC;
        this.ddS = ddS;
    }
}