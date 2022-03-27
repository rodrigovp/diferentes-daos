package br.com.rodnet.diferentesdaos;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"nome", "dataNascimento"})
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    public Usuario(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public void atualizarNome(String novoNome) {
        this.nome = novoNome;
    }
}
