package com.changgou.goods.dao;
import com.changgou.goods.pojo.Spec;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:Spec的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface SpecMapper extends Mapper<Spec> {
//    @Select("select tb.* from tb_category_brand tcb join tb_brand tb where tcb.category_id=#{Cid} and tcb.brand_id = tb.id")

    @Select("select ts.* from tb_category tc,tb_spec ts where tc.id=#{id} and tc.template_id = ts.template_id")
//    @Results(id = "tsMap",
//    value = {@Result(column = "template_id",property = "templateId")})
    List<Spec> findSpecByCategory(@Param("id") Integer id);
}
