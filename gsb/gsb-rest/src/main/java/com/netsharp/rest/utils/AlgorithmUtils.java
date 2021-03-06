package com.netsharp.rest.utils;

import java.io.Serializable;

public class AlgorithmUtils implements Serializable {
    private static final long	serialVersionUID	= -3357428692528862880L;

    public static Boolean KMP(String source, String pattern) {
        int[] N=getN(pattern);
        int sourceLength=source.length();
        int patternLength=pattern.length();
        for(int i=0;i<=(sourceLength-patternLength);){
            String str=source.substring(i, i+patternLength);//要比较的字符串

            int count=getNext(pattern, str,N);
            if(count==0){
                return true;
            }

            i=i+count;
        }

        return false;
    }

    public static Integer KMPIndex(String source, String pattern) {
        int[] N=getN(pattern);
        int sourceLength=source.length();
        int patternLength=pattern.length();
        for(int i=0;i<=(sourceLength-patternLength);){
            String str=source.substring(i, i+patternLength);//要比较的字符串

            int count=getNext(pattern, str,N);
            if(count==0){
                return i;
            }

            i=i+count;
        }

        return 0;
    }

    /**
     * 得到下一次要移动的次数
     *
     * @param pattern
     * @param str
     * @param N
     * @return 0,字符串匹配；
     */
    private static int getNext(String pattern,String str,int[] N) {
        int n = pattern.length();
        char v1[] = str.toCharArray();
        char v2[] = pattern.toCharArray();
        int x = 0;
        while (n-- != 0) {
            if (v1[x] != v2[x]){
                if(x==0){
                    return 1;//如果第一个不相同，移动1步
                }
                return x-N[x-1];//x:第一次出现不同的索引的位置，即j
            }
            x++;
        }
        return 0;
    }
    private static int[] getN(String pattern) {
        char[] pat=pattern.toCharArray();
        int j=pattern.length()-1;
        int[] N=new int[j+1];
        for(int i=j;i>=2;i--){
            N[i-1]=getK(i,pat);
        }
        return N;
    }
    private static int getK(int j, char[] pat) {
        int x=j-2;
        int y=1;
        while (x>=0 && compare(pat, 0, x, y, j-1)) {
            x--;
            y++;
        }
        return x+1;
    }
    private static boolean compare(char[] pat,int b1,int e1,int b2,int e2){
        int n = e1-b1+1;
        while (n-- != 0) {
            if (pat[b1] != pat[b2]){
                return true;
            }
            b1++;
            b2++;
        }
        return false;
    }
}
