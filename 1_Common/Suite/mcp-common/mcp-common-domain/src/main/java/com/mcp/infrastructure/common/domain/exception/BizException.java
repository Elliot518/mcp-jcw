package com.mcp.infrastructure.common.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: KG
 * @description: 业务异常类信息
 * @date: Created in 9:20 PM 2019/8/30
 * @modified by:
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -7864604160297181941L;

    /**
     * 错误码
     */
    protected final ErrorCode errorCode;

    /**
     * 这个是和谐一些不必要的地方,冗余的字段
     * 尽量不要用
     */
    private String code;

    /**
     * 无参默认构造UNSPECIFIED
     */
    public BizException() {
        super(BaseErrorCodeEnum.UNSPECIFIED.getDescription());
        this.errorCode = BaseErrorCodeEnum.UNSPECIFIED;
    }

    /**
     * 指定错误码构造通用异常
     *
     * @param errorCode 错误码
     */
    public BizException(final ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    /**
     * 指定详细描述构造通用异常
     *
     * @param detailedMessage 详细描述
     */
    public BizException(final String detailedMessage) {
        super(detailedMessage);
        this.errorCode = BaseErrorCodeEnum.UNSPECIFIED;
    }

    /**
     * 指定导火索构造通用异常
     *
     * @param t 导火索
     */
    public BizException(final Throwable t) {
        super(t);
        this.errorCode = BaseErrorCodeEnum.UNSPECIFIED;
    }

    /**
     * 构造通用异常
     *
     * @param errorCode       错误码
     * @param detailedMessage 详细描述
     */
    public BizException(final ErrorCode errorCode, final String detailedMessage) {
        super(detailedMessage);
        this.errorCode = errorCode;
    }

    /**
     * 构造通用异常
     *
     * @param errorCode 错误码
     * @param t         导火索
     */
    public BizException(final ErrorCode errorCode, final Throwable t) {
        super(errorCode.getDescription(), t);
        this.errorCode = errorCode;
    }

    /**
     * 构造通用异常
     *
     * @param detailedMessage 详细描述
     * @param t               导火索
     */
    public BizException(final String detailedMessage, final Throwable t) {
        super(detailedMessage, t);
        this.errorCode = BaseErrorCodeEnum.UNSPECIFIED;
    }

    /**
     * 构造通用异常
     *
     * @param errorCode       错误码
     * @param detailedMessage 详细描述
     * @param t               导火索
     */
    public BizException(final ErrorCode errorCode, final String detailedMessage,
                        final Throwable t) {
        super(detailedMessage, t);
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property rate of errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }


}
