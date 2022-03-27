package br.com.rodnet.diferentesdaos.springdata;

import br.com.rodnet.diferentesdaos.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RepositorioUsuariosSpringDataJpaTest {

    @Autowired
    private RepositorioUsuariosSpringDataJpa repositorioUsuarios;

    private Usuario umUsuario;

    @BeforeEach
    void setUp(){
        umUsuario = new Usuario("José", LocalDate.of(2010, 10, 10));
        umUsuario = repositorioUsuarios.save(umUsuario);
    }

    @AfterEach
    void tearDown(){
        repositorioUsuarios.deleteById(umUsuario.getId());
    }

    @Test
    void buscarOUsuario(){
        var usuarioPersistido = repositorioUsuarios.findAll();

        assertThat(usuarioPersistido).containsOnlyOnce(umUsuario);
    }

    @Test
    void atualizarOUsuario(){
        umUsuario.atualizarNome("Pedro");
        Usuario usuarioAtualizado = repositorioUsuarios.save(umUsuario);

        assertThat(usuarioAtualizado).isEqualTo(umUsuario);
    }
}
