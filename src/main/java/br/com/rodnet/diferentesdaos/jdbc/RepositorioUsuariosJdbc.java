package br.com.rodnet.diferentesdaos.jdbc;

import br.com.rodnet.diferentesdaos.RepositorioUsuarios;
import br.com.rodnet.diferentesdaos.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioUsuariosJdbc implements RepositorioUsuarios {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String usuario;

    @Value("${spring.datasource.password}")
    private String senha;

    private Connection conexao;

    @PostConstruct
    void posConstrutor(){
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Usuario umUsuario) {
        if(!existe(umUsuario)) salvar(umUsuario);
        else atualizar(umUsuario);
    }

    private boolean existe(Usuario umUsuario) {
        return umUsuario.getId() != null;
    }

    private void atualizar(Usuario usuario) {
        try(var statement = conexao.prepareStatement("update usuarios set nome = ? where id = ?")){
            statement.setString(1, usuario.getNome());
            statement.setLong(2, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try(var statement = conexao.prepareStatement("delete from usuarios where id = ?")){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> findAll() {
        var usuarios = new ArrayList<Usuario>();
        try(var statement = conexao.prepareStatement("select * from usuarios");
        var resultset = statement.executeQuery()){
            while(resultset.next()){
                var id = resultset.getLong("id");
                var nome = resultset.getString("nome");
                var dataNascimento = resultset.getObject("data_nascimento", LocalDate.class);
                var usuario = new Usuario(nome, dataNascimento);
                usuario.setId(id);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void close() {
        try {
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean idEhNulo(Usuario umUsuario) {
        return umUsuario.getId() == null;
    }

    private void salvar(Usuario usuario){
        var sql = "insert into usuarios (nome, data_nascimento) values (?, ?)";
        try(var statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, usuario.getNome());
            statement.setObject(2, usuario.getDataNascimento());
            statement.executeUpdate();

            try(var ids = statement.getGeneratedKeys()){
                ids.next();
                usuario.setId(ids.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
