package br.com.biblioteca.servico;

import br.com.biblioteca.excecao.LivroNaoDisponivelException;
import br.com.biblioteca.modelo.Emprestimo;
import br.com.biblioteca.modelo.Livro;
import br.com.biblioteca.modelo.Membro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoServico {

    private static List<Emprestimo> emprestimosAtivos= new ArrayList<>(); // Criação da lista de emprestimos ativos que teoricamente existem.


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

 /* Passo a passo para entender oque realmente acontece dentro desse código,
Os parâmetros são os espaços reservados para os objetos reais para quando forem chamado, após isso, verifica-se se existe realmente um objeto através da condição "emprestimo != null"
se realmente existir um objeto então ele da continuidade definindo a data real de emprestimo, usando o método da classe emprestimo que é responsável por definir a data real do mesmo,
sendo ele o "setDataDevolucaoReal" e utilizando o atributo "dataDevolucaoReal" para receber a informação do dia que foi feito a devolução, em seguida, define-se o estatus do emprestimo 
com o método "Enum" que esta presente dentro da classe "Emprestimo" definindo seu valor como "DEVOLVIDO", Então, criase uma variável do tipo "Livro" que irá receber o objeto
"Livro" do objeto "emprestimo" que foi feito dentro do método "realizarEmprestimo", Se o "livroDevolvido" receber o objeto "Livro" ele aumentara a quantidade correspondente do mesmo,
e após isso as respostas caso de errado em algum momento durante o código!
*/
    
public void realizarDevolucao(Emprestimo emprestimo, LocalDate dataDevolucaoReal){
    if(emprestimo != null){
        emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
        emprestimo.setStatus(Emprestimo.StatusEmprestimo.DEVOLVIDO);
        Livro livroDevolvido = emprestimo.getLivro();
        if(livroDevolvido != null){
            livroDevolvido.incrementarQuantidade();
        }
        System.out.println("Devolução do livro: "+ emprestimo.getLivro().getTitulo()+ "\nDevolução realizada em: "+dataDevolucaoReal );
    }else {
        System.out.println("ERRO: Livro indisponível para devolção tente novamente mais tarde !");
    }



/*Método responsável por procurar dentro da lista de emprestimos ativos um emprestimo em específico pelo ID dela,
Explicação: Criação do método chamado 'buscarEmprestimoPorId' do tipo 'Emprestimo' 
cria-se um parâmetro chamado emprestimoId do tipo int, que ja tem a informação referente ao id do emprestimo do usuário
portanto, após a criação do parâmetro cria-se o laço de repetição for-each, onde nessa ocasião irá percorrer a lista de emprestimos ativos chamada 
emprestimosAtivos, o objeto empresitmo percorre então a lista, então cria-se a condição na qual o objeto emprestimo identificar o id igual 
ao do parâmetro, usa-se um return que irá mostrar como resultado o emprestimo em questão. Caso não encontre irá apenas retornar nulo !
*/
    public Emprestimo buscarEmprestimoPorID(int emprestimoId)(
        for(Emprestimo emprestimo: emprestimosAtivos ){
        if(emprestimo.getEmprestimoId()== emprestimoId){
            return emprestimo;
        
    );
            return null;
}

}
