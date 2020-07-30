package com.example.jawabasic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    public static void main(String[] arg) {
        System.out.println(isDateValid("2020-06-8"));
        System.out.println(isDateValid("2020-06-9"));
        System.out.println(isDateValid("2020-06-16"));
        System.out.println(isDateValid("20200616"));
        String returnCode = "001000";

    }

    /**
     * 判断给定的procEndTime日期是否大于等于当前日期
     *
     * @param procEndTime 截止日期
     * @return
     */
    private static boolean isDateValid(String procEndTime) {
        //放款日期大于等于当前日期 返回true  格式YYYY-MM-DD
        Date currentDate = new Date();
        String pattern = "";
        if (procEndTime.contains("-")) {
            pattern = "yyyy-MM-dd";
        } else {
            pattern = "yyyyMMdd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Date endTime = null;
        try {

            endTime = sdf.parse(procEndTime);
            currentDate = sdf.parse(sdf.format(currentDate));
            System.out.println("current date  ===" + currentDate.toString());
            //current dateTue Jun 09 00:00:00 CST 2020
            System.out.println("end date ====" + endTime.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return !currentDate.after(endTime);
    }
}
