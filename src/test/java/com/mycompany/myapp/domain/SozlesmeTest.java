package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SozlesmeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sozlesme.class);
        Sozlesme sozlesme1 = new Sozlesme();
        sozlesme1.setId(1L);
        Sozlesme sozlesme2 = new Sozlesme();
        sozlesme2.setId(sozlesme1.getId());
        assertThat(sozlesme1).isEqualTo(sozlesme2);
        sozlesme2.setId(2L);
        assertThat(sozlesme1).isNotEqualTo(sozlesme2);
        sozlesme1.setId(null);
        assertThat(sozlesme1).isNotEqualTo(sozlesme2);
    }
}
