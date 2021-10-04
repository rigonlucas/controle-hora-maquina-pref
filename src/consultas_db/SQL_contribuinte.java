package consultas_db;

import java.sql.*;
import javax.swing.JOptionPane;

public class SQL_contribuinte {
    conectar conec = new conectar();
    public SQL_contribuinte(){        
        id_conexao = conec.conectBase();
        //JOptionPane.showMessageDialog(null, "conectado!");
    }
    private Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    
    public ResultSet SQL_pesquisa_contribuinte(String nome){
        retornCons = null;
        //id_conexao = conecB.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT cod_contribuinte, nome FROM contribuinte WHERE nome LIKE ? ORDER BY nome");
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
    
    
    public ResultSet SQL_pesquisa_contribuinte_completo(int cod){
        retornCons = null;
        //id_conexao = conecB.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT * FROM contribuinte WHERE cod_contribuinte = ?");
            pComando.setInt(1,cod);
            retornCons = pComando.executeQuery();
//          JOptionPane.showMessageDialog(null, "consulta ok: exemplo: "+retornCons.getString("nome"));
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
        }
    }
    
    //cadastrar contribuinte
      
    public int SQL_insere_contribuinte_completo(String nome, String apelido, String cpf,String rg, String n_bloco_produtor, String telefone, String endereco){
                //conec.id_Conexao = conec.conectBase();
                try{
                    //comando = conecB.id_Conexao.createStatement();                    
                    
                    pComando = id_conexao.prepareStatement("INSERT INTO contribuinte (nome, apelido, cpf, rg, n_bloco_produtor, telefone, endereco, horas_pagas, horas_executadas) VALUES (?,?,?,?,?,?,?,0.0,0.0)");
                    pComando.setString(1,nome.toUpperCase());
                    pComando.setString(2,apelido.toUpperCase());
                    pComando.setString(3,cpf);
                    pComando.setString(4,rg);
                    pComando.setString(5,n_bloco_produtor);
                    pComando.setString(6,telefone);
                    pComando.setString(7,endereco.toUpperCase());
                    
                    
                    pComando.execute();
                    JOptionPane.showMessageDialog(null, "Cadastro Efetuado Com Sucesso!");
                    return 1;
                }catch(SQLException sqlEx){
                    JOptionPane.showMessageDialog(null, "ERRO AO INSERIR DADOS\n"+sqlEx.getMessage());
                    System.out.println(sqlEx);
                    return 0;
                }finally{
                    conec.desconetBase();
                }
        }
    
    //atualizar cadastro
    public ResultSet SQL_altera_cad(String nome, String apelido, String cpf, String rg, String n_bloco, String telefone, String endereco, int cod_contr){
        id_conexao = conec.conectBase();
        
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("UPDATE contribuinte SET nome = ?, apelido = ?, cpf = ?, rg = ?, n_bloco_produtor = ?, telefone = ?, endereco = ? WHERE cod_contribuinte = ?");
            pComando.setString(1, nome);
            pComando.setString(2, apelido);
            pComando.setString(3, cpf);
            pComando.setString(4, rg);
            pComando.setString(5, n_bloco);
            pComando.setString(6, telefone);
            pComando.setString(7, endereco);
            pComando.setInt(8, cod_contr);
            pComando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Edição Efetuado Com sucesso!");
            //JOptionPane.showMessageDialog(null, "Produto "+nome_Prod+" Editado com sucesso!");
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Z\n"+ex.getMessage());
            return null;
        }
    }
    public ResultSet SQL_altera_horas(int cod_contr, float horas_pg, float horas_exec){
        id_conexao = conec.conectBase();
        
        try {
            pComando = (PreparedStatement) id_conexao.prepareStatement("UPDATE contribuinte SET horas_pagas = ?, horas_executadas = ? WHERE cod_contribuinte = ?");
            pComando.setFloat(1, horas_pg);
            pComando.setFloat(2, horas_exec);
            pComando.setInt(3, cod_contr);
            pComando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Edição Efetuado Com sucesso!");
            //JOptionPane.showMessageDialog(null, "Produto "+nome_Prod+" Editado com sucesso!");
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Z\n"+ex.getMessage());
            return null;
        }
    }
    
}
