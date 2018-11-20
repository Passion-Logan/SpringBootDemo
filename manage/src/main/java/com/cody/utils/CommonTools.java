package com.cody.utils;

import java.sql.Timestamp;

/**
 * @author Cody_
 * @date 18/10/5
 */
public class CommonTools {
    public static boolean isEmpty(String str) {
        if(str == null) {
            return true;
        }
        if(str.isEmpty()) {
            return true;
        }
        if(str == "" || str.equals("")) {
            return true;
        }
        return false;
    }

    public static Timestamp getCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }

}
