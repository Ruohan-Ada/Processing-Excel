package cn.demo.service.impl;

import cn.demo.mapper.BexcelMapper;
import cn.demo.model.domain.Bexcel;
import cn.demo.service.BexcelService;
import cn.demo.model.dto.BexcelDTO;
import cn.demo.util.Result;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BexcelServiceImpl extends ServiceImpl<BexcelMapper, Bexcel>
        implements BexcelService {

    @Override
    public Result getBexcelPage(BexcelDTO.Query dto) {
        Page<Bexcel> page = lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getA()), Bexcel::getA, dto.getA())
                .like(StrUtil.isNotBlank(dto.getB()), Bexcel::getB, dto.getB())
                .like(StrUtil.isNotBlank(dto.getC()), Bexcel::getC, dto.getC())
                .like(StrUtil.isNotBlank(dto.getD()), Bexcel::getD, dto.getD())
                .like(StrUtil.isNotBlank(dto.getE()), Bexcel::getE, dto.getE())
                .like(StrUtil.isNotBlank(dto.getF()), Bexcel::getF, dto.getF())
                .eq(dto.getAa() != null, Bexcel::getAa, dto.getAa())
                .eq(dto.getTrueaa() != null, Bexcel::getTrueaa, dto.getTrueaa())
                .eq(dto.getBb() != null, Bexcel::getBb, dto.getBb())
                .eq(dto.getTruebb() != null, Bexcel::getTruebb, dto.getTruebb())
                .eq(dto.getCc() != null, Bexcel::getCc, dto.getCc())
                .eq(dto.getTruecc() != null, Bexcel::getTruecc, dto.getTruecc())
                .eq(dto.getDd() != null, Bexcel::getDd, dto.getDd())
                .eq(dto.getTruedd() != null, Bexcel::getTruedd, dto.getTruedd())
                .eq(dto.getEe() != null, Bexcel::getEe, dto.getEe())
                .eq(dto.getTrueee() != null, Bexcel::getTrueee, dto.getTrueee())
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return Result.builder()
                .code(200)
                .data(page)
                .build();
    }
}





