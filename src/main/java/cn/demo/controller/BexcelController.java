package cn.demo.controller;

import cn.demo.model.domain.Bexcel;
import cn.demo.model.dto.BexcelDTO;
import cn.demo.service.BexcelService;
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
@RequestMapping("/Bexcel")
public class BexcelController {

    @Resource
    private BexcelService bexcelService;


    @GetMapping("/page")
    public Result getBexcelPage(BexcelDTO.Query dto) {

        return bexcelService.getBexcelPage(dto);

    }

    @PostMapping
    public Result addBexcel(@RequestBody BexcelDTO.Add bexcelDTO) {
        Bexcel bexcel = BeanUtil.copyProperties(bexcelDTO, Bexcel.class);
        boolean save = bexcelService.save(bexcel);
        return Result.builder()
                .code(200)
                .data(save)
                .build();
    }


    @DeleteMapping
    public Result deleteBexcel(@RequestBody BexcelDTO.Delete dto) {
        boolean remove = bexcelService.remove(Wrappers.lambdaQuery(Bexcel.class)
                .eq(Bexcel::getC, dto.getC()));
        return Result.builder()
                .code(200)
                .data(remove)
                .build();
    }

    @PutMapping
    public Result updateBexcel(@RequestBody BexcelDTO.Update bexcelDTO) {
        Bexcel bexcel = BeanUtil.copyProperties(bexcelDTO, Bexcel.class);
        boolean update = bexcelService.update(bexcel, Wrappers.lambdaUpdate(Bexcel.class)
                .eq(Bexcel::getA, bexcelDTO.getA()));
        return Result.builder()
                .code(200)
                .data(update)
                .build();
    }

    @ApiOperation(value = "下载", produces = "application/octet-stream")
    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response,
                              @RequestParam(required = false, defaultValue = "false") Boolean needData) throws IOException {
        List<Bexcel> bexcels = new ArrayList<>();
        if (needData) {
            bexcels = bexcelService.list();
        }
        EasyExcel.write(response.getOutputStream(), Bexcel.class)
                .sheet()
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(bexcels);
    }


    @PostMapping("/excel")
    @ApiOperation(value = "导入", produces = "application/octet-stream")
    public Result importExcel(@RequestPart MultipartFile file) throws IOException {
        List<Bexcel> bexcelList = EasyExcel.read(file.getInputStream())
                .head(Bexcel.class)
                .doReadAllSync();
        for (Bexcel bexcel : bexcelList) {
            boolean exists = bexcelService.lambdaQuery()
                    .eq(Bexcel::getA, bexcel.getA())
                    .exists();
            if (!exists){
                bexcelService.save(bexcel);
            }
        }
        return Result.builder()
                .code(200)
                .data(null)
                .build();
    }
}
