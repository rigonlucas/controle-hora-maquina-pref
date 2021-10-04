/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_sistema;

import consultas_db.SQL_contribuinte;
import consultas_db.SQL_relatorios;
import consultas_db.conectar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class relatorios_hora {
    private JDialog JDhoras_n_pg;
    
    //labels
    private JLabel JLB;
       
    //butons
    private JButton JBTconfirmar;
    private JButton JBTcancelar;
    
    //Jtable
    private JTable tabela;
    private JScrollPane Scroll;
    private DefaultTableModel dtm;
    
    private ResultSet retorno_consulta;
    
    private JLabel background;
    private ImageIcon imTitle;
     //método pesquisa contribuintes
    public void interface_horas_n_pg(){
        JDhoras_n_pg = new JDialog();
        JDhoras_n_pg.setBounds(200, 200, 450, 500);
        JDhoras_n_pg.setLocationRelativeTo(null);
        JDhoras_n_pg.setResizable(true);
        JDhoras_n_pg.setModal(true);
        JDhoras_n_pg.setLayout(null);
        JDhoras_n_pg.setTitle("Relatório de Horas Não Pagas");
        imTitle = new ImageIcon("imagens/background/BG_Sistema_01.png");
        background = new JLabel();
        background.setIcon(imTitle);
        background.setBounds(0, 0, 450, 500);
        JDhoras_n_pg.add(background);
        //label
            JLB = new JLabel("Contribuintes com horas pendentes");
            JLB.setBounds(10, 10, 350, 25);
            background.add(JLB);
        //tabela
            tabela = new JTable();  
                        tabela.setModel(new DefaultTableModel(  
                            new Object[][]{},  
                            //TITULOS DAS SUAS COLUNAS  
                            new String []{"Código","Nome"})  
                            {  
                                public boolean isCellEditable(int r, int c)  
                                {  
                                    return false;  
                                }  
                            } );  
                        tabela.getTableHeader().setReorderingAllowed(false);  
                        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);  
                        tabela.setSelectionMode(0);  
                        
                        dtm = (DefaultTableModel) tabela.getModel();
                        
                        tabela.getColumnModel().getColumn(0).setPreferredWidth(50); 
                        tabela.getColumnModel().getColumn(1).setPreferredWidth(345); 
                        Scroll = new JScrollPane();  
                        Scroll.setViewportView(tabela);  
                        Scroll.setBounds(20,50,400,340);  
                        background.add(Scroll);  
            
        //butons
           JBTconfirmar = new JButton("Imprimir");
           JBTconfirmar.setBounds(60, 400, 150, 25);
           background.add(JBTconfirmar);
           
           JBTcancelar = new JButton("Sair");
           JBTcancelar.setBounds(230, 400, 150, 25);
           background.add(JBTcancelar);
           
            consultas_db.SQL_contribuinte contribuinte = new SQL_contribuinte();//cria objeto na classe consultas e já conecta
            consultas_db.SQL_relatorios rlt = new SQL_relatorios();
            consultas_db.conectar conc = new conectar();
            retorno_consulta = new consultas_db.SQL_relatorios().SQL_pesquisa_horas_n_pg();
            
            try{
                dtm.setNumRows(0);
                    while(retorno_consulta.next()){
                        dtm.addRow(new String []{
                            retorno_consulta.getString("cod_contribuinte"), 
                            retorno_consulta.getString("nome")
                        });
                    }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro\n"+ex.getMessage());
            }finally{
                if(conc.Conectado == 0){
                    conc.desconetBase();                
                }
            }
           
           JBTconfirmar.addActionListener(new evt_horas_n_pg());
           JBTcancelar.addActionListener(new evt_horas_n_pg());
        JDhoras_n_pg.setVisible(true);
    }
    //------------------
    
    public void interface_horas_n_exe(){
        JDhoras_n_pg = new JDialog();
        JDhoras_n_pg.setBounds(200, 200, 450, 500);
        JDhoras_n_pg.setLocationRelativeTo(null);
        JDhoras_n_pg.setResizable(true);
        JDhoras_n_pg.setModal(true);
        JDhoras_n_pg.setLayout(null);
        JDhoras_n_pg.setTitle("Relatório de Horas Não Efetuadas");
        
        //label
            JLB = new JLabel("Contribuintes com horas pendentes");
            JLB.setBounds(10, 10, 350, 25);
            JDhoras_n_pg.add(JLB);
        //tabela
            tabela = new JTable();  
                        tabela.setModel(new DefaultTableModel(  
                            new Object[][]{},  
                            //TITULOS DAS SUAS COLUNAS  
                            new String []{"Código","Nome"})  
                            {  
                                public boolean isCellEditable(int r, int c)  
                                {  
                                    return false;  
                                }  
                            } );  
                        tabela.getTableHeader().setReorderingAllowed(false);  
                        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);  
                        tabela.setSelectionMode(0);  
                        
                        dtm = (DefaultTableModel) tabela.getModel();
                        
                        tabela.getColumnModel().getColumn(0).setPreferredWidth(50); 
                        tabela.getColumnModel().getColumn(1).setPreferredWidth(345); 
                        Scroll = new JScrollPane();  
                        Scroll.setViewportView(tabela);  
                        Scroll.setBounds(20,50,400,340);  
                        background.add(Scroll);  
            
        //butons
           JBTconfirmar = new JButton("Imprimir");
           JBTconfirmar.setBounds(60, 400, 150, 25);
           background.add(JBTconfirmar);
           
           JBTcancelar = new JButton("Sair");
           JBTcancelar.setBounds(230, 400, 150, 25);
           background.add(JBTcancelar);
           
            consultas_db.SQL_contribuinte contribuinte = new SQL_contribuinte();//cria objeto na classe consultas e já conecta
            consultas_db.SQL_relatorios rlt = new SQL_relatorios();
            consultas_db.conectar conc = new conectar();
            retorno_consulta = new consultas_db.SQL_relatorios().SQL_pesquisa_horas_n_exe();
            
            try{
                dtm.setNumRows(0);
                    while(retorno_consulta.next()){
                        dtm.addRow(new String []{
                            retorno_consulta.getString("cod_contribuinte"), 
                            retorno_consulta.getString("nome")
                        });
                    }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro\n"+ex.getMessage());
            }finally{
                if(conc.Conectado == 0){
                    conc.desconetBase();                
                }
            }
           
           JBTconfirmar.addActionListener(new evt_horas_n_pg());
           JBTcancelar.addActionListener(new evt_horas_n_pg());
        JDhoras_n_pg.setVisible(true);
    }
    
    
    private class evt_horas_n_pg implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                JOptionPane.showMessageDialog(null, "Imprimir");
                JDhoras_n_pg.dispose();
            }
            if(ob.equals(JBTcancelar)){
                JDhoras_n_pg.dispose();
            }
        }
        
    }
}
