package com.jeesite.modules.wms.util;

import com.jeesite.common.entity.Page;

/**
 * @Author christina
 * @Date 2022/12/17 17:55
 */
public class QueryUtil {
    public static <T extends Page> void validQuery(T query){
        if (query.getPageNo() <= 0){
            query.setPageNo(1);
        }
        if (query.getCount()<=0){
            query.setCount(20);
        }
    }
}
