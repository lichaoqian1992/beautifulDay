package com.manji.orService.enums;

/**
 * Created by Administrator on 2017/9/1.
 */
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;

@GwtCompatible
public enum CaseFormat {
    /**
     * Hyphenated variable naming convention, e.g., "lower-hyphen".
     * 带连接符的变量名转换
     */
    LOWER_HYPHEN(CharMatcher.is('-'), "-"),

    /**
     * C++ variable naming convention, e.g., "lower_underscore".
     * C++ 变量名转换
     */
    LOWER_UNDERSCORE(CharMatcher.is('_'), "_"),

    /**
     * Java variable naming convention, e.g., "lowerCamel".
     * Java 变量名转换
     */
    LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), ""),

    /**
     * Java and C++ class naming convention, e.g., "UpperCamel".
     * Java 和 C++ 类名转换
     */
    UPPER_CAMEL(CharMatcher.inRange('A', 'Z'), ""),

    /**
     * Java and C++ constant naming convention, e.g., "UPPER_UNDERSCORE".
     * Java 和 C++ 常量命名转换
     */
    UPPER_UNDERSCORE(CharMatcher.is('_'), "_");

    private final CharMatcher wordBoundary;
    private final String wordSeparator;

    CaseFormat(CharMatcher wordBoundary, String wordSeparator) {
        this.wordBoundary = wordBoundary;
        this.wordSeparator = wordSeparator;
    }

    /**
     * Converts the specified {@code String s} from this format to the specified {@code format}. A
     * "best effort" approach is taken; if {@code s} does not conform to the assumed format, then the
     * behavior of this method is undefined but we make a reasonable effort at converting anyway.
     *
     * 使用这个format将指定String s转为指定format.采取的是"尽力而为"的方法;假如s不符合设定的格式
     * 那么这个方法的行为将会是不确定的,但我们会尽量做出合理的转换
     *
     * 实际上我们使用的只有这一个方法
     */
    public String to(CaseFormat format, String s) {
        if (format == null) {
            throw new NullPointerException();
        }
        if (s == null) {
            throw new NullPointerException();
        }

        if (format == this) {
            return s;
        }

        /* optimize cases where no camel conversion is required */
        /* 没有驼峰转换的时候优化转换 */
        switch (this) {
            case LOWER_HYPHEN:
                switch (format) {
                    case LOWER_UNDERSCORE:
                        return s.replace('-', '_');
                    case UPPER_UNDERSCORE:
                        return Ascii.toUpperCase(s.replace('-', '_'));
                }
                break;
            case LOWER_UNDERSCORE:
                switch (format) {
                    case LOWER_HYPHEN:
                        return s.replace('_', '-');
                    case UPPER_UNDERSCORE:
                        return Ascii.toUpperCase(s);
                }
                break;
            case UPPER_UNDERSCORE:
                switch (format) {
                    case LOWER_HYPHEN:
                        return Ascii.toLowerCase(s.replace('_', '-'));
                    case LOWER_UNDERSCORE:
                        return Ascii.toLowerCase(s);
                }
                break;
        }

        // otherwise, deal with camel conversion
        // 处理驼峰转其他的转换
        StringBuilder out = null;
        int i = 0;
        int j = -1;
        // 将字符串按分隔符切分单词,转换每个单词
        while ((j = wordBoundary.indexIn(s, ++j)) != -1) {
            if (i == 0) {
                // include some extra space for separators
                // 为分隔符留出额外的空间
                out = new StringBuilder(s.length() + 4 * wordSeparator.length());
                // 第一个单词使用normalizeFirstWord处理
                out.append(format.normalizeFirstWord(s.substring(i, j)));
            } else {
                // 后续单词用normalizeWord处理
                out.append(format.normalizeWord(s.substring(i, j)));
            }
            out.append(format.wordSeparator);
            // 当前坐标后移
            i = j + wordSeparator.length();
        }
        if (i == 0) {
            return format.normalizeFirstWord(s);
        }
        // 处理最后一个分隔符右边的字符串
        out.append(format.normalizeWord(s.substring(i)));
        return out.toString();
    }

    /**
     * 将第一个单词普通化
     * LOWER_CAMEL -> 全小写
     * 其他 -> normalizeWord
     */
    private String normalizeFirstWord(String word) {
        switch (this) {
            case LOWER_CAMEL:
                return Ascii.toLowerCase(word);
            default:
                return normalizeWord(word);
        }
    }

    /**
     * 将单词普通化
     * LOWER_HYPHEN, LOWER_UNDERSCORE -> 全小写
     * LOWER_CAMEL, UPPER_CAMEL -> 第一个字母大写其他字母小写
     * UPPER_UNDERSCORE -> 全大写
     */
    private String normalizeWord(String word) {
        switch (this) {
            case LOWER_HYPHEN:
                return Ascii.toLowerCase(word);
            case LOWER_UNDERSCORE:
                return Ascii.toLowerCase(word);
            case LOWER_CAMEL:
                return firstCharOnlyToUpper(word);
            case UPPER_CAMEL:
                return firstCharOnlyToUpper(word);
            case UPPER_UNDERSCORE:
                return Ascii.toUpperCase(word);
        }
        throw new RuntimeException("unknown case: " + this);
    }

    /**
     * 将单词第一个字母变大写,其他变小写
     */
    private static String firstCharOnlyToUpper(String word) {
        int length = word.length();
        if (length == 0) {
            return word;
        }
        return new StringBuilder(length)
                .append(Ascii.toUpperCase(word.charAt(0)))
                .append(Ascii.toLowerCase(word.substring(1)))
                .toString();
    }
}
