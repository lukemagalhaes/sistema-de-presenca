package br.com.sistemadepresenca.turma;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Collection;

@DataJpaTest
class TurmaRepositoryTest {

    @Autowired
    private TurmaRepository turmaRepository;

    // @Test
    // void testFindBySerieContainingIgnoreCaseExactMatch() {
    //     Turma turma = new Turma();
    //     turma.setSerie("1A");
    //     turmaRepository.save(turma);

    //     Collection<Turma> found = turmaRepository.findBySerieContainingIgnoreCase("1A");

    //     assertThat(found).isNotEmpty();
    //     assertThat(found.iterator().next().getSerie()).isEqualTo("1A");
    // }

    // @Test
    // void testFindBySerieContainingIgnoreCaseDifferentCase() {
    //     Turma turma = new Turma();
    //     turma.setSerie("1A");
    //     turmaRepository.save(turma);

    //     Collection<Turma> found = turmaRepository.findBySerieContainingIgnoreCase("1a");

    //     assertThat(found).isNotEmpty();
    //     assertThat(found.iterator().next().getSerie()).isEqualTo("1A");
    // }

    // @Test
    // void testFindBySerieContainingIgnoreCasePartialMatch() {
    //     Turma turma = new Turma();
    //     turma.setSerie("1A");
    //     turmaRepository.save(turma);

    //     Collection<Turma> found = turmaRepository.findBySerieContainingIgnoreCase("1");

    //     assertThat(found).isNotEmpty();
    //     assertThat(found.iterator().next().getSerie()).isEqualTo("1A");
    // }
}
