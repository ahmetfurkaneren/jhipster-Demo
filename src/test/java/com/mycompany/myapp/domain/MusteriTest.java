package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MusteriTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Musteri.class);
        Musteri musteri1 = new Musteri();
        musteri1.setId(1L);
        Musteri musteri2 = new Musteri();
        musteri2.setId(musteri1.getId());
        assertThat(musteri1).isEqualTo(musteri2);
        musteri2.setId(2L);
        assertThat(musteri1).isNotEqualTo(musteri2);
        musteri1.setId(null);
        assertThat(musteri1).isNotEqualTo(musteri2);
    }
}
