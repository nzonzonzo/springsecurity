package com.changgou.Test;


import entry.IdWorker;
import org.junit.Test;



public class T {
    @Test
    public void ceshi(){

        IdWorker idWorker = new IdWorker(31, 31);
        for (int i = 0; i < 2600; i++) {
            System.out.println(idWorker.nextId()+"..."+i);
        }
    }

    @Test
    public void ceshi2(){
        int i = 4;
        System.out.println(getType(i+""));
    }

    public static String getType(Object o){ //获取变量类型方法
        return o.getClass().toString(); //使用int类型的getClass()方法
    }

}
