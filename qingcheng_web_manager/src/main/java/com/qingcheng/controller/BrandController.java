package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    /*这个也是要选择dubbo下面的*/

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    /*http://localhost:9102/brand/findPage.do?page=1&size=10*/
    /*注意哈:page和size是要一起传进去的*/
    public PageResult<Brand> findPage(int page, int size){
        return brandService.findPage(page,size);
    }

    /*条件查询*/
    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String,Object> searchMap) {
        return brandService.findList(searchMap);
    }

    /*分页+条件查询*/
    @PostMapping("/findage")
    public PageResult<Brand> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return brandService.findPage(searchMap,page,size);
    }

    /*id查询*/
    @RequestMapping("/findById")
    public Brand findById(Integer id) {
        return brandService.findById(id);
    }

    /*新增*/

    @PostMapping("/add")
    public Result add(@RequestBody Brand brand) {
         brandService.add(brand);
         return new Result();
    }

    /*修改*/
    @PostMapping("/update")
    public Result update(@RequestBody Brand brand) {
        brandService.update(brand);
        return new Result();
    }

    /*删除*/
    @GetMapping("/delete")
    public Result delete(Integer id) {
        brandService.delete(id);
        return new Result();
    }
}
