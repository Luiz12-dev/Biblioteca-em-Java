package br.com.biblioteca.persistencia;

import br.com.biblioteca.modelo.Livro;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class LivroDAO {

    public Livro salvar (Livro livro){
        //Public = a classe pode ser acessada em por qualquer classe do projeto;

        // Crição conexão banco de dados, Crialção da String que vai pro banco de dados, Crialção chave que mostra se a conexão do banco e a realização da String deu certo

        Connection conexao = null;

        PreparedStatement pstmt = null;

        ResultSet generatedKeys = null;

        try{
            conexao = Conexao.getConexao();

            String sql = "INSERT INTO LIVROS(TITULO, AUTOR, ISBN, ANOPUBLIC, QUANTIDADEDISPONIVEL , GENERO) VALUES(?,?,?,?,?,?)";

            pstmt = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, livro.getTitulo());

            pstmt.setString(2, livro.getAutor());

            pstmt.setString(3, livro.getIsbn());

            pstmt.setInt(4, livro.getAnoPublic());

            pstmt.setInt(5,livro.getQuantidadeDisponivel());
            pstmt.setString(6, livro.getGenero());



            int affectedRows = pstmt.executeUpdate();

            if(affectedRows > 0){

                generatedKeys = pstmt.getGeneratedKeys();

                if(generatedKeys.next()){

                    livro.setLivroId(generatedKeys.getInt(1));
                }

            }

            return livro;

        }catch (SQLException e){

            System.out.println("Erro ao salvar livro: "+ e.getMessage());

            return null;

        }finally {

            DbUtils.close(conexao,pstmt,generatedKeys);

        }
    }



    private Livro criarLivro(ResultSet rs) throws SQLException {

        Livro livro = new Livro();
        livro.setLivroId(rs.getInt("LIVROID"));
        livro.setTitulo(rs.getString("TITULO"));
        livro.setAutor(rs.getString("AUTOR"));
        livro.setIsbn(rs.getString("ISBN"));
        livro.setAnoPublic(rs.getInt("ANOPUBLIC"));
        livro.setQuantidadeDisponivel(rs.getInt("QUANTIDADEDISPONIVEL"));
        livro.setGenero(rs.getString("GENERO"));

        return livro;
    }

// Este método busca um livro no banco de dados usando seu ID.
// Primeiramente, estabelece a conexão com o banco de dados através da variável 'conexao' (do tipo Connection).
// Em seguida, prepara a instrução SQL de consulta (SELECT) com um PreparedStatement,
// que é o comando responsável por obter os dados do livro pelo seu ID.
// Se o livro for encontrado no banco de dados, o ResultSet ('rs') trará essas informações.
// Nesse caso, o método auxiliar 'criarLivro(rs)' é acionado para criar um novo objeto 'Livro'
// e preenchê-lo com os dados lidos do ResultSet, transformando-os em um objeto Java.


    public Livro buscarPorId(int id){

        Connection conexao =  null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try{

            // Código responsável por trazer as informaçoes referentes a partir do id de um livro existente no banco de dados

            conexao = Conexao.getConexao();

            String sqlBuscarID = "SELECT LIVROID, TITULO, AUTOR, ISBN, ANOPUBLIC, QUANTIDADEDISPONIVEL, GENERO FROM LIVROS WHERE LIVROID = ?";

            pstmt = conexao.prepareStatement(sqlBuscarID);

            pstmt.setInt(1,id);

            rs = pstmt.executeQuery();



            if(rs.next()){
                return criarLivro(rs);
            }
            return null;

        }catch (SQLException e){

            System.err.println("Erro ao buscar livro por ID:  " + e.getMessage());
            e.printStackTrace();

            return null;

        }finally {

            DbUtils.close(conexao, pstmt, rs);
        }
    }

  public List<Livro> listarTodos() throws SQLException {

        //preparamos a conexão

        /* Como que funciona esse código
        * basicamente em primeiro momento
        * criamos uma variável para Connection,
        * PreparedStatement e ResultSet.
        * Depois disso, definimos ela com null
        * já que ainda não queremos inicar ela.
        * Criamos também uma lista para armazenar todos os livros
        * que serão encotrados dentro do banco de dados, para que, quando
        * encontra-los os armazenar dentro dessa lista para depois, retornar a própria lista
        * depois.
        *
        * com isso, abrimos um try-cath já que
        * vamos ter conexão e exeções dentro do código
        * primeiro criamos a conexão, usando a variável
        * que recebeu o Connection que no caso é o
        * conexão, em seguida colocamos que ela é igual a
        * Conexao, que no caso é a nossa classe que esta lincada
        * com o banco de dados, com isso conseguimos trazer o método
        * get.Conexao que irá fazer a conexão com o banco de dados.
        *
        * a partir disso criamos o pstmt, que no caso é responsável por ser o
        * formulário que queremos enviar para o banco dedos.
        * como nesse caso queremos mostrar todos os livros, usamos o SELECT e os atributo dos livros
        * depois de deixar pronto esse formulário, preparamos o result set
        * que é responsável por trazer a resposta do banco de dados para o nosso código
        * depois de ele ser executado, preenchendo os dados que queremos.
        *
        * então, usamos um while, para que enqanto o rs consiga ler o pedido do formulário
        * será criado um objeto do tipo Livro, que será armazendo então dentro da lista chamada livros.
        * com isso, irá retornar a lista chamada livros.
        *
        *
        *
        * */

        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Livro>livros = new ArrayList<>(); 
        try {

            conexao = Conexao.getConexao();
            String sqlListTodos = "SELECT LIVROID, TITULO, AUTOR, ISBN, ANOPUBLIC, QUANTIDADEDISPONIVEL, GENERO FROM LIVROS";
            pstmt= conexao.prepareStatement(sqlListTodos);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Livro livro = criarLivro(rs);
                livros.add(livro);
            }
            return livros;

        }catch (SQLException e){
            System.err.println("Erro: não foi possível listar todos livros " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }finally {
            DbUtils.close(conexao,pstmt,rs);
        }

    }


    public boolean atualizarLivro(Livro livro) throws SQLException{
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try{
            conexao= Conexao.getConexao();
            String sql = "UPDATE LIVROS SET TITULO= ?, AUTOR=?, ISBN=?, ANOPUBLIC=?, QUANTIDADEDISPONIVEL=?, GENERO=? WHERE LIVROID = ? ";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1,livro.getTitulo());
            pstmt.setString(2,livro.getAutor());
            pstmt.setString(3,livro.getIsbn());
            pstmt.setInt(4,livro.getAnoPublic());
            pstmt.setInt(5,livro.getQuantidadeDisponivel());
            pstmt.setString(6,livro.getGenero());
            pstmt.setInt(7,livro.getLivroId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e){
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }finally {
            DbUtils.close(conexao,pstmt,null);
        }
    }

    public boolean removerLivro(Livro livro)throws SQLException{
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try{
            conexao= Conexao.getConexao();
            String sql = "DELETE FROM LIVROS WHERE LIVROID = ?";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1,livro.getLivroId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e){
            System.err.println("Erro: Falha ao remover livro: "+ e.getMessage());
            e.printStackTrace();
            return false;
        }finally {
            DbUtils.close(conexao,pstmt,null);
        }
    }



}
