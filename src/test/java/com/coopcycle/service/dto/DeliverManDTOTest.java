package com.coopcycle.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.coopcycle.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliverManDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliverManDTO.class);
        DeliverManDTO deliverManDTO1 = new DeliverManDTO();
        deliverManDTO1.setId(1L);
        DeliverManDTO deliverManDTO2 = new DeliverManDTO();
        assertThat(deliverManDTO1).isNotEqualTo(deliverManDTO2);
        deliverManDTO2.setId(deliverManDTO1.getId());
        assertThat(deliverManDTO1).isEqualTo(deliverManDTO2);
        deliverManDTO2.setId(2L);
        assertThat(deliverManDTO1).isNotEqualTo(deliverManDTO2);
        deliverManDTO1.setId(null);
        assertThat(deliverManDTO1).isNotEqualTo(deliverManDTO2);
    }
}
