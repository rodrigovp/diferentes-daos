package br.com.rodnet.diferentesdaos.jpa;

import br.com.rodnet.diferentesdaos.RepositorioUsuariosTest;
import br.com.rodnet.diferentesdaos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class RepositorioUsuariosJpaTest extends RepositorioUsuariosTest {

    @Autowired
    private RepositorioUsuariosJpa repositorioUsuariosJpa;

    @PostConstruct
    void init(){
        this.repositorioUsuarios = repositorioUsuariosJpa;
    }
}
