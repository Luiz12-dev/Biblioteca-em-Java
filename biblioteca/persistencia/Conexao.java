package br.com.biblioteca.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection conexao;
    private static final String URL = "jdbc:mysql://localhost:3306/biblios";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345678";

    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
        return conexao;
    }

    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conexao = getConexao();
        if (conexao != null) {
            System.out.println("Conexão estabelecida com sucesso.");
            fecharConexao();
        }
    }
}
