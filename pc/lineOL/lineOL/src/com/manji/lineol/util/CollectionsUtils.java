package com.manji.lineol.util;

import java.util.Collection;
import java.util.Map;

public class CollectionsUtils {
	
	 /**
     * 判断判断集合为空
     * @param collection
     * @return
     */
    public static boolean isEmptyCollection(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断Map为空
     * @param map
     * @return
     */
    public static boolean isEmptyMap(Map<?, ?> map) {
        return map == null || isEmptyCollection(map.keySet());
    }

}
