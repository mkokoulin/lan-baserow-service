package com.lan.app.infrastructure.baserow.dto;

public enum BaserowCoworkingNotificationStatus {
    PENDING(5541178),
    SENDING(5541179),
    SENT(5541180),
    FAILED(5541181);

    private final int value;

    BaserowCoworkingNotificationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
