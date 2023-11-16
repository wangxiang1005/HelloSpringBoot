package com.paladin.paixu;

import java.util.Arrays;

/**
 * 希尔排序
 *
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {4,2,8,9,5,7,6,1,3};
        //未排序数组顺序为
        System.out.println("未排序数组顺序为：");
        display(array);
        System.out.println("-----------------------");
        array = shellSort(array);
        System.out.println("-----------------------");
        System.out.println("经过冒泡排序后的数组顺序为：");
        display(array);
    }


    //希尔排序 间隔序列2h
    public static int[] shellSort(int[] array){
        System.out.println("原数组为"+ Arrays.toString(array));
        int step;
        int len = array.length;
        for(step = len/2 ;step > 0 ; step /= 2){
            //分别对每个增量间隔进行排序
            for(int i = step ; i < array.length ; i++){
                int j = i;
                int temp = array[j];
                if(array[j] < array[j-step]){
                    while(j-step >=0 && temp < array[j-step]){
                        array[j] = array[j-step];
                        j -= step;
                    }
                    array[j] = temp;
                }
            }
            System.out.println("间隔为"+step+"的排序结果为"+Arrays.toString(array));
        }
        return array;
    }

    //遍历显示数组
    public static void display(int[] array){
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}