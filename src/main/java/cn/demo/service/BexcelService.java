package cn.demo.service;

import cn.demo.model.domain.Bexcel;
import cn.demo.model.dto.BexcelDTO;
import cn.demo.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BexcelService extends IService<Bexcel> {


    Result getBexcelPage(BexcelDTO.Query dto);
}
