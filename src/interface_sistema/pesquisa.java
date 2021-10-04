/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_sistema;

import consultas_db.SQL_contribuinte;
import consultas_db.conectar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class pesquisa {
    private JDialog JDialog_pesquisa;
    
    //labels
    private JLabel JLnome;
    
    //fields
    private JTextField JTFnome;
    
    //butons
    private JButton JBTconfirmar;
    private JButton JBTcancelar;
    
    //Jtable
    private JTable tabela;
    private JScrollPane Scroll;
    private DefaultTableModel dtm;
    
    private JLabel background;
    private ImageIcon imTitle;
     //método pesquisa contribuintes
    public void interface_pesquisa_contribuinte(){
        JDialog_pesquisa = new JDialog();
        JDialog_pesquisa.setBounds(200, 200, 450, 500);
        JDialog_pesquisa.setLocationRelativeTo(null);
        JDialog_pesquisa.setResizable(true);
        JDialog_pesquisa.setModal(true);
        JDialog_pesquisa.setLayout(null);
        JDialog_pesquisa.setTitle("Pesquisa de Dados Cadastrais");
        
        imTitle = new ImageIcon("imagens/background/BG_Sistema_01.png");
        background = new JLabel();
        background.setIcon(imTitle);
        background.setBounds(0, 0, 450, 500);
        JDialog_pesquisa.add(background);
        //label
            JLnome = new JLabel("Nome:");
            JLnome.setBounds(10, 10, 45, 25);
            background.add(JLnome);
        //text field
            JTFnome = new JTextField();
            JTFnome.setBounds(65, 10, 350, 25);
            background.add(JTFnome);
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
           JBTconfirmar = new JButton("Confirmar");
           JBTconfirmar.setBounds(60, 400, 150, 25);
           background.add(JBTconfirmar);
           
           JBTcancelar = new JButton("Cancelar");
           JBTcancelar.setBounds(230, 400, 150, 25);
           background.add(JBTcancelar);
           
           JBTconfirmar.addActionListener(new evt_pesquisa_contribuinte());
           JBTcancelar.addActionListener(new evt_pesquisa());
           JTFnome.addKeyListener(new evt_key_listner_pesquisa_cliente());
           
        JDialog_pesquisa.setVisible(true);
    }
    
    
    
    //busca de funcionários
    public void interface_pesquisa_tipo_horas(){
        JDialog_pesquisa = new JDialog();
        JDialog_pesquisa.setBounds(200, 200, 450, 500);
        JDialog_pesquisa.setLocationRelativeTo(null);
        JDialog_pesquisa.setResizable(true);
        JDialog_pesquisa.setModal(true);
        JDialog_pesquisa.setLayout(null);
        JDialog_pesquisa.setTitle("Pesquisa de Dados Cadastrais");
        imTitle = new ImageIcon("imagens/background/BG_Sistema_01.png");
        background = new JLabel();
        background.setIcon(imTitle);
        background.setBounds(0, 0, 450, 500);
        JDialog_pesquisa.add(background);
        //label
            JLnome = new JLabel("Funcionário:");
            JLnome.setBounds(10, 10, 70, 25);
            background.add(JLnome);
        //text field
            JTFnome = new JTextField();
            JTFnome.setBounds(90, 10, 325, 25);
            background.add(JTFnome);
        //tabela
            tabela = new JTable();  
                        tabela.setModel(new DefaultTableModel(  
                            new Object[][]{},  
                            //TITULOS DAS SUAS COLUNAS  
                            new String []{"Código","Nome do Funcionário"})  
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
           JBTconfirmar = new JButton("Confirmar");
           JBTconfirmar.setBounds(60, 400, 150, 25);
           background.add(JBTconfirmar);
           
           JBTcancelar = new JButton("Cancelar");
           JBTcancelar.setBounds(230, 400, 150, 25);
           background.add(JBTcancelar);
           
           JBTconfirmar.addActionListener(new evt_pesquisa_tipo_hora());
           JBTcancelar.addActionListener(new evt_pesquisa());
           
        JDialog_pesquisa.setVisible(true);
    }
    
    private ResultSet retorno_consulta;
    
    //classe genérica
    private class evt_pesquisa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTcancelar)){
                get_cod_contri = 0;
                new interface_sistema.servico_hora_efetuada().cod_contri = 0;
                
                JDialog_pesquisa.dispose();
            }
        }        
    }
    //eventos para contribuinte
    public int get_cod_contri;
    private class evt_pesquisa_contribuinte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                int row;
                row = tabela.getSelectedRow();
                if(row>-1){
                    get_cod_contri = Integer.parseInt(tabela.getValueAt(row, 0).toString());
                }
                //retorno_consulta = new consultas_db.SQL_contribuinte().SQL_pesquisa_contribuinte_completo(cod);
                //JOptionPane.showMessageDialog(null, get_cod_contri);

                if(get_cod_contri < 1){
                    JOptionPane.showMessageDialog(null, "Selecione Algum Contribuinte!");
                }
                JDialog_pesquisa.dispose();
                        

            }
        }
    }    
    
   
    //classe de eventos do tipo hora
    private class evt_pesquisa_tipo_hora implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                JDialog_pesquisa.dispose();
            }
        }        
    }
    //CLASSE DE BUSCA DE CONTRIBUINTE
    private class evt_key_listner_pesquisa_cliente implements KeyListener{

        consultas_db.conectar conectar_DB = new conectar();
        public void keyTyped(KeyEvent e) { }
        
        public void keyPressed(KeyEvent e) {  
             int tecla  = e.getKeyCode();
             if(tecla == 27){
                 JDialog_pesquisa.dispose();
             }
        }

        public void keyReleased(KeyEvent e) {
            consultas_db.SQL_contribuinte contribuinte = new SQL_contribuinte();//cria objeto na classe consultas e já conecta
            retorno_consulta = contribuinte.SQL_pesquisa_contribuinte(JTFnome.getText().trim().toUpperCase());
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
                if(conectar_DB.Conectado == 0){
                    conectar_DB.desconetBase();
                }
            }
            
        }
    }
}
