package com.example.cuishou.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuishou.R;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenEditText extends android.support.v7.widget.AppCompatEditText {

    private final String regex1;
    private final int maxLen;
    private final int starId;
    private String tipContent;
    private boolean isPasted;

    public boolean isPasted() {
        return isPasted;
    }

    public ListenEditText(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ListenEditText);
        tipContent = typedArray.getString(R.styleable.ListenEditText_tip_content);
        starId = typedArray.getResourceId(R.styleable.ListenEditText_star_id, -1);
        regex1 = typedArray.getString(R.styleable.ListenEditText_tip_content_regx);
        maxLen = typedArray.getInt(R.styleable.ListenEditText_content_maxlen, 20);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (regex1 == null||regex1.length()<3) {
                    return;
                }
                String trim = s.toString().trim();
                tipContent = tipContent == null ? "" : tipContent;
                changeStarColorState(trim.matches(regex1));

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (isFalse&&s.length()>0) {
//                    CharSequence old = s.subSequence(0, s.length() - 1);
//                    setText(old);
//                    setSelection(old.length());
//                }
            }
        });
        typedArray.recycle();

    }

    private void changeStarColorState(Boolean match) {
        changeStarColorState(match, null);
    }

    private void changeStarColorState(Boolean match, String tipContent2) {
        if (match) {
            TextView v = (TextView) getRootView().findViewById(starId);
            if (v != null) {
                v.setTextColor(Color.GREEN);
                v.setHint("1");
            }

        } else {
            Toast.makeText(getContext(), tipContent2 == null ? tipContent : tipContent2, Toast.LENGTH_SHORT).show();
//                            new WarnDialog(getContext(),tipContent).show();
            TextView v = (TextView) getRootView().findViewById(starId);
            if (v != null) {
                v.setTextColor(Color.RED);
                v.setHint("0");
            }
        }

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        String trim = getText().toString().trim();
        if (!focused && !TextUtils.isEmpty(regex1) && trim.length() > 0) {
            switch (regex1) {
                case "1":
                    changeStarColorState(allowMaxLenthOfString(trim, maxLen) && isEnglishAndChineseAndDot(trim));
                    break;

                case "2":
                    boolean b = allowMaxLenthOfString(trim, maxLen) && isEnglishAndDigitAndChineseAndOther3(trim);
                    changeStarColorState( b);
                    break;
                case "3":
                    boolean c = allowMaxLenthOfString(trim, maxLen) && isEnglishAndDigit2(trim);
                    if (c) {
                        changeStarColorState(isDouble(trim), "符号[]{}()必须成对出现");
                    } else {
                        changeStarColorState(false);
                    }

                    break;

            }
        }
    }

    @Override
    public void onEditorAction(int actionCode) {
        super.onEditorAction(actionCode);
    }
    private void toast(String msg){
        Toast.makeText(getContext(), msg,Toast.LENGTH_SHORT).show();
    }
    /**
     * 全是重复数字校验
     *
     * @param numOrStr
     * @return
     */
    public  boolean equalStr(String numOrStr) {
        boolean flag = true;
        char str = numOrStr.charAt(0);
        for (int i = 0; i < numOrStr.length(); i++) {
            if (str != numOrStr.charAt(i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    /**
     * 字串s是否仅为数字
     *
     * @param s
     * @return true or false
     */
    public boolean isAllDigit(String s) {
        int len = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            if (Character.isDigit(s.charAt(idx))) {
                len++;
            }
        }
        if (len == s.length()) {
            return true;
        }

        return false;
    }
    /**
     * 判断字串 是否只有数字、 字母 ‘-’
     *
     * @param str
     * @return true or false
     */
    public  boolean isnc(String str) {

        int len = 0;
        for (int idx = 0; idx < str.length(); idx++) {
            char c = str.charAt(idx);

            if (('A'<=c && c<='Z')||('a'<=c && c<='z')||Character.isDigit(c) || c == '-') {
                len++;
            }
        }
        if (len == str.length()) {
            return true;
        }
        return false;
    }
    /**
     * 判断[]{}()是否成对出现
     * false 表示不成对出现
     */

    public boolean isDouble(String str) {
        // TODO Auto-generated method stub
        Stack<Character> sc = new Stack<Character>();
        try {
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '(' || c[i] == '[' || c[i] == '{' || c[i] == '（' || c[i] == '【') {
                    sc.push(c[i]);
                } else if (c[i] == ')') {
                    if (sc.peek() == '(') {
                        sc.pop();
                    }
                } else if (c[i] == ']') {
                    if (sc.peek() == '[') {
                        sc.pop();
                    }
                } else if (c[i] == '}') {
                    if (sc.peek() == '{') {
                        sc.pop();
                    }
                } else if (c[i] == '）') {
                    if (sc.peek() == '（') {
                        sc.pop();
                    }
                } else if (c[i] == '】') {
                    if (sc.peek() == '【') {
                        sc.pop();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        if (sc.empty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字串str是否为英文和中文,'.'
     *
     * @param str
     * @return true or false
     */
    public boolean isEnglishAndChineseAndDot(String str) {
        int j = 0;
        int k = 0;
        int i = str.length();
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            j++;
        }
        for (int idx = 0; idx < i; idx++) {
            char c = str.charAt(idx);
            int tmp = (int) c;
            if ((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z') || tmp == '.') {
                k++;
            }
        }
        if (i == j + k) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字串str是否为大写英文和数字“()”、“[]”、“{}”、“-” 证件号、组织机构代码
     *
     * @param str
     * @return true or false
     */
    public boolean isEnglishAndDigit2(String str) {
        int j = 0;
        int k = 0;
        int i = 0;
        for (int idx = 0; idx < str.length(); idx++) {
            if (Character.isDigit(str.charAt(idx))) {
                j++;
            }
        }
        for (int idx = 0; idx < str.length(); idx++) {
            char c = str.charAt(idx);
            if ('A' <= c && c <= 'Z') {
                k++;
            }
        }
        for (int ic = 0; ic < str.length(); ic++) {
            char c = str.charAt(ic);
            if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' || c == '-') {
                i++;
            }
        }
        if (i + k + j == str.length()) {
            return true;
        }

        return false;
    }


    /**
     * 字串s是否为数字、字母、汉字、中文符号顿号、‘’；和中英文状态符号。，-:[]{}() 公司信息校验：经营范围  门店信息页面：门店介绍
     *
     * @param
     * @return true or false
     */

    public boolean isEnglishAndDigitAndChineseAndOther3(String str) {
        int j = 0;
        int k = 0;
        int q = 0;
        int n = 0;
        for (int idx = 0; idx < str.length(); idx++) {
            if (Character.isDigit(str.charAt(idx))) {
                j++;
            }
        }
        for (int idx = 0; idx < str.length(); idx++) {
            char c = str.charAt(idx);
            if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') {
                k++;
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' || c == '（' || c == '）' || c == '【' || c == '】' || c == '。' || c == '、' || c == '‘' || c == '’' || c == ':' || c == '：' || c == '；' || c == '-' || c == '，' || c == '.' || c == ',') {
                n++;
            }
        }
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            q++;
        }
        if (k + j + q + n == str.length()) {
            return true;
        }

        return false;
    }

    /**
     * 字串s是否超过maxLen(规则：全角字符2位，半角1位；汉字2位，字母和数字1位；)
     *
     * @param s and maxLen
     * @return s是否超过最大长度maxLen, 是返回false，否则true
     */
    public boolean allowMaxLenthOfString(String s, int maxLen) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i, i + 1);
            if (isChinese(tmp)) {
                num += 2;
            } else {
                if (tmp.getBytes().length == 3) {
                    num += 2;
                } else if (tmp.getBytes().length == 1) {
                    num += 1;
                }
            }
        }
        if (num <= maxLen) {
            return true;
        }
        return false;
    }

    /**
     * 字串s是否仅为汉字
     *
     * @param
     * @return true or false
     */
    public boolean isChinese(String name) {
        int idx = 0;
        name = name.trim();
        int len = name.length();
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = pattern.matcher(name);
        while (m.find()) {
            idx++;
        }
        if (len == idx) {
            return true;
        } else {
            return false;
        }
    }
}
