/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class SQL_novo_semestre {
    conectar conec = new conectar();
    public SQL_novo_semestre(){        
        id_conexao = conec.conectBase();
        //JOptionPane.showMessageDialog(null, "conectado!");
    }
    private Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    public ResultSet SQL_novo_semestre(){
        
        id_conexao = conec.conectBase();
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("UPDATE contribuinte SET horas_pagas = 0.0, horas_executadas = 0.0");
            pComando.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Produto "+nome_Prod+" Editado com sucesso!");
            SQL_nova_hora_maquina();
            new impressoes.relatorio_troca_ano().relatorio_ano();            
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Z\n"+ex.getMessage());
            return null;
        }
    }
    
    public ResultSet SQL_nova_hora_maquina(){
        
        id_conexao = conec.conectBase();
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("DELETE FROM hora_maquina");
            //pComando.setInt(1, cod_servico);
            pComando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Serviços Excluidos");
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Z\n"+ex.getMessage());
            return null;
        }
   }
    
}
