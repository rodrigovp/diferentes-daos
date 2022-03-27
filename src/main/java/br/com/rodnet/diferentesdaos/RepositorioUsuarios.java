package br.com.rodnet.diferentesdaos;

import java.util.List;

public interface RepositorioUsuarios {

    void save(Usuario umUsuario);

    void deleteById(Long id);

    List<Usuario> findAll();

    void close();
}
