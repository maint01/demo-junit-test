package com.itsol.junit.string;

import org.apache.commons.lang3.StringUtils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public String standardString(String s) {
        if (StringUtils.isBlank(s))
            return StringUtils.EMPTY;
        StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        StringBuilder stringBuilder = new StringBuilder();
        while (stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();
            if (StringUtils.isBlank(element))
                continue;
            stringBuilder.append(element.substring(0, 1).toUpperCase());
            if (element.length() > 1)
                stringBuilder.append(element.substring(1).toLowerCase());
            stringBuilder.append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public String findCharacterNotMatchRegex(String source, String regex) {
        if (StringUtils.isBlank(regex) || StringUtils.isBlank(source))
            return StringUtils.EMPTY;
        Pattern pattern = Pattern.compile(regex);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            Character charI = source.charAt(i);
            if (!pattern.matcher(String.valueOf(charI)).find()) {
                stringBuilder.append(charI);
            }
        }
        return stringBuilder.toString();
    }
}
