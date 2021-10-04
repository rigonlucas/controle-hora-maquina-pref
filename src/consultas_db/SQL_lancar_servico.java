/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class SQL_lancar_servico {
    conectar conec = new conectar();
    public SQL_lancar_servico(){        
        id_conexao = conec.conectBase();
        //JOptionPane.showMessageDialog(null, "conectado!");
    }
    private Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    
    public int SQL_solicita_servico(int cod, float quantidade_hora, float valor_total, int cod_tipo,String type_servico){
        retornCons = null;
        int controle = 0;
        //id_conexao = conecB.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT lanca_servico2(?,?,?,?,?);");
            pComando.setInt(1, cod);
            pComando.setDouble(2, quantidade_hora);
            pComando.setDouble(3, valor_total);
            pComando.setInt(4, cod_tipo);
            pComando.setString(5, type_servico);
            retornCons = pComando.executeQuery();
            controle = 1;
            return 1;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro x\n"+ex.getMessage());
            return 0;
        }finally{
            conec.desconetBase();
            float quantidade_horas_pagas = 0;
            if(controle == 1){
                //retornCons = null;
                retornCons = SQL_pesquisa_horas_pagas(cod);
                try {
                    retornCons.next();
                    quantidade_horas_pagas = Float.parseFloat(retornCons.getString("horas_pagas"));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro x2\n"+ex.getMessage());
                    Logger.getLogger(SQL_lancar_servico.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(quantidade_horas_pagas > -1){
                    quantidade_horas_pagas += quantidade_hora;
                    //JOptionPane.showMessageDialog(null, quantidade_horas_pagas);
                    SQL_solicita_servico_altera_quantidade_horas_pagas(cod, quantidade_horas_pagas);
                }
            }
        }
    }
    public ResultSet SQL_pesquisa_horas_pagas(int cod){
        retornCons = null;
        id_conexao = conec.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT horas_pagas FROM contribuinte WHERE cod_contribuinte = ?");
            pComando.setInt(1,cod);
            retornCons = pComando.executeQuery();
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro y\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
        }
    }
    public ResultSet SQL_solicita_servico_altera_quantidade_horas_pagas(int cod, float quantidade_hora){
        
        id_conexao = conec.conectBase();
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("UPDATE contribuinte SET horas_pagas = ? WHERE cod_contribuinte = ?");
            pComando.setFloat(1, quantidade_hora);
            pComando.setInt(2, cod);
            pComando.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Produto "+nome_Prod+" Editado com sucesso!");
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Z\n"+ex.getMessage());
            return null;
        }
    }
    
    //---------------------------------------------------
                //BUSCA DE HORAS EFETUADAS PORCONTRIBUINTES
   public ResultSet SQL_horas_solicitadas_por_contri(int cod){
        retornCons = null;
        int controle = 0;
        id_conexao = conec.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT cod_hora_maquina, n_dam, data_pagamento, quantidade_horas, valor_servico, pago FROM hora_maquina WHERE cod_contribuinte = ?");
            pComando.setInt(1, cod);
         
            retornCons = pComando.executeQuery();
            controle = 1;
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro x\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
            
        }
    }
   
   public ResultSet SQL_servico_confirma_pagamento(int cod_servico, String n_dam, String data_pg){
        
        id_conexao = conec.conectBase();
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("UPDATE hora_maquina SET n_dam = ?, data_pagamento = ? , pago ='SIM', executado = 'NÃO' WHERE cod_hora_maquina = ?");
            pComando.setString(1, n_dam);
            pComando.setString(2, data_pg);
            pComando.setInt(3, cod_servico);
            pComando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Documento Autenticado");
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Z\n"+ex.getMessage());
            return null;
        }
   }
   
   //--------------------------
   //excluir hora máquina
   public ResultSet SQL_servico_excluir(int cod_servico){
        
        id_conexao = conec.conectBase();
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("DELETE FROM hora_maquina WHERE cod_hora_maquina = ?");
            pComando.setInt(1, cod_servico);
            pComando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Serviço Excluido");
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Z\n"+ex.getMessage());
            return null;
        }
   }
}
