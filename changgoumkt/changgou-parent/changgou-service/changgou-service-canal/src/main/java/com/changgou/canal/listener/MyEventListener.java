package com.changgou.canal.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.changgou.content.feign.ContentFegin;
import com.changgou.content.pojo.Content;
import com.xpand.starter.canal.annotation.*;
import entry.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @author chen.qian
 * @date 2018/3/19
 */
@CanalEventListener
public class MyEventListener {

//    @InsertListenPoint
//    public void onEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
////        rowData.getAfterColumnsList().forEach((c) -> System.err.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
//        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
//
//    }

//    @UpdateListenPoint(destination = "example",schema = "changgou_content",table = "tb_content")
//    public void onEvent1(CanalEntry.RowData rowData) {
//        System.err.println("UpdateListenPoint");
//        rowData.getAfterColumnsList().forEach((c) -> System.err.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
//        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
//        for (CanalEntry.Column bc : beforeColumnsList) {
//            String name = bc.getName();
//            String value = bc.getValue();
//            System.out.println(name+":"+value);
//        }
//        System.out.println("===============================");
//        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
//        for (CanalEntry.Column ac : afterColumnsList) {
//            System.out.println(ac.getName()+":"+ac.getValue());
//        }
//
//    }

//    @DeleteListenPoint
//    public void onEvent3(CanalEntry.EventType eventType) {
//        System.err.println("DeleteListenPoint");
//    }
    @Autowired
    private ContentFegin contentFegin;

    @Autowired
    private RedisTemplate redisTemplate;

    //destination 指定要监听的目的地和配置文件中的值一致 和canal-server中的目录的名称一致
    // schema  指定监听的库
    // table 监听的表
    // eventType 监听的操作类型
    @ListenPoint(destination = "example",
                 schema = "changgou_content",
                 table = {"tb_content"},
            eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void onEvent4(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //1.监听mysql数据库表的变化，如果有变化，获取category_id
        String categoryId = getColumnValue(eventType, rowData);
        //2.调用fegin远程查询修改的表的数据
        Result<List<Content>> byCategory = contentFegin.findByCategory(Long.valueOf(categoryId));
        List<Content> contentList = byCategory.getData();
        //3.将数据写入到redis中
        redisTemplate.boundValueOps("content_"+categoryId).set(JSON.toJSONString(contentList));
    }

    private String getColumnValue(CanalEntry.EventType eventType, CanalEntry.RowData rowData){
        //这里为什么单独判断是不是DELETE类型，因为DELETE类型只能拿修改之前的数据，而没有修改之后的数据
        //INSERT和UPDATE拿修改之后的数据，其实如果categoryId不会改动的话，都拿修改之前的就可以
        String categoryId = "";
        if (eventType == CanalEntry.EventType.DELETE){
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                if ("category_id".equals(column.getName())){
                    categoryId = column.getValue();
                    break;
                }
            }
        }else
        {
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            for (CanalEntry.Column column : afterColumnsList) {
                if ("category_id".equals(column.getName())) {
                    categoryId = column.getValue();
                    break;
                }
            }
        }
        return categoryId;
    }
}
