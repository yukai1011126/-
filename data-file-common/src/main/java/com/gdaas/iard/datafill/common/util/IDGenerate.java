package com.gdaas.iard.datafill.common.util;

/**
 * ID生成器
 */
public class IDGenerate {



    public  static String   id(){
        return System.currentTimeMillis()+""+System.nanoTime();
    }

}
