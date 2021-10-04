package consultas_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import sistema_agricultura.var_parametro;

public class conectar {
    sistema_agricultura.var_parametro prt = new var_parametro();
    private String JDBC_DRIVE = "org.postgresql.Driver";//driver
    private String caminho_base = "jdbc:postgresql://localhost:5432/"+prt.DB_ano;//endereço
    private String usuario = "postgres";
    private String senha = prt.senha_DB;
    public Connection id_Conexao;
    public int Conectado = -1;
    private  Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    
    
    public Connection conectBase(){
        try{
            Class.forName(JDBC_DRIVE);
            id_Conexao = DriverManager.getConnection(caminho_base, usuario, senha);
            Conectado = 0;
            return id_Conexao;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Não foi possível conecatar a Base de Dados\n"+ex.getMessage());
            Conectado = -1; 
            return null;
        }
    }
    public void desconetBase(){
        try{
           id_Conexao.close(); 
           Conectado = -1;
        }catch(Exception ex){
           JOptionPane.showMessageDialog(null, "Não foi possível desconectar a Base de Dados\n"+ex.getMessage()); 
        }
    }
}
