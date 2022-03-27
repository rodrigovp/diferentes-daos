package br.com.rodnet.diferentesdaos;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional //repositorio jdbc não precisa
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class RepositorioUsuariosTest {

    protected RepositorioUsuarios repositorioUsuarios;

    private Usuario umUsuario;

    @BeforeEach
    void setUp(){
        umUsuario = new Usuario("José", LocalDate.of(2010, 10, 10));
        repositorioUsuarios.save(umUsuario);
    }

    @AfterEach
    void tearDown(){
        repositorioUsuarios.deleteById(umUsuario.getId());
    }

    @AfterAll
    void tearDownAll(){
        repositorioUsuarios.close();
    }

    @Test
    void buscarOUsuario(){
        var usuarioPersistido = repositorioUsuarios.findAll();

        assertThat(usuarioPersistido).containsOnlyOnce(umUsuario);
    }

    @Test
    void atualizarOUsuario(){
        umUsuario.atualizarNome("Pedro");
        repositorioUsuarios.save(umUsuario);

        var usuarioPersistido = repositorioUsuarios.findAll();

        assertThat(usuarioPersistido).containsOnlyOnce(umUsuario);
    }
}
