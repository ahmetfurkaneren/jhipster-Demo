package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DakikaKullanimTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DakikaKullanim.class);
        DakikaKullanim dakikaKullanim1 = new DakikaKullanim();
        dakikaKullanim1.setId(1L);
        DakikaKullanim dakikaKullanim2 = new DakikaKullanim();
        dakikaKullanim2.setId(dakikaKullanim1.getId());
        assertThat(dakikaKullanim1).isEqualTo(dakikaKullanim2);
        dakikaKullanim2.setId(2L);
        assertThat(dakikaKullanim1).isNotEqualTo(dakikaKullanim2);
        dakikaKullanim1.setId(null);
        assertThat(dakikaKullanim1).isNotEqualTo(dakikaKullanim2);
    }
}
