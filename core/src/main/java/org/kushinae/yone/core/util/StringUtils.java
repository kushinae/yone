package org.kushinae.yone.core.util;

import com.google.common.base.CaseFormat;

import java.util.Objects;

/**
 * @author bnyte
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class StringUtils {

    public static Boolean hasText(String text) {
        if (Objects.isNull(text)) {
            return false;
        }
        return text.length() > 0;
    }

    public static Boolean nonText(String text) {
        if (Objects.isNull(text)) {
            return false;
        }
        return text.length() == 0;
    }

    /**
     * 中划线分割字符转换为小写驼峰
     *  test-data >> testData
     * @param lowerHyphen 需要转换的目标字符
     * @return 转换为驼峰之后的字符串
     */
    public static String lowerHyphen2LowerCamel(String lowerHyphen) {
        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, lowerHyphen);
    }

    /**
     * 下划线分割字符转换为小写驼峰
     *  test_data >> testData
     * @param lowerUnderscore 需要转换的目标字符
     * @return 转换为驼峰之后的字符串
     */
    public static String lowerUnderscore2LowerCamel(String lowerUnderscore) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, lowerUnderscore);
    }

    /**
     * 下划线分割字符转换为大写驼峰
     *  test_data >> TestData
     * @param upperUnderscore 需要转换的目标字符
     * @return 转换为驼峰之后的字符串
     */
    public static String upperUnderscore2UpperCamel(String upperUnderscore) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, upperUnderscore);
    }

    /**
     * 小写驼峰转换为由下划线分割的字符串
     *  testData >> test_data
     * @param lowerCamel 需要转换的目标字符
     * @return 转换为驼峰之后的字符串
     */
    public static String lowerCamel2LowerUnderscore(String lowerCamel) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, lowerCamel);
    }

    /**
     * 小写驼峰转换为由中划线分割的字符串
     *  testData >> test-data
     * @param lowerCamel 需要转换的目标字符
     * @return 转换为驼峰之后的字符串
     */
    public static String lowerCamel2LowerHyphen(String lowerCamel) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, lowerCamel);
    }
}
