package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SozlesmeninPaketleriTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SozlesmeninPaketleri.class);
        SozlesmeninPaketleri sozlesmeninPaketleri1 = new SozlesmeninPaketleri();
        sozlesmeninPaketleri1.setId(1L);
        SozlesmeninPaketleri sozlesmeninPaketleri2 = new SozlesmeninPaketleri();
        sozlesmeninPaketleri2.setId(sozlesmeninPaketleri1.getId());
        assertThat(sozlesmeninPaketleri1).isEqualTo(sozlesmeninPaketleri2);
        sozlesmeninPaketleri2.setId(2L);
        assertThat(sozlesmeninPaketleri1).isNotEqualTo(sozlesmeninPaketleri2);
        sozlesmeninPaketleri1.setId(null);
        assertThat(sozlesmeninPaketleri1).isNotEqualTo(sozlesmeninPaketleri2);
    }
}
