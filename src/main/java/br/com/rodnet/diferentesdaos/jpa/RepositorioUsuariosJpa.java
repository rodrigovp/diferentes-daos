package br.com.rodnet.diferentesdaos.jpa;

import br.com.rodnet.diferentesdaos.RepositorioUsuarios;
import br.com.rodnet.diferentesdaos.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
class RepositorioUsuariosJpa implements RepositorioUsuarios {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Usuario umUsuario){
        em.persist(umUsuario);
        em.flush();
    }

    @Override
    public void deleteById(Long id){
        Usuario usuario = em.find(Usuario.class, id);
        em.remove(usuario);
    }

    @Override
    public List<Usuario> findAll(){
        return em.createQuery("from Usuario").getResultList();
    }

    @Override
    public void close() {
        em.close();
    }
}
