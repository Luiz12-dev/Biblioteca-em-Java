package br.com.biblioteca.persistencia;
import br.com.biblioteca.modelo.Membro;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; //Para autoincremento !


public class MembroDAO {
    public Membro criarMembro(ResultSet rs ) throws SQLException{

        Membro membro = new Membro();
        membro.setMembroId(rs.getInt("membroId"));
        membro.setNome(rs.getString("nome"));
        membro.setMatricula(rs.getString("matricula"));
        membro.setEndereco(rs.getString("endereco"));

        return membro;
    }

    public Membro salvar(Membro membro) throws SQLException{
        Connection conexao = null;
        PreparedStatement pstmt= null;
        ResultSet generatedKeys = null;
        try{
            conexao = Conexao.getConexao();
            String insert = "INSERT INTO MEMBROS(NOME, MATRICULA, ENDERECO) VALUES (?,?,?)";

            pstmt=conexao.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,membro.getNome());
            pstmt.setString(2,membro.getMatricula());
            pstmt.setString(3, membro.getEndereco());

            int affectedRows=pstmt.executeUpdate(); // nos diz, quantas linas foram inseridas  // método Update correto para usar o INSERT, UPDATE,DELETE

            if(affectedRows>0){

                generatedKeys = pstmt.getGeneratedKeys();// recupera a -cave -erada

                if(generatedKeys.next()){
                    membro.setMembroId(generatedKeys.getInt(1));
                }

            }
        }catch (SQLException e){
            System.err.println("Erro: Nao foi possível salvar membro: "+ e.getMessage());
            e.printStackTrace();

        } finally {
            DbUtils.close(conexao,pstmt,generatedKeys);
        }
        return membro;
    }


    public Membro buscarPorId (int id)  {
        Connection conexao = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;

        try {
            conexao= Conexao.getConexao();

            String sql = "SELECT NOME, MATRICULA, ENDERECO FROM MEMBRO WHERE MEMBROID = ? ";

            pstmt = conexao.prepareStatement(sql);

            pstmt.setInt(1,id);

            rs= pstmt.executeQuery();

            if (rs.next()){
                return criarMembro(rs);
            }
            return null;

        }catch (SQLException e ){
            System.err.println("Erro: Fala ao buscar membro por ID"+e.getMessage());
            e.printStackTrace();

            return null; // retorna null caso de erro, sempre colocar

        } finally {
            DbUtils.close(conexao,pstmt,rs);
        }


    }

}
