package cn.demo.service;

import cn.demo.model.domain.Aexcel;
import cn.demo.model.dto.AexcelDTO;
import cn.demo.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author adazhang
 * @description 针对表【Aexcel】的数据库操作Service
 * @createDate 2024-03-09 18:02:31
 */
public interface AexcelService extends IService<Aexcel> {


    Result getAexcelPage(AexcelDTO.Query dto);
}
