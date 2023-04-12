    package com.jeesite.modules.wms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author christina
 * @Date 2023/2/2 9:58
 */
public class MobileUtil {
    public static boolean checkMobile(String mobile){
        String regex = "^((13[0-9])|(14[1,2,3,5,7,8,9])|(15[0-9])|(166)|(191)|(17[0,1,2,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (mobile.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(mobile);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return false;
            }
            return true;
        }
    }
}
