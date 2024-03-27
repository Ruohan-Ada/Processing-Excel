package cn.demo.service.impl;

import cn.demo.mapper.AexcelMapper;
import cn.demo.model.domain.Aexcel;
import cn.demo.service.AexcelService;
import cn.demo.model.dto.AexcelDTO;
import cn.demo.util.Result;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author adazhang
 * @description 针对表【Aexcel】的数据库操作Service实现
 * @createDate 2024-03-09 18:02:31
 */
@Service
public class AexcelServiceImpl extends ServiceImpl<AexcelMapper, Aexcel>
        implements AexcelService {

    @Override
    public Result getAexcelPage(AexcelDTO.Query dto) {
        Page<Aexcel> page = lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getA()), Aexcel::getA, dto.getA())
                .like(StrUtil.isNotBlank(dto.getB()), Aexcel::getB, dto.getB())
                .like(StrUtil.isNotBlank(dto.getC()), Aexcel::getC, dto.getC())
                .like(StrUtil.isNotBlank(dto.getD()), Aexcel::getD, dto.getD())
                .like(StrUtil.isNotBlank(dto.getE()), Aexcel::getE, dto.getE())
                .like(StrUtil.isNotBlank(dto.getF()), Aexcel::getF, dto.getF())
                .eq(dto.getAa() != null, Aexcel::getAa, dto.getAa())
                .eq(dto.getBb() != null, Aexcel::getBb, dto.getBb())
                .eq(dto.getCc() != null, Aexcel::getCc, dto.getCc())
                .eq(dto.getDd() != null, Aexcel::getDd, dto.getDd())
                .eq(dto.getEe() != null, Aexcel::getEe, dto.getEe())
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return Result.builder()
                .code(200)
                .data(page)
                .build();
    }
}





