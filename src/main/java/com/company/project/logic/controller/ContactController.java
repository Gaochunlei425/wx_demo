package com.company.project.logic.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.logic.entity.Contact;
import com.company.project.logic.service.ContactService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by GCL on 2023/03/21.
*/
@RestController
@RequestMapping(value = "/contact")
public class ContactController {
    @Resource
    private ContactService contactService;

    @ApiOperation(value = "新增")
    @ApiImplicitParams({@ApiImplicitParam(name = "contact", value = "", required = true, dataType = "String")})
    @PostMapping(value = "/add", produces = "application/json", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Result add(@RequestParam(value = "contact") Contact contact) {
        contactService.save(contact);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "", required = true, dataType = "int")})
    @DeleteMapping(value = "/delete", produces = "application/json")
    public Result delete(@RequestParam(value = "id") int id) {
        contactService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "contact", value = "", required = true, dataType = "String")})
    @PutMapping(value = "/update", produces = "application/json", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Result update(@RequestParam(value = "contact") Contact contact) {
        contactService.update(contact);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "查询详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "", required = true, dataType = "int")})
    @GetMapping(value = "/detail",produces = "application/json")
    public Result detail(@RequestParam(value = "id") int id) {
        Contact contact = contactService.findById(id);
        return ResultGenerator.genSuccessResult(contact);
    }

    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "int"),
                        @ApiImplicitParam(name = "size", value = "页面大小", required = false, dataType = "int")})
    @GetMapping(value = "/list",produces = "application/json")
    public Result list( @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<Contact> list = contactService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation(value = "动态查询列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "int"),
                        @ApiImplicitParam(name = "size", value = "页面大小", required = false, dataType = "int")})
    @PostMapping(value = "/listByExample",produces = "application/json")
    public Result findListByExample( @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                        @RequestParam(value = "contact", required = false) Contact contact) {
        PageHelper.startPage(page, size);
        Example example = new Example(Contact.class);
        // example.orderBy("createTime").desc();
        List<Contact> allByExample = contactService.findAllByExample(example);
        PageInfo pageInfo = new PageInfo(allByExample);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
