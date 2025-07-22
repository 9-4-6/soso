package com.gz.soso.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.pojo.dto.*;
import com.gz.soso.pojo.vo.PostListVO;
import com.gz.soso.service.SysPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final SysPositionService sysPositionService;

    @GetMapping("/list-page")
    public WebResponse<IPage<PostListVO>> listPage (@RequestBody PostListDTO dto) {
        IPage<PostListVO> deptListPage =sysPositionService.listPage(dto);
        return WebResponseBuilder.success(deptListPage);
    }


    @PostMapping("/add")
    public WebResponse<Long> add (@Validated @RequestBody PostAddDTO dto) {
        Long id =sysPositionService.add(dto);
        return WebResponseBuilder.success(id);
    }
    @PutMapping("/update")
    public WebResponse update (@Validated @RequestBody PostUpdateDTO dto) {
        sysPositionService.updatePost(dto);
        return WebResponseBuilder.success(null);
    }

    @DeleteMapping("/delete")
    public WebResponse delete (@RequestParam Long id) {
        sysPositionService.delete(id);
        return WebResponseBuilder.success(null);
    }
}
