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
public class UsuarioDAO {

    public boolean checkLogin(String Usuario, String Senha) {

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

            stmt = con.prepareStatement("SELECT * FROM TABUsers WHERE Usuario = ? and Senha = ?");
            stmt.setString(1, Usuario);
            stmt.setString(2, Senha);

            rs = stmt.executeQuery();

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
