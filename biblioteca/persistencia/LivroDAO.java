package br.com.biblioteca.persistencia;

import br.com.biblioteca.modelo.Livro;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

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
        livro.setQuantidadeDisponivel(rs.getInt("QuantidadeDisponivel"));
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

}


