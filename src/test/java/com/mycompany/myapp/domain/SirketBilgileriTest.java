package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SirketBilgileriTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SirketBilgileri.class);
        SirketBilgileri sirketBilgileri1 = new SirketBilgileri();
        sirketBilgileri1.setId(1L);
        SirketBilgileri sirketBilgileri2 = new SirketBilgileri();
        sirketBilgileri2.setId(sirketBilgileri1.getId());
        assertThat(sirketBilgileri1).isEqualTo(sirketBilgileri2);
        sirketBilgileri2.setId(2L);
        assertThat(sirketBilgileri1).isNotEqualTo(sirketBilgileri2);
        sirketBilgileri1.setId(null);
        assertThat(sirketBilgileri1).isNotEqualTo(sirketBilgileri2);
    }
}
