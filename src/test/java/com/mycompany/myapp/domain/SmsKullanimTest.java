package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SmsKullanimTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SmsKullanim.class);
        SmsKullanim smsKullanim1 = new SmsKullanim();
        smsKullanim1.setId(1L);
        SmsKullanim smsKullanim2 = new SmsKullanim();
        smsKullanim2.setId(smsKullanim1.getId());
        assertThat(smsKullanim1).isEqualTo(smsKullanim2);
        smsKullanim2.setId(2L);
        assertThat(smsKullanim1).isNotEqualTo(smsKullanim2);
        smsKullanim1.setId(null);
        assertThat(smsKullanim1).isNotEqualTo(smsKullanim2);
    }
}
