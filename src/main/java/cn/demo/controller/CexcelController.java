package cn.demo.controller;

import cn.demo.model.domain.Cexcel;
import cn.demo.model.dto.CexcelDTO;
import cn.demo.service.CexcelService;
import cn.demo.mapper.CexcelMapper;
import cn.demo.util.Result;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author adazhang
 * @date 2024/03/09
 */

@RestController
@RequestMapping("/Cexcel")
public class CexcelController {

    @Resource
    private CexcelService cexcelService;

    @Autowired
    private CexcelMapper cexcelMapper;


    @GetMapping("/page")
    public Result getCexcelPage(CexcelDTO.Query dto) {

        return cexcelService.getCexcelPage(dto);

    }

    @PostMapping
    public Result calculateC() {
        String B = "A";
        float aexcelAA = (cexcelMapper.calAexcelAA() == 0) ? 1: cexcelMapper.calAexcelAA();
        float aexcelBB = (cexcelMapper.calAexcelBB() == 0) ? 1: cexcelMapper.calAexcelBB();
        float aexcelCC = (cexcelMapper.calAexcelCC() == 0) ? 1: cexcelMapper.calAexcelCC();
        float aexcelDD = (cexcelMapper.calAexcelDD() == 0) ? 1: cexcelMapper.calAexcelDD();
        float aexcelEE = (cexcelMapper.calAexcelEE() == 0) ? 1: cexcelMapper.calAexcelEE();
        float bexcelAA = (cexcelMapper.calBexcelAA() == 0) ? 1: cexcelMapper.calBexcelAA();
        float bexcelBB = (cexcelMapper.calBexcelBB() == 0) ? 1: cexcelMapper.calBexcelBB();
        float bexcelCC = (cexcelMapper.calBexcelCC() == 0) ? 1: cexcelMapper.calBexcelCC();
        float aaS = (cexcelMapper.calTrueAA() / aexcelAA);
        float bbS = (cexcelMapper.calTrueBB() / aexcelBB);
        float ccS = (cexcelMapper.calTrueCC() / aexcelCC);
        float aaA = (cexcelMapper.calTrueAA() / bexcelAA);
        float bbA = (cexcelMapper.calTrueBB() / bexcelBB);
        float ccA = (cexcelMapper.calTrueCC() / bexcelCC);
        float aaSS = (cexcelMapper.calTrueEE() / aexcelEE);
        float aaC = ((cexcelMapper.calTrueAA() + cexcelMapper.calTrueEE()) / (aexcelAA + aexcelEE));
        float ddS = (cexcelMapper.calTrueDD() / aexcelDD);
        System.out.println(aaS + "\t" + bbS);
        cexcelMapper.calculateCexcel(B, aaS, bbS, ccS, aaA, bbA, ccA, aaSS, aaC, ddS);
        return Result.builder()
                .code(200)
                .data(null)
                .build();
    }


    @DeleteMapping
    public Result deleteCexcel(@RequestBody CexcelDTO.Delete dto) {
        boolean remove = cexcelService.remove(Wrappers.lambdaQuery(Cexcel.class)
                .eq(Cexcel::getB, dto.getB()));
        return Result.builder()
                .code(200)
                .data(remove)
                .build();
    }

    @PutMapping
    public Result updateCexcel(@RequestBody CexcelDTO.Update cexcelDTO) {
        Cexcel cexcel = BeanUtil.copyProperties(cexcelDTO, Cexcel.class);
        boolean update = cexcelService.update(cexcel, Wrappers.lambdaUpdate(Cexcel.class)
                .eq(Cexcel::getB, cexcelDTO.getB()));
        return Result.builder()
                .code(200)
                .data(update)
                .build();
    }

    @ApiOperation(value = "下载", produces = "application/octet-stream")
    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response,
                              @RequestParam(required = false, defaultValue = "false") Boolean needData) throws IOException {
        List<Cexcel> cexcels = new ArrayList<>();
        if (needData) {
            cexcels = cexcelService.list();
        }
        EasyExcel.write(response.getOutputStream(), Cexcel.class)
                .sheet()
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(cexcels);
    }


    @PostMapping("/excel")
    @ApiOperation(value = "导入", produces = "application/octet-stream")
    public Result importExcel(@RequestPart MultipartFile file) throws IOException {
        List<Cexcel> cexcelList = EasyExcel.read(file.getInputStream())
                .head(Cexcel.class)
                .doReadAllSync();
        for (Cexcel cexcel : cexcelList) {
            boolean exists = cexcelService.lambdaQuery()
                    .eq(Cexcel::getB, cexcel.getB())
                    .exists();
            if (!exists){
                cexcelService.save(cexcel);
            }
        }
        return Result.builder()
                .code(200)
                .data(null)
                .build();
    }
}
