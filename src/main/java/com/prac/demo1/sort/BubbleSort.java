package com.prac.demo1.sort;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/18 11:53
 * @Description:冒泡排序
 */
@Slf4j
public class BubbleSort {

    public static void main(String[] args) {
        //Integer[] arr = getRandomArr(5);
        Integer[] arr = {80,16,7,75,34,27,57,39,60,98};
        System.out.println(StringUtils.join(arr,","));
        long start = System.currentTimeMillis();
        sort(arr);
        long end = System.currentTimeMillis();
        System.out.println(StringUtils.join(arr,","));
        System.out.println(end - start);
    }


    public static void sort (Integer[] arr) {
        int length = arr.length;
        for (int i = 0;i < length;i++) {
            for (int j = 0;j < length - 1 - i;j++) {
                if (arr[j] > arr[j+1]) {
                    Integer temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            log.info("第{}次变换后:{}",i+1, StringUtils.join(arr,","));
        }
    }

    // 错误
    public static void sort2 (Integer[] arr) {
        String origin = StringUtils.join(arr,",");
        int i = arr.length - 1;
        int position = 0;
        int count = 0;
        boolean hasChange = true;
        while (i > 0 && hasChange) {
            hasChange = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    position = j;
                    hasChange = true;
                    Portal.exchange(arr,j,j+1);
                }
            }
            i = position;
            log.info("第{}次变换后,position:{},数组由{}==排序为==>{}",++count, position, origin, StringUtils.join(arr,","));
        }
    }



    @Test
    public void temp () {
        System.out.println(StringUtils.join(Portal.getRandomArr(10), ","));
    }

}
