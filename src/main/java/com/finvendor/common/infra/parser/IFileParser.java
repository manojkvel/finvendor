package com.finvendor.common.infra.parser;

import java.util.Map;

/**
 * Ayush on 8-Aug-2018
 * @param <V>
 */
public interface IFileParser<K,V> {

    Map<K, V> parse(String  fileName) throws Exception;
}
