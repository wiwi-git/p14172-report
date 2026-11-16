package com.p14172wi.global.rsData;

/**
 * API 공통 응답 형식
 *
 * @param resultCode 결과 코드 (예: "201-1", "200-1")
 * @param msg        사용자에게 보여줄 메시지
 * @param data       응답 데이터 (없으면 null)
 */
public record RsData<T>(
        String resultCode,
        String msg,
        T data
) {
    public RsData(String resultCode, String msg) {
        this(resultCode, msg, null);
    }
}
