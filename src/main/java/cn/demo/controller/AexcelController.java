package cn.demo.controller;

import cn.demo.mapper.CexcelMapper;
import cn.demo.model.domain.Aexcel;
import cn.demo.model.dto.AexcelDTO;
import cn.demo.service.AexcelService;
import cn.demo.mapper.AexcelMapper;
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
@RequestMapping("/Aexcel")
public class AexcelController {

    @Resource
    private AexcelService aexcelService;

    @Autowired
    private AexcelMapper aexcelMapper;

    @GetMapping("/page")
    public Result getAexcelPage(AexcelDTO.Query dto) {

        return aexcelService.getAexcelPage(dto);

    }

    @PostMapping
    public Result addAexcel(@RequestBody AexcelDTO.Add aexcelDTO) {
        Aexcel aexcel = BeanUtil.copyProperties(aexcelDTO, Aexcel.class);
        boolean save = aexcelService.save(aexcel);
        return Result.builder()
                .code(200)
                .data(save)
                .build();
    }


    @DeleteMapping
    public Result deleteAexcel(@RequestBody AexcelDTO.Delete dto) {
        boolean remove = aexcelService.remove(Wrappers.lambdaQuery(Aexcel.class)
                .eq(Aexcel::getA, dto.getA()));
        return Result.builder()
                .code(200)
                .data(remove)
                .build();
    }

    @PutMapping
    public Result updateAexcel(@RequestBody AexcelDTO.Update aexcelDTO) {
        Aexcel aexcel = BeanUtil.copyProperties(aexcelDTO, Aexcel.class);
        boolean update = aexcelService.update(aexcel, Wrappers.lambdaUpdate(Aexcel.class)
                .eq(Aexcel::getA, aexcelDTO.getA()));
        return Result.builder()
                .code(200)
                .data(update)
                .build();
    }


    @ApiOperation(value = "下载", produces = "application/octet-stream")
    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response,
                              @RequestParam(required = false, defaultValue = "false") Boolean needData) throws IOException {
        List<Aexcel> aexcels = new ArrayList<>();
        if (needData) {
            aexcels = aexcelService.list();
        }
        EasyExcel.write(response.getOutputStream(), Aexcel.class)
                .sheet()
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(aexcels);
    }


    @PostMapping("/excel")
    @ApiOperation(value = "导入", produces = "application/octet-stream")
    public Result importExcel(@RequestPart MultipartFile file) throws IOException {
        List<Aexcel> aexcelList = EasyExcel.read(file.getInputStream())
                .head(Aexcel.class)
                .doReadAllSync();
        for (Aexcel aexcel : aexcelList) {
            boolean exists = aexcelService.lambdaQuery()
                    .eq(Aexcel::getA, aexcel.getA())
                    .exists();
            if (!exists){
                aexcelService.save(aexcel);
            }
        }
        return Result.builder()
                .code(200)
                .data(null)
                .build();
    }
}
