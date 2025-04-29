package br.com.biblioteca.modelo;

import java.time.LocalDate;

    public class Emprestimo {
        private int emprestimoId;
        private Livro livro;
        private Membro membro;
        private LocalDate dataEmprestimo;
        private LocalDate dataDevolucaoEsperada;
        private LocalDate dataDevolucaoReal;
        private StatusEmprestimo status;


        public enum StatusEmprestimo{
            ATIVO,
            DEVOLVIDO,
            ATRASADO
        }

    public Emprestimo (Livro livro, Membro membro, LocalDate dataEmprestimo, LocalDate dataDevolucaoEsperada, LocalDate dataDevolucaoReal) {
        this.livro = livro;
        this.membro= membro;
        this.dataEmprestimo= dataEmprestimo;
        this.dataDevolucaoEsperada= dataDevolucaoEsperada;
        this.status= StatusEmprestimo.ATIVO;
        this.dataDevolucaoReal=null;

    }

        public Livro getLivro() {
            return livro;
        }

        public void setLivro(Livro livro) {
            this.livro = livro;
        }

        public Membro getMembro() {
            return membro;
        }

        public void setMembro(Membro membro) {
            this.membro = membro;
        }

        public LocalDate getDataEmprestimo() {
            return dataEmprestimo;
        }

        public void setDataEmprestimo(LocalDate dataEmprestimo) {
            this.dataEmprestimo = dataEmprestimo;
        }

        public LocalDate getDataDevolucaoEsperada() {
            return dataDevolucaoEsperada;
        }

        public void setDataDevolucaoEsperada(LocalDate dataDevolucaoEsperada) {
            this.dataDevolucaoEsperada = dataDevolucaoEsperada;
        }

        public int getEmprestimoId() {
            return emprestimoId;
        }

        public void setEmprestimoId(int emprestimoId) {
            this.emprestimoId = emprestimoId;
        }

        public LocalDate getDataDevolucaoReal() {
            return dataDevolucaoReal;
        }

        public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
            this.dataDevolucaoReal = dataDevolucaoReal;
        }

        public StatusEmprestimo getStatus() {
            return status;
        }



        public void setStatus(StatusEmprestimo status) {
            this.status= status;
        };


        /*
           método responsponsável por verificar se a data de entrega está vazia,
           portanto ainda não foi devolvido, logo ele definite a data atual,
           e a data eseperada de entrega, se a data atual for depois da data marcada ele resulta em positivo para o boolen caso não,
           define como falso.
           */

        public boolean estaAtrasado(){

            if(this.dataDevolucaoReal == null && LocalDate.now().isAfter(this.dataDevolucaoEsperada)){
                return true;
            }
            return false;
        }
    }
