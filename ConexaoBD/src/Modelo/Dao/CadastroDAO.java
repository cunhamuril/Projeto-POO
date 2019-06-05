/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.me.concexaobd.ConexaoBD;

/**
 *
 * @author Marcio Damazio
 */
public class CadastroDAO {
     public boolean checkLogin(String NOME_COMPLETO, String Usuario, String ID_DE_FUNCIONARIO, String Senha, String CONFIRMAR_SENHA)  {
        
        Connection con = null;
            try {
                con = ConexaoBD.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        PreparedStatement stmt = null;
        ResultSet rs = null;
        

        boolean check = false;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO EMPRESA.TABUsers "+"(NOME_COMPLETO , USUARIO, ID_DE_FUNCIONARIO, SENHA, CONFIRMAR_SENHA) " + "VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1,NOME_COMPLETO);
            stmt.setString(2, Usuario);
            stmt.setString(3, ID_DE_FUNCIONARIO);
            stmt.setString(4,Senha);
            stmt.setString(5, CONFIRMAR_SENHA);
            stmt.executeUpdate();
           
            
            if (rs.next()) {

              
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }

        return check;
        
    }
}


