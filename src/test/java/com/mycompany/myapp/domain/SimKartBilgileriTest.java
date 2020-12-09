package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SimKartBilgileriTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SimKartBilgileri.class);
        SimKartBilgileri simKartBilgileri1 = new SimKartBilgileri();
        simKartBilgileri1.setId(1L);
        SimKartBilgileri simKartBilgileri2 = new SimKartBilgileri();
        simKartBilgileri2.setId(simKartBilgileri1.getId());
        assertThat(simKartBilgileri1).isEqualTo(simKartBilgileri2);
        simKartBilgileri2.setId(2L);
        assertThat(simKartBilgileri1).isNotEqualTo(simKartBilgileri2);
        simKartBilgileri1.setId(null);
        assertThat(simKartBilgileri1).isNotEqualTo(simKartBilgileri2);
    }
}
