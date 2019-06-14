package Modelo.Dao;

import Modelo.bean.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.me.concexaobd.ConexaoBD;

/**
 *
 * @author muril
 */
public class ProdutoDAO {

    public boolean check = false;
    public String msg = "";

    public String Inserir(String nome, String qtd, String fab, String lote, String obs) {

        Connection con = null;
        try {
            con = ConexaoBD.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement("INSERT INTO tabproduto (nome, quantidade, data_fab, lote, obs)\n"
                    + "VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, qtd);
            stmt.setString(3, fab);
            stmt.setString(4, lote);
            stmt.setString(5, obs);

            stmt.executeUpdate();
            this.msg = "Produto cadastrado com sucesso!";
            this.check = true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            this.msg = "Erro no Banco de Dados!";
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }

        return msg;
    }

    public String Alterar(String id, String nome, String qtd, String fab, String lote, String obs) {

        Connection con = null;
        try {
            con = ConexaoBD.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("UPDATE tabproduto SET nome = ?, quantidade = ?, "
                    + "data_fab = ?, lote = ?, obs = ? WHERE id_produto = ?"
                    + "");
            stmt.setString(1, nome);
            stmt.setString(2, qtd);
            stmt.setString(3, fab);
            stmt.setString(4, lote);
            stmt.setString(5, obs);
            stmt.setString(6, id);

            stmt.executeUpdate();
            this.msg = "Produto alterado com sucesso!";
            this.check = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }

        return msg;
    }

    public String Excluir(String id) {
        Connection con = null;
        try {
            con = ConexaoBD.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tabproduto WHERE id_produto = ?");
            stmt.setString(1, id);

            stmt.executeUpdate();
            this.msg = "Produto excluído com sucesso!";
            this.check = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }

        return msg;
    }

    public List<Produtos> getProduto(String id) {
        List<Produtos> resultados = new ArrayList<>();

        Connection con = null;
        try {
            con = ConexaoBD.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {

            stmt = con.prepareStatement("select * from tabproduto where id_produto = ?");
            stmt.setString(1, id);

            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                resultados.add(new Produtos(
                        resultSet.getInt("ID_PRODUTO"),
                        resultSet.getString("NOME"),
                        resultSet.getString("QUANTIDADE"),
                        resultSet.getString("DATA_FAB"),
                        resultSet.getString("LOTE"),
                        resultSet.getString("OBS")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(con, stmt, resultSet);
        }

        return resultados;
    }

}
