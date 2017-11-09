package com.yayiabc.common.utils;

public class ScoreUtil {
    public static double getScore(long id){
        System.out.println(id);
        String score="0.";
        int count=5;
        while(id/10!=0){
            count--;
        }
        for(int i=0;i<count;i++){
            score+="0";
        }
        score+=id;
        System.out.println("出来了");
        return Double.parseDouble(score);
    }
}
