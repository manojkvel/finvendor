package com.finvendor.common.infra.persist;

public interface IFilePersist<T> {
    T persist(String filePath) throws RuntimeException;
}
