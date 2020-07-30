package com.example.jawabasic;

class TestStr {

    public static void main(String[] arg) {
        String s = "123456654321";
        String s1 = "1234566543210";
        String s2 = "12345665432101";
        String s3 = "123456654321012";
        String s4 = "1234566543210123";
        String s5 = "1234566543210123456";
        System.out.println(hiddenInformationString(s));
        System.out.println(hiddenInformationString(s1));
        System.out.println(hiddenInformationString(s2));
        System.out.println(hiddenInformationString(s3));
        System.out.println(hiddenInformationString(s4));
        System.out.println(hiddenInformationString(s5));
        System.out.println(hiddenInformationString(""));
        System.out.println(hiddenInformationString(null));

    }
    public static String hiddenInformationString(String str) {
        if (str==null) {
            return null;
        }
        int len = str.length();
        int start =0 , end=0;
        if (len == 16) {
            start = 5; end =12;
        } else if (len == 19) {
            start = 5; end =15;
        } else if (len >= 12 && len <= 15) {
            start = 5; end =11;
        } else {
            return str;
        }
        start--;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < end - start; i++) {
            sb.append("*");
        }
        str = str.substring(0, start) + sb.toString() + str.substring(end);
        return str;
    }
}
