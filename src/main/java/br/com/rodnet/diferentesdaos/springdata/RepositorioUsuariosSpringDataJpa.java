package br.com.rodnet.diferentesdaos.springdata;

import br.com.rodnet.diferentesdaos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuariosSpringDataJpa extends JpaRepository<Usuario, Long> {
}
