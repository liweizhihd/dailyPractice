package com.weizhi.prac.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/18 11:55
 * @Description:
 */
public class Portal {

    public static void main(String[] args) {

    }

    public static <T> void exchange(T[] arr,int indexA,int indexB){
        T temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    public static Integer[] getRandomArr (int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0;i < length;i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(0,100);
        }
        return arr;
    }

}
