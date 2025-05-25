package com.tectonic.tectonic.system.api;


import com.tectonic.tectonic.system.api.domain.bo.RemoteLoginInfoBo;
import com.tectonic.tectonic.system.api.domain.bo.RemoteOperLogBo;

/**
 * 日志服务
 *
 * @author Lion Li
 */
public interface RemoteLogService {

    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     */
    void saveLog(RemoteOperLogBo sysOperLog);

    /**
     * 保存访问记录
     *
     * @param sysLogininfor 访问实体
     */
    void saveLogininfor(RemoteLoginInfoBo sysLogininfor);

}
