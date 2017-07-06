package com.even.common.generator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangbiao
 * @date 2016-09-04 16：15
 */
public class GeneratorTest {

    public static void main(String[] args) {
//
        String source = "lastloginip";
        String regexStr = "[a-z]";
        Matcher matcher = Pattern.compile(regexStr).matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String g = matcher.group();
            matcher.appendReplacement(sb, g.toUpperCase());
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        System.out.println(sb.toString());

    }
}
