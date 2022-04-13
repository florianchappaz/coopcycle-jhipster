package com.coopcycle.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.coopcycle.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliverManTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliverMan.class);
        DeliverMan deliverMan1 = new DeliverMan();
        deliverMan1.setId(1L);
        DeliverMan deliverMan2 = new DeliverMan();
        deliverMan2.setId(deliverMan1.getId());
        assertThat(deliverMan1).isEqualTo(deliverMan2);
        deliverMan2.setId(2L);
        assertThat(deliverMan1).isNotEqualTo(deliverMan2);
        deliverMan1.setId(null);
        assertThat(deliverMan1).isNotEqualTo(deliverMan2);
    }
}
