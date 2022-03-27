package br.com.rodnet.diferentesdaos.hibernate;

import br.com.rodnet.diferentesdaos.RepositorioUsuariosTest;
import br.com.rodnet.diferentesdaos.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioUsuariosHibernateTest extends RepositorioUsuariosTest {

    @Autowired
    private RepositorioUsuariosHibernate repositorioUsuariosHibernate;

    @PostConstruct
    void init(){
        this.repositorioUsuarios = repositorioUsuariosHibernate;
    }
}
