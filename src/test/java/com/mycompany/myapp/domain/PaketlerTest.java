package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PaketlerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paketler.class);
        Paketler paketler1 = new Paketler();
        paketler1.setId(1L);
        Paketler paketler2 = new Paketler();
        paketler2.setId(paketler1.getId());
        assertThat(paketler1).isEqualTo(paketler2);
        paketler2.setId(2L);
        assertThat(paketler1).isNotEqualTo(paketler2);
        paketler1.setId(null);
        assertThat(paketler1).isNotEqualTo(paketler2);
    }
}
