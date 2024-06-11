/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.operatingsystemrr;

/**
 *
 * @author ahmed
 */
public class OperatingSystemRR {

    public static void main(String[] args) {
        int[] a= new int[4];
        a[0]=4;
        a[1]=3;
        a[2]=2;
        a[3]=1;
        int temp;
        for(int i=0;i<4;i++){
          for(int j=i+1;j<4;j++){
            if(a[i]>a[j]){
             temp=a[i];
             a[i]=a[j];
             a[j]=temp;
            }
          }  
        }
        for(int i=0;i<4;i++)
            System.out.println(a[i]);
    }
}
