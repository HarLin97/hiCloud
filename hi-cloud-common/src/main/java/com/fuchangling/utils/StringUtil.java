package com.fuchangling.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * @author wangzhen
 * @date 2019-12-27
 */
public class StringUtil {

    /**
     * 将数组转换成sql语句中的in串
     *
     * @param ids
     * @return inStr
     */
    public static String arrayToInStr(final Object[] ids) {
        final StringBuilder builder = new StringBuilder();
        if (ArrayUtils.isNotEmpty(ids)) {
            for (int i = 0; i < ids.length; i++) {
                builder.append("'").append(ids[i]).append("'");
                if (i != ids.length - 1) {
                    builder.append(",");
                }
            }
        }
        return builder.toString();
    }

    /**
     * 获取随机位数的字符串
     *
     * @param length 随机位数
     */
    public static String getRandomString(final int length) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // 获取ascii码中的字符 数字48-57 小写65-90 大写97-122
            int range = random.nextInt(75) + 48;
            range = range < 97 ? (range < 65 ? (range > 57 ? 114 - range : range) : (range > 90 ? 180 - range : range)) : range;
            sb.append((char) range);
        }
        return sb.toString();
    }

    public static boolean checkNull(final Object obj) {
        return StringUtils.isEmpty(obj.toString());
    }

}
