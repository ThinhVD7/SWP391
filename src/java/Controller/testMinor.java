/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ROG
 */
public class testMinor {
    public static void main(String[] args) {
        int a = 609;
        System.out.println((a/60)>9?"yess":(a/60));
        System.out.println(a%60);
        String timeLimit = null;
        int temp = 4;
        timeLimit =  ((temp/60>9)?(""+String.valueOf(temp/60)):("0"+String.valueOf(temp/60))) +":" + ((temp%60>9)?(String.valueOf(temp%60)):("0"+String.valueOf(temp%60))) + ":00";
        System.out.println(timeLimit);
        
    }
}
