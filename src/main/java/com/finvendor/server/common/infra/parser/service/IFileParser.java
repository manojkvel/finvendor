package com.finvendor.server.common.infra.parser.service;

import com.finvendor.common.util.Pair;

import java.util.Map;

/**
 * Ayush on 8-Aug-2018
 * @param <V>
 */
public interface IFileParser<K,V> {

    Map<K, V> parse(String  fileName) throws Exception;
}
