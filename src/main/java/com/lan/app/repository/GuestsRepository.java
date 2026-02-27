package com.lan.app.repository;

import com.lan.app.domain.Guest;

public interface GuestsRepository {
    Guest get(String guestId);
}
