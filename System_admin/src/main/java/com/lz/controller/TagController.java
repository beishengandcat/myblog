package com.lz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lz.domain.ResponseResult;
import com.lz.domain.Tag;
import com.lz.domain.dto.TagListDto;
import com.lz.domain.vo.PageVo;
import com.lz.domain.vo.TagVo;
import com.lz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 功能描述：查询所有标签列表
 *
 * @Author: 李振
 * @Date: 2022/12/26 21:41
 **/
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    //查询所有的tag列表
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){

        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult add(@RequestBody Tag tag){
        return tagService.add(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult remove(@PathVariable("id") Long id){
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getId,id);
        return ResponseResult.okResult(tagService.remove(queryWrapper));
    }

    @GetMapping("/{id}")
    public ResponseResult get(@PathVariable("id") Long id){
        return ResponseResult.okResult(tagService.getById(id));
    }

    @PutMapping
    public ResponseResult update(@RequestBody Tag tag){
        return ResponseResult.okResult(tagService.updateById(tag));
    }


    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
