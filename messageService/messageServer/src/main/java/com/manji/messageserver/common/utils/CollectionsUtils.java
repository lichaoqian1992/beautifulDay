package com.manji.messageserver.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by pudding on 2016-12-12.
 */
public class CollectionsUtils {

    /**
     * 判断判断集合为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmptyCollection(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断Map为空
     *
     * @param map
     * @return
     */
    public static boolean isEmptyMap(Map<?, ?> map) {
        return map == null || isEmptyCollection(map.keySet());
    }
}
