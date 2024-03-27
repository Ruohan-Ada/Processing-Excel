package cn.demo.service.impl;

import cn.demo.mapper.CexcelMapper;
import cn.demo.model.domain.Cexcel;
import cn.demo.service.CexcelService;
import cn.demo.model.dto.CexcelDTO;
import cn.demo.util.Result;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CexcelServiceImpl extends ServiceImpl<CexcelMapper, Cexcel>
        implements CexcelService {

    @Override
    public Result getCexcelPage(CexcelDTO.Query dto) {
        Page<Cexcel> page = lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getB()), Cexcel::getB, dto.getB())
                .eq(dto.getAaS() != null, Cexcel::getAaS, dto.getAaS())
                .eq(dto.getBbS() != null, Cexcel::getBbS, dto.getBbS())
                .eq(dto.getCcS() != null, Cexcel::getCcS, dto.getCcS())
                .eq(dto.getAaA() != null, Cexcel::getAaA, dto.getAaA())
                .eq(dto.getBbA() != null, Cexcel::getBbA, dto.getBbA())
                .eq(dto.getCcA() != null, Cexcel::getCcA, dto.getCcA())
                .eq(dto.getAaSS() != null, Cexcel::getAaSS, dto.getAaSS())
                .eq(dto.getAaC() != null, Cexcel::getAaC, dto.getAaC())
                .eq(dto.getDdS() != null, Cexcel::getDdS, dto.getDdS())
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return Result.builder()
                .code(200)
                .data(page)
                .build();
    }
}





