package com.coopcycle.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeliverManMapperTest {

    private DeliverManMapper deliverManMapper;

    @BeforeEach
    public void setUp() {
        deliverManMapper = new DeliverManMapperImpl();
    }
}
