package br.com.biblioteca.servico;

import br.com.biblioteca.excecao.LivroNaoDisponivelException;
import br.com.biblioteca.modelo.Emprestimo;
import br.com.biblioteca.modelo.Livro;
import br.com.biblioteca.modelo.Membro;

import java.time.LocalDate;

public class EmprestimoServico {


public Emprestimo realizarEmprestimo(Livro livro, Membro membro, LocalDate dataDevolucaoEsperada) throws LivroNaoDisponivelException {   // método responsável por verificar se existe o livro no estoque, caso tenha, ira diminuir a quantidade em 1, em seguida definir a data em que foi realizado o emrpestimo após isso, criara um objeto constando que o emprestimo foi realizado com sucesso !

    if(livro.getQuantidadeDisponivel()>0){
        livro.decrementarQuantidade();
        LocalDate dataEmprestimo = LocalDate.now();

        Emprestimo Emprestimo1 = new Emprestimo(livro,membro,dataEmprestimo, dataDevolucaoEsperada, null);
        return Emprestimo1;

    }else {
        throw  new LivroNaoDisponivelException("O livro " + livro.getTitulo()+ "não esta disponível no estoque !");
    }
}

public void realizarDevolucao(Emprestimo emprestimo, LocalDate dataDevolucaoReal){   // Método responsável por marcar a devolução do livro
    if(emprestimo != null){
        emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
        emprestimo.setStatus(Emprestimo.StatusEmprestimo.DEVOLVIDO);
     Livro LivroDevolvido = Emprestimo.getLivro();
    }

}

}
