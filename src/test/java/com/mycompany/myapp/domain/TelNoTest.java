package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TelNoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TelNo.class);
        TelNo telNo1 = new TelNo();
        telNo1.setId(1L);
        TelNo telNo2 = new TelNo();
        telNo2.setId(telNo1.getId());
        assertThat(telNo1).isEqualTo(telNo2);
        telNo2.setId(2L);
        assertThat(telNo1).isNotEqualTo(telNo2);
        telNo1.setId(null);
        assertThat(telNo1).isNotEqualTo(telNo2);
    }
}
