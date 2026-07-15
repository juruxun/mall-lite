package com.blake.malllite.common;

public class UserHolder {
    private static final ThreadLocal<Long> t1 = new ThreadLocal<Long>();

    //保存用户ID
    public static void saveUser(Long userId){
        t1.set(userId);

    }

    //获取用户Id
    public static Long getUser(){
        return t1.get();
    }

    //删除用户信息
    public static void remove(){
        t1.remove();
    }


}
