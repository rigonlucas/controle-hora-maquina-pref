package consultas_db;

import java.sql.*;
import javax.swing.JOptionPane;

public class SQL_relatorios {
    conectar conec = new conectar();
    public SQL_relatorios(){        
        id_conexao = conec.conectBase();
        //JOptionPane.showMessageDialog(null, "conectado!");
    }
    private Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    
    public ResultSet SQL_pesquisa_horas_n_pg(){
        retornCons = null;
        //id_conexao = conecB.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT ct.cod_contribuinte, ct.nome FROM contribuinte ct, hora_maquina hm WHERE pago LIKE 'NÃO' and ct.cod_contribuinte = hm.cod_contribuinte ORDER BY nome");
            retornCons = pComando.executeQuery();
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
        }
    }
    public ResultSet SQL_pesquisa_horas_n_exe(){
        retornCons = null;
        //id_conexao = conecB.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT ct.cod_contribuinte, ct.nome,hm.tipo_servico FROM contribuinte ct, hora_maquina hm WHERE ct.horas_pagas > ct.horas_executadas and hm.pago LIKE 'SIM' and ct.cod_contribuinte = hm.cod_contribuinte  ORDER BY nome");
            retornCons = pComando.executeQuery();
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
        }
    }
    
    
    //--------------------relátórios para JTFields
    public ResultSet SQL_pesquisa_contribuinte_n_exec(String nome){
        retornCons = null;
        //id_conexao = conecB.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT cont.cod_contribuinte, cont.nome FROM contribuinte cont, hora_maquina hm WHERE cont.nome = ? and hm.cod_contribuinte = cont.cod_contribuinte and pago = 'SIM' and executado = 'SIM' ORDER BY cont.nome");
            pComando.setString(1, "%"+nome+"%");
            retornCons = pComando.executeQuery();
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
        }
    }
    
}
