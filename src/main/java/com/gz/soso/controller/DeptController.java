package com.gz.soso.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.pojo.dto.DeptAddDTO;
import com.gz.soso.pojo.dto.DeptListDTO;
import com.gz.soso.pojo.dto.DeptUpdateDTO;
import com.gz.soso.pojo.vo.DeptListVO;
import com.gz.soso.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
@Slf4j
public class DeptController {

    private final SysDeptService sysDeptService;


    @GetMapping("/list-page")
    public WebResponse<IPage<DeptListVO>> listPage (@RequestBody DeptListDTO dto) {
        IPage<DeptListVO> deptListPage =sysDeptService.listPage(dto);
        return WebResponseBuilder.success(deptListPage);
    }


    @PostMapping("/add")
    public WebResponse<Long> add (@Validated @RequestBody DeptAddDTO dto) {
        Long id =sysDeptService.add(dto);
        return WebResponseBuilder.success(id);
    }
    @PutMapping("/update")
    public WebResponse update (@Validated @RequestBody DeptUpdateDTO dto) {
        sysDeptService.updateDept(dto);
        return WebResponseBuilder.success(null);
    }

    @DeleteMapping("/delete")
    public WebResponse delete (@RequestParam Long id) {
        sysDeptService.delete(id);
        return WebResponseBuilder.success(null);
    }


}
