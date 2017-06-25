package com.liang.graduationProject.response;

import lombok.Data;

/**
 * Created by admin on 2017/3/29.
 */

@Data
public class DataResult {
    /**
     * 给前端用来提示的文字，例如：“请求成功”、“请求失败”，允许为空
     */
    private String reason;

    /**
     * 返回具体的数据，允许为空
     */
    private Object result;

    /**
     * 如果报错，返回错误码，错误码约定看ErrorCode这个类，允许为空
     */
    private String errorCode;


}
