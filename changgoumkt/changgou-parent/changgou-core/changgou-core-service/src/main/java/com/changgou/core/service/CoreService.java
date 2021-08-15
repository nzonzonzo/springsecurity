package com.changgou.core.service;

/***
 * 描述
 * @author ljh
 * @packagename com.changgou.core.changgou
 * @version 1.0
 * @date 2020/8/9
 */
public interface CoreService<T> extends
        DeleteService<T>,
        InsertService<T>,
        PagingService<T>,
        SelectService<T>,
        UpdateService<T> {
    //批量进行删除
    //批量进行添加


}
