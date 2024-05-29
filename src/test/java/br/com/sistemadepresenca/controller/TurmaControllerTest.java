package br.com.sistemadepresenca.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.sistemadepresenca.turma.Turma;
import br.com.sistemadepresenca.turma.TurmaRepository;
import br.com.sistemadepresenca.turma.TurmaRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

class TurmaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TurmaRepository turmaRepository;

    @InjectMocks
    private TurmaController turmaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(turmaController).build();
    }

    // @Test
    // void testSaveTurma() throws Exception {
    //     TurmaRequestDTO turmaRequestDTO = new TurmaRequestDTO(0, null, null);
    //     Turma turma = new Turma(turmaRequestDTO);

    //     when(turmaRepository.save(any(Turma.class))).thenReturn(turma);

    //     mockMvc.perform(post("/api/turma")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(new ObjectMapper().writeValueAsString(turmaRequestDTO)))
    //             .andExpect(status().isOk());
    // }

    @Test
    void testGetAllTurmas() throws Exception {
        List<Turma> turmaList = new ArrayList<>();
        when(turmaRepository.findAll()).thenReturn(turmaList);

        mockMvc.perform(get("/api/turma"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
