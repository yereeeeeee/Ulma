package com.ssafy11.domain.Account;

public enum BankCode {
    한국은행("001"),
    산업은행("002"),
    기업은행("003"),
    국민은행("004"),
    외환은행("005"),
    수협중앙회("007"),
    수출입은행("008"),
    농협은행("011"),
    지역농축협("012"),
    우리은행("020"),
    SC은행("023"),
    한국씨티은행("027"),
    대구은행("031"),
    부산은행("032"),
    광주은행("034"),
    제주은행("035"),
    전북은행("037"),
    경남은행("039"),
    새마을금고중앙회("045"),
    신협중앙회("048"),
    상호저축은행("050"),
    중국은행("051"),
    모건스탠리은행("052"),
    HSBC은행("054"),
    도이치은행("055"),
    알비에스피엘씨은행("056"),
    제이피모간체이스은행("057"),
    미즈호은행("058"),
    미쓰비시도쿄UFJ은행("059"),
    BOA은행("060"),
    비엔피파리바은행("061"),
    중국공상은행("062"),
    산림조합중앙회("064"),
    우체국("071"),
    신용보증기금("076"),
    기술보증기금("077"),
    KEB하나은행("081"),
    신한은행("088"),
    케이뱅크("089"),
    카카오뱅크("090"),
    토스뱅크("092"),
    얼마페이("123"),
    TEST("999");

    private final String code;

    BankCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    // Enum을 JSON에서 받을 때 매핑하는 로직
    public static BankCode fromCode(String code) {
        for (BankCode bankCode : BankCode.values()) {
            if (bankCode.getCode().equals(code)) {
                return bankCode;
            }
        }
        throw new IllegalArgumentException("Unknown bank code: " + code);
    }
}
