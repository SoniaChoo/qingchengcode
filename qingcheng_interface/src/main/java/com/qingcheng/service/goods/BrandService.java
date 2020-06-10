package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    /*查询全部*/
    public List<Brand> findAll();
    /*分页查询*/
    public PageResult<Brand> findPage(int page, int size);
    /*根据条件查询*/
    public List<Brand> findList(Map<String, Object> searchMap);
    /*分页+条件查询*/
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size);
    /*根据id查询*/
    /*注意,这里要写Integer, 因为实体类中也是Integer,而上面的page和size是int的原因是因为他们不是实体类中的属性*/
    public Brand findById(Integer id);
    /*新增*/
    public void add(Brand brand);
    /*修改*/
    public void update(Brand brand);
    /*删除*/
    public void delete(Integer id);
}
