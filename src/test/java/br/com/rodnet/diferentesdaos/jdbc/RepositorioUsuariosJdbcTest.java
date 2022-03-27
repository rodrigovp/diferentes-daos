package br.com.rodnet.diferentesdaos.jdbc;

import br.com.rodnet.diferentesdaos.RepositorioUsuariosTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class RepositorioUsuariosJdbcTest extends RepositorioUsuariosTest {

    @Autowired
    private RepositorioUsuariosJdbc repositorioUsuariosJdbc;

    @PostConstruct
    @ValueSource()
    void init(){
        this.repositorioUsuarios = repositorioUsuariosJdbc;
    }
}
