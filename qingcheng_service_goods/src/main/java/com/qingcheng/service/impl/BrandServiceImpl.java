package com.qingcheng.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/*这个注解我们需要选择dubbo下面的,不要选错了*/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }


    @Override
    public PageResult<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Brand> pageResult = (Page<Brand>) brandMapper.selectAll();
        return new PageResult<Brand>(pageResult.getTotal(), pageResult.getResult());
    }

    /*构建map根据条件查询*/
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example = creatExample(searchMap);
        return brandMapper.selectByExample(example);
    }

    private Example creatExample(Map<String, Object> searchMap) {
        /*这里有一个需要注意的点:因为用了通用Mapper的原因,所以我们的实体类都是Object类型的,否则就不能根据相关条件进行查询了*/
        /*参考https://blog.csdn.net/Rebirth321/article/details/88174084*/
        /*还有Date。 通用Mapper不识别java.sql.Date,只识别java.util.Date*/
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name","%"+(String)searchMap.get("name")+"%");
            }
            if(searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
                criteria.andEqualTo("letter",(String)searchMap.get("letter"));
            }
        }
        return example;
    }

    /*根据分页+条件查询*/
    @Override
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = creatExample(searchMap);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        return new PageResult<Brand>(brands.getTotal(), brands.getResult());
    }

    /*根据id查询,注意id的类型要和实体类中的一样,是Integer*/

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /*新增*/

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    /*修改*/

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /*删除*/

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}
