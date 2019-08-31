package com.funch.ledger.conf;

public enum ErrorCode {

    ERROR_ENTITY_VALIDATION_NULL(400, "E001", "필수 값들을 반드시 입력해야합니다.");

    private int status;
    private final String code;
    private final String message;

    ErrorCode(int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public int getStatus() { return status; }
    public String getCode() { return code; }
    public String getMessage() {
        return message;
    }
}
