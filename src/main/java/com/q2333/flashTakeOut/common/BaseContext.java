package com.q2333.flashTakeOut.common;

/**
 *
 * 基于ThreadLocal封装工具类.
 * 保存当前登录用户的id
 * @date 2022/06/16 11:20
 **/
public class BaseContext {
    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId( ){
        return threadLocal.get();
    }
}
