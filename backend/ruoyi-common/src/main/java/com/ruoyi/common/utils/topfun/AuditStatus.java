package com.ruoyi.common.utils.topfun;

public enum AuditStatus {
    /**
     * 未审核
     */
    UNAUDITED(0, "审核中"),
    /**
     * 审核通过
     */
    AUDIT_PASS(1, "审核通过"),
    /**
     * 审核不通过
     */
    AUDIT_FAIL(2, "审核不通过");

    private final Integer code;
    private final String info;

    AuditStatus(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static AuditStatus getAuditStatus(Integer code) {
        for (AuditStatus auditStatus : AuditStatus.values()) {
            if (auditStatus.getCode().equals(code)) {
                return auditStatus;
            }
        }
        return null;
    }
}
