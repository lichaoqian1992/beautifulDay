package com.manji.lineol.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.manji.lineol.common.errorcode.ErrorCodeEnum;
import com.manji.lineol.common.exception.AssertException;

public class AssertUtil {

	public static void isNull(Object object, ErrorCodeEnum errorCode, Object... params) {
		if (object != null) {
			throw new AssertException(errorCode);
		}
	}

	public static void notNull(Object object, ErrorCodeEnum errorCode, Object... params) {
		if (object == null) {
			throw new AssertException(errorCode);
		}
	}

	public static void notEmpty(Collection<?> collection, ErrorCodeEnum errorCode, Object... params) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new AssertException(errorCode);
		}
	}

	public static void isEmpty(Collection<?> collection, ErrorCodeEnum errorCode, Object... params) {
		if (!CollectionUtils.isEmpty(collection)) {
			throw new AssertException(errorCode);
		}
	}

	public static void isEmpty(String string, ErrorCodeEnum errorCode) {
		if (!StringUtils.isEmpty(string)) {
			throw new AssertException(errorCode);
		}
	}

	public static void notEmpty(String string, ErrorCodeEnum errorCode) {
		if (StringUtils.isEmpty(string)) {
			throw new AssertException(errorCode);
		}
	}

	public static void isTrue(boolean flag, ErrorCodeEnum errorCode, Object... params) {
		if (!flag) {
			throw new AssertException(errorCode);
		}
	}

	public static void isFalse(boolean flag, ErrorCodeEnum errorCode, Object... params) {
		if (flag) {
			throw new AssertException(errorCode);
		}
	}

	public static <T extends Enum<T>> void legalStatus(Enum<T> actualStatus, Enum<T> expectedStatus,
			ErrorCodeEnum errorCode) {
		if (actualStatus != expectedStatus) {
			throw new AssertException(errorCode);
		}
	}

	public static <T extends Enum<T>> void legalStatuses(Enum<T> actualStatus, Enum<T>[] expectedStatuses,
			ErrorCodeEnum errorCode) {
		List<Enum<T>> statusList = Arrays.asList(expectedStatuses);
		if (!statusList.contains(actualStatus)) {
			throw new AssertException(errorCode);
		}
	}

	public static void isEquals(String actual, String expected, ErrorCodeEnum errorCode) {
		if (!actual.equals(expected)) {
			throw new AssertException(errorCode);
		}
	}

	public static void notEquals(String first, String second, ErrorCodeEnum errorCode) {
		if (first.equals(second)) {
			throw new AssertException(errorCode);
		}
	}

}
