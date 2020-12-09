package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FaturaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fatura.class);
        Fatura fatura1 = new Fatura();
        fatura1.setId(1L);
        Fatura fatura2 = new Fatura();
        fatura2.setId(fatura1.getId());
        assertThat(fatura1).isEqualTo(fatura2);
        fatura2.setId(2L);
        assertThat(fatura1).isNotEqualTo(fatura2);
        fatura1.setId(null);
        assertThat(fatura1).isNotEqualTo(fatura2);
    }
}
