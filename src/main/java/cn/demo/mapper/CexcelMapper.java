package cn.demo.mapper;

import cn.demo.model.domain.Cexcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.transaction.annotation.Transactional;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CexcelMapper extends BaseMapper<Cexcel> {

    @Select("select sum(aa) as aaSum from Aexcel")
    float calAexcelAA();

    @Select("select sum(bb) as aaSum from Aexcel")
    float calAexcelBB();

    @Select("select sum(cc) as aaSum from Aexcel")
    float calAexcelCC();

    @Select("select sum(dd) as aaSum from Aexcel")
    float calAexcelDD();

    @Select("select sum(ee) as aaSum from Aexcel")
    float calAexcelEE();

    @Select("select sum(Trueaa) as aaSum from Bexcel")
    float calTrueAA();

    @Select("select sum(Truebb) as aaSum from Bexcel")
    float calTrueBB();

    @Select("select sum(Truecc) as aaSum from Bexcel")
    float calTrueCC();

    @Select("select sum(Truedd) as aaSum from Bexcel")
    float calTrueDD();

    @Select("select sum(Trueee) as aaSum from Bexcel")
    float calTrueEE();

    @Select("select sum(aa) as aaSum from Bexcel")
    float calBexcelAA();

    @Select("select sum(bb) as aaSum from Bexcel")
    float calBexcelBB();

    @Select("select sum(cc) as aaSum from Bexcel")
    float calBexcelCC();

    @Update("insert into Cexcel values(#{B}, #{aaS}, #{bbS}, #{ccS}, #{aaA}, #{bbA}, #{ccA}, #{aaSS}, #{aaC}, #{ddS})")
    @Transactional
    void calculateCexcel(@Param("B") String B, @Param("aaS") float aaS, @Param("bbS") float bbS, @Param("ccS") float ccS, @Param("aaA") float aaA, @Param("bbA") float bbA, @Param("ccA") float ccA, @Param("aaSS") float aaSS, @Param("aaC") float aaC, @Param("ddS") float ddS);
}




