package cn.demo.service;

import cn.demo.model.domain.Cexcel;
import cn.demo.model.dto.CexcelDTO;
import cn.demo.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CexcelService extends IService<Cexcel> {
    Result getCexcelPage(CexcelDTO.Query dto);
}
