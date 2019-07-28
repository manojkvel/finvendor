package com.finvendor.common.infra.upload.exception;

import com.finvendor.common.exception.FvTechnicalException;

public class FileUploadException extends FvTechnicalException {
    public FileUploadException(String message,Exception e) {
        super(message, e);
    }
}
