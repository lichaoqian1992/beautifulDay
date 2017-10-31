package com.manji.messageserver.utils;


import com.manji.messageserver.common.errorcode.ErrorCodeEnum;
import com.manji.messageserver.common.exception.AssertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
/**
 * Created by luobairan on 2016/7/26.
 */
public class AssertUtil {

    private static Logger logger = LoggerFactory.getLogger(AssertUtil.class);

    public static void isNull(Object object, ErrorCodeEnum errorCode, Object... params) {
        if (object != null) {
            logger.error("应该为空: object={}, errorCode={}, params={}", object, errorCode, params);
            throw new AssertException(errorCode);
        }
    }

    public static void notNull(Object object, ErrorCodeEnum errorCode, Object... params) {
        if (object == null) {
            logger.error("不应为空: object={}, errorCode={}, params={}", null, errorCode, params);
            throw new AssertException(errorCode);
        }
    }

    public static void notEmpty(Collection<?> collection, ErrorCodeEnum errorCode, Object... params) {
        if (CollectionUtils.isEmpty(collection)) {
            logger.error("不应为空: collection={}, errorCode={}, params={}", collection, errorCode,
                    params);
            throw new AssertException(errorCode);
        }
    }

    public static void isEmpty(Collection<?> collection, ErrorCodeEnum errorCode, Object... params) {
        if (!CollectionUtils.isEmpty(collection)) {
            logger.error("应该为空: collection={}, errorCode={}, params={}", collection, errorCode,
                    params);
            throw new AssertException(errorCode);
        }
    }

    public static void isEmpty(String string, ErrorCodeEnum errorCode) {
        if (!StringUtils.isEmpty(string)) {
            logger.error("应该为空: string={}, errorCode={}", string, errorCode);
            throw new AssertException(errorCode);
        }
    }

    public static void notEmpty(String string, ErrorCodeEnum errorCode) {
        if (StringUtils.isEmpty(string)) {
            logger.error("不应为空: string={}, message={}", string, errorCode);
            throw new AssertException(errorCode);
        }
    }

    public static void isTrue(boolean flag, ErrorCodeEnum errorCode, Object... params) {
        if (!flag) {
            logger.error("应该为TRUE: flag={}, errorCode={}, params={}", flag, errorCode, params);
            throw new AssertException(errorCode);
        }
    }

    public static void isFalse(boolean flag, ErrorCodeEnum errorCode, Object... params) {
        if (flag) {
            logger.error("应该为FALSE: flag={}, errorCode={}, params={}", flag, errorCode, params);
            throw new AssertException(errorCode);
        }
    }

    public static <T extends Enum<T>> void legalStatus(Enum<T> actualStatus,
                                                       Enum<T> expectedStatus,
                                                       ErrorCodeEnum errorCode) {
        if (actualStatus != expectedStatus) {
            logger.error("状态错误：errorCode={}, actualStatus={}, expectedStatus={}", errorCode,
                    actualStatus, expectedStatus);
            throw new AssertException(errorCode);
        }
    }

    public static <T extends Enum<T>> void legalStatuses(Enum<T> actualStatus,
                                                         Enum<T>[] expectedStatuses,
                                                         ErrorCodeEnum errorCode) {
        List<Enum<T>> statusList = Arrays.asList(expectedStatuses);
        if (!statusList.contains(actualStatus)) {
            logger.error("状态错误：errorCode={}, actualStatus={}, expectedStatuses={}", errorCode,
                    actualStatus, expectedStatuses);
            throw new AssertException(errorCode);
        }
    }

    public static void isEquals(String actual, String expected, ErrorCodeEnum errorCode) {
        if (!actual.equals(expected)) {
            logger.error("{}：actual={}, expected={}", errorCode.getMessage(), actual, expected);
            throw new AssertException(errorCode);
        }
    }

    public static void notEquals(String first, String second, ErrorCodeEnum errorCode) {
        if (first.equals(second)) {
            logger.error("{}：first={}, second={}", errorCode.getMessage(), first, second);
            throw new AssertException(errorCode);
        }
    }
}
