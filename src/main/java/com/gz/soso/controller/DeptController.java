package com.gz.soso.controller;

import com.gz.soso.pojo.common.WebResponse;
import com.gz.soso.pojo.common.WebResponseBuilder;
import com.gz.soso.pojo.dto.DeptAddDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
@Slf4j
public class DeptController {

    @PostMapping("/add")
    public WebResponse<Long> add (@Validated @RequestBody DeptAddDTO dto) {
        return WebResponseBuilder.success(null);
    }
}
