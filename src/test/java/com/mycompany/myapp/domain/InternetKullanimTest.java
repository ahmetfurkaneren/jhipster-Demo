package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InternetKullanimTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InternetKullanim.class);
        InternetKullanim internetKullanim1 = new InternetKullanim();
        internetKullanim1.setId(1L);
        InternetKullanim internetKullanim2 = new InternetKullanim();
        internetKullanim2.setId(internetKullanim1.getId());
        assertThat(internetKullanim1).isEqualTo(internetKullanim2);
        internetKullanim2.setId(2L);
        assertThat(internetKullanim1).isNotEqualTo(internetKullanim2);
        internetKullanim1.setId(null);
        assertThat(internetKullanim1).isNotEqualTo(internetKullanim2);
    }
}
