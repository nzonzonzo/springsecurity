package com.changgou.core;

import entry.Result;

/***
 * 描述
 * @author ljh
 * @packagename com.changgou.core.changgou
 * @version 1.0
 * @date 2020/8/9
 */
public interface IUpdateController<T> {

    /**
     * 根据对象进行更新 根据ID
     *
     * @param record
     * @return
     */
    Result updateByPrimaryKey(T record);
}
