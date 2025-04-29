package br.com.biblioteca.servico;

import br.com.biblioteca.excecao.LivroNaoDisponivelException;
import br.com.biblioteca.modelo.Emprestimo;
import br.com.biblioteca.modelo.Livro;
import br.com.biblioteca.modelo.Membro;

import java.time.LocalDate;

public class EmprestimoServico {


/*deixa eu ver se entendi então,
 eu estou criando um método, do tipo Empréstimo,
 que pode ser acessado dentro dentro e fora dessa classe por ser público,
  depois crio os parâmetros, como nessa situação não tenho os objetos de membro, livro e local Date pronto eu uso apenas os mesmos nomes como base para a situação atual onde ainda não estão criados, depois disso, existe uma exceção para caso não seja possível chamar o método, em seguida é criado a condição para o funcionamento do método, onde, ele puxa a quantidade disponível do livro em específico, verifica se existe mais de 0 em estoque, se por acaso existir livros em estoque ainda, ele decrementa a quantidade a partir do método criado, em seguida ele define a a data que foi realizado o emprestimo, no caso, quando foi decrementado a quantidade do livro existente no estoque*\

 */
public Emprestimo realizarEmprestimo(Livro livro, Membro membro, LocalDate dataDevolucaoEsperada) throws LivroNaoDisponivelException {
    if(livro.getQuantidadeDisponivel()>0){
        livro.decrementarQuantidade();
        LocalDate dataEmprestimo = LocalDate.now();

        Emprestimo Emprestimo1 = new Emprestimo(livro,membro,dataEmprestimo, dataDevolucaoEsperada, null);
        return Emprestimo1;

    }else {
        throw  new LivroNaoDisponivelException("O livro " + livro.getTitulo()+ "não esta disponível no estoque !");
    }
}

public void realizarDevolucao(Emprestimo emprestimo, LocalDate dataDevolucaoReal){
    if(emprestimo != null){
        emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
        emprestimo.setStatus(Emprestimo.StatusEmprestimo.DEVOLVIDO);
    }

}

}