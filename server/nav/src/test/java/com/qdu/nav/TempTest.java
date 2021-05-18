package com.qdu.nav;

import java.util.Scanner;
//日历
import javax.swing.JTable.PrintMode;

public class TempTest {
  public static void main(String[] args) {
    Scanner input=new Scanner(System.in);
    System.out.println("please input the year  eg:2012");
    int year=input.nextInt();
    System.out.println("please input the month 1to12");
    int month=input.nextInt();
    printMonth( year, month);
  }
  public static void printMonth(int year,int month) {
    printMonthTitle(year,month);
    printMonthBody(year,month);
  }
  public static void printMonthTitle(int year,int month) {
    System.out.println("          "+getMonthName(month)+" "+year);
    System.out.println("  Sun Mon Tue Wed Thu Fri Sat");
  }
  public static String getMonthName(int month){
    String MonthName="";
    if(month>12||month<1){
      System.out.println("input have error");
      System.exit(1);
    }
    switch (month) {
      case 1: MonthName = "一月";  break;
      case 2: MonthName = "二月"; break;
      case 3: MonthName = "三月";    break;
      case 4: MonthName = "四月";    break;
      case 5: MonthName = "五月";      break;
      case 6: MonthName = "六月";     break;
      case 7: MonthName = "七月";     break;
      case 8: MonthName = "八月";   break;
      case 9: MonthName = "九月";break;
      case 10: MonthName = "十月"; break;
      case 11: MonthName = "十一月";break;
      case 12: MonthName = "十二月";break;
    }
    return MonthName;
  }
  public static void printMonthBody(int year,int month) {
    int startDay=getStarDay(year,month);
    int i=0;
    for(i=0;i<startDay;i++)
      System.out.printf("    ");
    int numberOfDaysInMonth = getNumberOfDayInMonth(year,month);

    for(i=1;i<=numberOfDaysInMonth;i++){
      System.out.printf("%4d",i);
      if((i+startDay)%7==0)
        System.out.println();
    }
  }


  public static int getStarDay(int year, int month) {
    final int START_DAY_FOR_JAN_1_1800=2;
    int totalNumberOfDays=getTotalNumberOfDays(year,month);

    return (totalNumberOfDays+START_DAY_FOR_JAN_1_1800)%7;
  }
  public static int getTotalNumberOfDays(int year, int month) {
    int total = 0;
    for(int i=1800;i<year;i++){
      if(isLeapYear(i))
        total+=366;
      else
        total+=365;
    }
    for(int i=1;i<month;i++){
      total+=getNumberOfDayInMonth(year, i);
    }
    return total;
  }
  public static boolean isLeapYear(int year) {

    return year%400==0||(year%4==0&&year%100!=0);
  }
  public static int getNumberOfDayInMonth(int year, int month) {
    if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
      return 31;
    if(month==4||month==6||month==9||month==11)
      return 30;
    if(month==2){
      if(isLeapYear( year)){
        return 28;
      }else{
        return 29;
      }
    }
    return 0;
  }
}
