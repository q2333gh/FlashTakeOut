package com.q2333.flashTakeOut.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回结果范型类
 * 服务端的响应统一封装为Return类的对象
 *
 * @param <T>
 */
@Data
public class Return<T> {
    //类似http的response数据格式:
    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据
    private Map map = new HashMap(); //动态数据

    public static <T> Return<T> success(T object) {
        Return<T> r = new Return<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> Return<T> error(String msg) {
        Return r = new Return();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public Return<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
