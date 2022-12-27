package com.mcp.infrastructure.common.domain.code;

/**
 * @author: KG
 * @description:
 * @date: Created in 2:33 PM 2019/7/8
 * @modified by:
 */

public interface BaseCode {
    interface Success {
        String CODE = "0";
        String MSG = "成功";
    }

    interface UnknownException {
        String CODE = "50000";
        String MSG = "Unknown Exception";
    }

    interface SystemBusy {
        String CODE = "50001";
        String MSG = "System Busy";
    }

    /**
     * 接口异常
     */
    interface InterfaceError {
        String CODE = "5000";
        String MSG = "接口异常";
    }

    /**
     * 参数校验异常
     */
    interface ParamValidationError {
        String CODE = "1001";
        String MSG = "参数校验失败";
    }
}
