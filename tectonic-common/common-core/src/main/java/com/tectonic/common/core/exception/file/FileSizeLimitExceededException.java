package com.tectonic.common.core.exception.file;

import java.io.Serial;

/**
 * 文件名大小限制异常类
 *
 * @author xiaoyan.song
 */
public class FileSizeLimitExceededException extends FileException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
