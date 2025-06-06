package br.com.biblioteca.modelo;

import br.com.biblioteca.excecao.LivroNaoDisponivelException;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private int anoPublic;
    private int quantidadeDisponivel=0;
    private int livroId;
    private String genero;


    public Livro (){
        this.quantidadeDisponivel=0;
    }



    public Livro(String titulo, String autor, String isbn, int anoPublicacao, int quantidadeDisponivel, int livroId, String genero){
        this.titulo = titulo;
        this.autor= autor;
        this.isbn=isbn;
        this.anoPublic =anoPublicacao;
        this.quantidadeDisponivel= 0;
        this.livroId=livroId;
        this.genero= genero;
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);

    }

    public void decrementarQuantidade() throws LivroNaoDisponivelException {
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

    @Override
    public String toString() {
        return "Livro{" +
                "livroId=" + livroId +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoPublic=" + anoPublic +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", genero=" + genero + // Exibir nome do gênero
                '}';
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

    public int getAnoPublic() {
        return anoPublic;
    }

    public void setAnoPublic(int anoPublic) {
        this.anoPublic = anoPublic;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        genero = genero;
    }
}
