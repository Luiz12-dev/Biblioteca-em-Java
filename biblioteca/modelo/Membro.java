package br.com.biblioteca.modelo;

public class Membro {
    private String nome;
    private String matricula;
    private String endereco;
    private int membroId;

    public Membro(String nome, String matricula, String endereco, int membroId){
        this.nome = nome;
        this.matricula= matricula;
        this.endereco = endereco;
        this.membroId= membroId;
    }

    public int getMembroId(){
      return membroId;
    }

    public void setMembroId(int membroId){
        this.membroId= membroId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
