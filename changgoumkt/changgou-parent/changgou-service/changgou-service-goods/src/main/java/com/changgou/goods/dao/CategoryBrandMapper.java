package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.pojo.CategoryBrand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:CategoryBrand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface CategoryBrandMapper extends Mapper<CategoryBrand> {

    @Select("select tb.* from tb_category_brand tcb join tb_brand tb where tcb.category_id=#{Cid} and tcb.brand_id = tb.id")
    public List<Brand> findBrandByCid(@Param("Cid") Integer cid);
}
