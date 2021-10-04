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
public class SQL_lancar_horas_efetuadas {
    conectar conec = new conectar();
    public SQL_lancar_horas_efetuadas(){        
        id_conexao = conec.conectBase();
        //JOptionPane.showMessageDialog(null, "conectado!");
    }
    private Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    

    //---------------------------------------------------
                //BUSCA DE HORAS EFETUADAS PORCONTRIBUINTES
   public ResultSet SQL_horas_efetuadas(int cod){
        retornCons = null;
        int controle = 0;
        id_conexao = conec.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT hm.cod_hora_maquina, hm.n_dam, hm.data_pagamento, hm.quantidade_horas, hm.valor_servico, hm.executado FROM hora_maquina hm, contribuinte ct WHERE hm.cod_contribuinte = ? and ct.cod_contribuinte = hm.cod_contribuinte");
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
   
   //-----------------------confirma execução
   public ResultSet SQL_servico_confirma_execucao(int cod_servico, float quantidade_horas_eftF, int cod_contri){//este método esta ok
        
        id_conexao = conec.conectBase();
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("UPDATE hora_maquina SET executado ='SIM' WHERE cod_hora_maquina = ?");
            pComando.setInt(1, cod_servico);
            pComando.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Documento Autenticado");
            SQL_busca_quant_horas_exe(cod_contri, quantidade_horas_eftF);
            
            //JOptionPane.showMessageDialog(null, quanti_h_ex+"\n"+quantidade_horas_eftF);
            
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro SQL_servico_confirma_execucao\n"+ex.getMessage());
            return null;
        }
   }
   public ResultSet SQL_busca_quant_horas_exe(int cod, float quantidade_horas_eftF){
        retornCons = null;
        //int controle = 0;
        id_conexao = conec.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT horas_executadas FROM contribuinte WHERE cod_contribuinte = ?");
            pComando.setInt(1, cod);
            retornCons = pComando.executeQuery();
            //controle = 1;
            retornCons.next();
            float qt = Float.parseFloat(retornCons.getString("horas_executadas"));
            quantidade_horas_eftF =quantidade_horas_eftF +  qt;
            //JOptionPane.showMessageDialog(null, qt+"\n"+quantidade_horas_eftF);
            SQL_solicita_servico_altera_quantidade_horas_exe(cod, quantidade_horas_eftF);
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro x\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
            
        }
    }
   public ResultSet SQL_solicita_servico_altera_quantidade_horas_exe(int cod, float quantidade_hora){
        
        id_conexao = conec.conectBase();
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("UPDATE contribuinte SET horas_executadas = ? WHERE cod_contribuinte = ?");
            pComando.setFloat(1, quantidade_hora);
            pComando.setInt(2, cod);
            pComando.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Produto "+nome_Prod+" Editado com sucesso!");
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro SQL_solicita_servico_altera_quantidade_horas_exe\n"+ex.getMessage());
            return null;
        }
    }
   
}