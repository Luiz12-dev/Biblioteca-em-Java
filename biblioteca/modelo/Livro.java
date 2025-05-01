package br.com.biblioteca.modelo;

import br.com.biblioteca.excecao.LivroNaoDisponivelException;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private int anoPublicacao;
    private int quantidadeDisponivel=0;
    private int livroId;

    public Livro(String titulo, String autor, String isbn, int anoPublicacao, int quantidadeDisponivel, int livroId){
        this.titulo = titulo;
        this.autor= autor;
        this.isbn=isbn;
        this.anoPublicacao=anoPublicacao;
        this.quantidadeDisponivel= 0;
        this.livroId=livroId;
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);

    }

    public void decrementarQuantidade() throws LivroNaoDisponivelException {  
        
    /* Métdoo responsável por diminuir a quantidade de livros existentes dento do sistema,
    como funciona: Em primeiro momento criamos uma condiçaõ para saber se realmente existem livros disponíveis para emprestimo,
    se houver, então ele diminui a quantidade de livros disponíves no sistema, para complementar e saber se deu certo a execução 
    do método, criei um boolean chamado "emprestado", que no caso quando executar a diminuição da quantidade referente ao livro, retornará true para o boolean
    assim gerando um tipo de confirmação referente a execução do metodo*/
        
        boolean emprestado = false;
        if (this.quantidadeDisponivel>0){
            this.quantidadeDisponivel--;
            emprestado = true;
        }else {
            throw new LivroNaoDisponivelException("O livro: "+ this.titulo + " não esta disponível no momento !");
              }

    }

    public void incrementarQuantidade(){
        this.quantidadeDisponivel++;
        exibirMensagem("Quantidade de cópias aumentada com sucesso !");


    }

    public int getLivroId(){
        return livroId;
    };

    public void setLivroId(int livroId){
        this.livroId = livroId;
    };


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
}
