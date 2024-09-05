package br.edu.univille.br.spa.modelo.repositorio;

import br.edu.univille.br.spa.modelo.entidade.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepositorio
        extends JpaRepository<Pessoa, Long> {
}
