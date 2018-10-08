package com.finvendor.server.common.infra.persist;

public interface IFilePersist<T> {
    T persist(String filePath) throws RuntimeException;
}
