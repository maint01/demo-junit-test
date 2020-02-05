package com.itsol.junit.string;

import org.apache.commons.lang3.StringUtils;

import java.util.StringTokenizer;

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
}
