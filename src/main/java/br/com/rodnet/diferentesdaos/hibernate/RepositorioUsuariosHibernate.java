package br.com.rodnet.diferentesdaos.hibernate;

import br.com.rodnet.diferentesdaos.RepositorioUsuarios;
import br.com.rodnet.diferentesdaos.Usuario;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RepositorioUsuariosHibernate implements RepositorioUsuarios {

    @PersistenceContext
    private EntityManager em;

    private Session sessao;

    @PostConstruct
    void posConstrutor(){
        sessao = em.unwrap(Session.class);
    }

    @Override
    public void save(Usuario umUsuario) {
        sessao.save(umUsuario);
    }

    @Override
    public void deleteById(Long id) {
        var usuario = sessao
                .createQuery("from Usuario where id = %d".formatted(id))
                .uniqueResult();
        sessao.delete(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return sessao.createQuery("from Usuario").getResultList();
    }

    @Override
    public void close() {
        sessao.close();
    }
}
