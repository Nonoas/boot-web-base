package org.nonoas.bootweb.utils;

import java.util.UUID;

/**
 * @author Nonoas
 * @datetime 2022/4/10 14:21
 */
public class SequenceUtil {
    private SequenceUtil() {
    }

    /**
     * 生成随机文件名
     *
     * @param name 原文件名
     * @return 随机新文件名
     */
    public static String getRandomFileName(String name) {
        int i = name.lastIndexOf('.');
        String suffix = -1 == i ? "" : name.substring(i);
        return UUID.randomUUID().toString().replace("-", "") + suffix;
    }

}
