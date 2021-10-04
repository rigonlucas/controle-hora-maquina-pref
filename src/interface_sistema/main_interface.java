/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_sistema;


import consultas_db.SQL_contribuinte;
import consultas_db.SQL_relatorios;
import consultas_db.conectar;
import java.awt.Color;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.rmi.CORBA.Tie;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;



public class main_interface {
    private JFrame JFrame_principal;
    
   
    //butons
    
    private JButton JBTimprimir_h_n_pg;
    private JButton JBTimprimir_h_n_exe;
    private JButton JBTatua_pg;
    private JButton JBTatua_exe;
        
    private JLabel JLBbakgroud, JLBbakgroud2, JLBbakgroud3;
    private ImageIcon imTitle;
    private ImageIcon imTitle2;
    
    //Jtable
    private JTable tabela;
    private JScrollPane Scroll;
    private DefaultTableModel dtm;
    
    private JTable tabela2;
    private JScrollPane Scroll2;
    private DefaultTableModel dtm2;
    
    private JMenu menu_hora_maquina;
    private JMenu menu_cad_contribuinte;
    
    private JMenuBar bar;
    
    
    private JMenuItem iten_solicitar_doc;
    private JMenuItem iten_confirmar_Pg;
    private JMenuItem iten_confirmar_exe;
    private JMenuItem iten_buscar_hora;
    private JMenuItem iten_excluir_hora;
    private JMenuItem iten_relatorio;
    private JMenuItem iten_alterar_quant_horas;
    private JMenuItem iten_novo_semestre;
    
    
    private JMenuItem iten_cadastrar_contribuinte;
    private JMenuItem iten_editar_contribuinte;
     //método pesquisa contribuintes
    public main_interface(){
        window_main();
    }
    
    private void window_main(){
        JFrame_principal = new JFrame();
        JFrame_principal.setBounds(200, 200, 1390, 735);
        JFrame_principal.setLocationRelativeTo(null);
        JFrame_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame_principal.setResizable(true);
        JFrame_principal.setLayout(null);
        ImageIcon titulo = new ImageIcon("imagens/icones/title.png");
        JFrame_principal.setIconImage(titulo.getImage());
        JFrame_principal.setTitle("Sistema de Controle SEMAG - BASE DE DADOS: "+new sistema_agricultura.var_parametro().DB_ano);
        
        
        JLBbakgroud = new JLabel();   
        JLBbakgroud.setBounds(0, -50, 1500, 800);
        imTitle = new ImageIcon("imagens/background/BG_Sistema_02.png");
        //imTitle = new ImageIcon("BG_Sistema_02.png");
        JLBbakgroud.setIcon(imTitle);
        JFrame_principal.add(JLBbakgroud);    
        
        JLabel lb = new JLabel("Desenvolvido por Lucas Rigon");
        lb.setForeground(Color.RED);
        lb.setBounds(1180, 60, 250, 25);
        JLBbakgroud.add(lb);
                
        imTitle2 = new ImageIcon("imagens/background/BG_Sistema_03.png");
        
           //meunu bar
                bar = new JMenuBar();                
                
                menu_cad_contribuinte = new JMenu("Cadastro de Contribuinte");
                ImageIcon cad_contribuinte = new ImageIcon("imagens/icones/contribuinte.png");
                menu_cad_contribuinte.setIcon(cad_contribuinte);
                
                menu_hora_maquina = new JMenu("Horas Máquina");
                ImageIcon cad_trator = new ImageIcon("imagens/icones/trator.png");
                menu_hora_maquina.setIcon(cad_trator);
                
                bar.add(menu_cad_contribuinte);
                bar.add(menu_hora_maquina);
                                
                iten_solicitar_doc = new JMenuItem("Requisição de Recolhimento");
                ImageIcon solici_requi = new ImageIcon("imagens/icones/golds.png");
                iten_solicitar_doc.setIcon(solici_requi);
                
                iten_confirmar_Pg = new JMenuItem("Pagamento da Guia");
                ImageIcon pg_ok = new ImageIcon("imagens/icones/ok.png");
                iten_confirmar_Pg.setIcon(pg_ok);
                
                iten_confirmar_exe = new JMenuItem("Execução do Serviço");
                ImageIcon trator_ok = new ImageIcon("imagens/icones/trator_ok.png");
                iten_confirmar_exe.setIcon(trator_ok);
                
                iten_buscar_hora = new JMenuItem("Buscar Horas de Contribuinte");
                ImageIcon busca_hora = new ImageIcon("imagens/icones/btn-buscar.png");
                iten_buscar_hora.setIcon(busca_hora);
                
                iten_excluir_hora = new JMenuItem("Excluir Horas");
                ImageIcon excluir_hora = new ImageIcon("imagens/icones/remover.png");
                iten_excluir_hora.setIcon(excluir_hora);
                
                iten_relatorio = new JMenuItem("Relatório");
                ImageIcon relat_hora = new ImageIcon("imagens/icones/relatorio.png");
                iten_relatorio.setIcon(relat_hora);
                
                iten_alterar_quant_horas = new JMenuItem("Alterar Quantidade de Horas");
                ImageIcon altera_quant = new ImageIcon("imagens/icones/edit.png");
                iten_alterar_quant_horas.setIcon(altera_quant);
                
                iten_novo_semestre = new JMenuItem("Iniciar Outro Semestre");
                ImageIcon set_semestre = new ImageIcon("imagens/icones/atualizar.png");
                iten_novo_semestre.setIcon(set_semestre);
                
                iten_cadastrar_contribuinte = new JMenuItem("Cadastrar Contribuinte");                
                ImageIcon cad_cl = new ImageIcon("imagens/icones/cliente_add.gif");
                iten_cadastrar_contribuinte.setIcon(cad_cl);
                
                iten_editar_contribuinte = new JMenuItem("Editar Contribuinte");   
                ImageIcon cad_cl_edit = new ImageIcon("imagens/icones/editar.png");
                iten_editar_contribuinte.setIcon(cad_cl_edit);
                
                menu_hora_maquina.add(iten_solicitar_doc);
                menu_hora_maquina.add(iten_confirmar_Pg);
                menu_hora_maquina.add(iten_confirmar_exe);
                menu_hora_maquina.add(iten_buscar_hora);
                menu_hora_maquina.add(iten_excluir_hora);
                menu_hora_maquina.add(iten_relatorio);
                menu_hora_maquina.add(iten_alterar_quant_horas);
                menu_hora_maquina.add(iten_novo_semestre);
                
                menu_cad_contribuinte.add(iten_cadastrar_contribuinte);
                menu_cad_contribuinte.add(iten_editar_contribuinte);
                
                JFrame_principal.setJMenuBar(bar);
           
           iten_solicitar_doc.addActionListener(new evt_main_funcoes());
           iten_confirmar_Pg.addActionListener(new evt_main_funcoes());
           iten_confirmar_exe.addActionListener(new evt_main_funcoes());
           iten_buscar_hora.addActionListener(new evt_main_funcoes());
           iten_excluir_hora.addActionListener(new evt_main_funcoes());
           iten_relatorio.addActionListener(new evt_main_funcoes());
           iten_novo_semestre.addActionListener(new evt_main_funcoes());
           iten_alterar_quant_horas.addActionListener(new evt_main_funcoes());
           //JBTatua_pg.addActionListener(new evt_main_funcoes());
           iten_cadastrar_contribuinte.addActionListener(new evt_main_funcoes());
           iten_editar_contribuinte.addActionListener(new evt_main_funcoes());
           
        JFrame_principal.setVisible(true);
    }
    private JPanel PN_horas_n_pg, PN_horas_n_exe;
    private JDialog JDrelat, JD_select_relat;
    private JRadioButton JRB_pg, JRB_exe, JRB_hora_exec;
    private JButton JBTselect_relat, JBTsair;
    
    private void select_relat(){
        JD_select_relat = new JDialog();
        JD_select_relat.setTitle("Relatórios");
        JD_select_relat.setBounds(0, 0, 400, 160);
        JD_select_relat.setLayout(null);
        JD_select_relat.setResizable(true);
        JD_select_relat.setModal(true);
        JD_select_relat.setLocationRelativeTo(null);
        
        JLBbakgroud2 = new JLabel();   
        JLBbakgroud2.setBounds(0, 0, 400, 160);
        JLBbakgroud2.setIcon(imTitle2);
        JD_select_relat.add(JLBbakgroud2);
                
        JRB_exe = new JRadioButton("Não Executadas");
        JRB_exe.setSelected(true);
        JRB_exe.setBounds(20, 20, 130, 25);
        JRB_exe.setOpaque(false);
        JLBbakgroud2.add(JRB_exe);

        JRB_pg = new JRadioButton("Não Pagas");
        JRB_pg.setBounds(150, 20, 100, 25);
        JRB_pg.setOpaque(false);
        JLBbakgroud2.add(JRB_pg);

        JRB_hora_exec = new JRadioButton("Horas Semestre");
        JRB_hora_exec.setBounds(250, 20, 120, 25);
        JRB_hora_exec.setOpaque(false);
        JLBbakgroud2.add(JRB_hora_exec);
        
        JBTselect_relat = new JButton("Selecionar");
        JBTselect_relat.setBounds(100, 50, 190, 25);
        JLBbakgroud2.add(JBTselect_relat);
        
        JBTsair = new JButton("Sair");
        JBTsair.setBounds(100, 90, 190, 25);
        JLBbakgroud2.add(JBTsair);
        //group
        ButtonGroup BtBroup = new ButtonGroup();
        BtBroup.add(JRB_exe);
        BtBroup.add(JRB_pg);
        BtBroup.add(JRB_hora_exec);
        
        
        //.setBackground(new Color(255,255,255,0))
        //JD_select_relat.getRootPane().setOpaque(false);
        //JD_select_relat.setUndecorated(true);
        
        JBTselect_relat.addActionListener(new evt_select_relat());
        JBTsair.addActionListener(new evt_select_relat());
        JD_select_relat.setVisible(true);
    }
    
    private class evt_select_relat implements ActionListener{

        
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTselect_relat)){
                if(JRB_exe.isSelected()){
                    JD_select_relat.dispose();
                    relat("exe");
                }
                if(JRB_pg.isSelected()){
                    relat("pg");
                    JD_select_relat.dispose();
                }
                if(JRB_hora_exec.isSelected()){
                    new impressoes.relatorio_troca_ano().relatorio_ano();
                }
            }
            if(ob.equals(JBTsair)){
                JD_select_relat.dispose();
            }
        }
        
    }
    
    private void relat(String tipo){
        JDrelat = new JDialog();
        JDrelat.setTitle("Relatórios");
        JDrelat.setBounds(0, 0, 420, 300);
        JDrelat.setLayout(null);
        JDrelat.setResizable(true);
        JDrelat.setModal(true);
        JDrelat.setLocationRelativeTo(null);
        
        JLBbakgroud3 = new JLabel();   
        JLBbakgroud3.setBounds(0, 0, 420, 300);
        JLBbakgroud3.setIcon(imTitle2);
        JDrelat.add(JLBbakgroud3);
        
        if(tipo.equals("pg")){
            relat_horas_pg();
        }else if(tipo.equals("exe")){
            relat_horas_exec();
        }
        JDrelat.setVisible(true);
    }
    private void relat_horas_pg(){
            
            PN_horas_n_pg = new JPanel();
            PN_horas_n_pg.setBorder(BorderFactory.createTitledBorder(null, "Horas Não Pagas", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, null, Color.RED));
            PN_horas_n_pg.setBounds(10, 10, 400, 240);
            PN_horas_n_pg.setLayout(null);
            PN_horas_n_pg.setOpaque(false);
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
                        tabela.getColumnModel().getColumn(1).setPreferredWidth(280); 
                        Scroll = new JScrollPane();  
                        Scroll.setViewportView(tabela);  
                        Scroll.setBounds(20,30,350,150);  
                                                
                        PN_horas_n_pg.add(Scroll); 
                        horas_n_pg();
                        
                        JBTimprimir_h_n_pg = new JButton("Imprimir");
                        JBTimprimir_h_n_pg.setBounds(125, 200, 150, 25);
                        PN_horas_n_pg.add(JBTimprimir_h_n_pg);



                        JBTatua_pg = new JButton();
                        JBTatua_pg.setBounds(300, 200, 50, 25);
                        PN_horas_n_pg.add(JBTatua_pg);
                JLBbakgroud3.add(PN_horas_n_pg);
    }
    private void horas_n_pg(){
        ResultSet retorno_consulta;
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
    }
    private void relat_horas_exec(){
        
            PN_horas_n_exe = new JPanel();
            PN_horas_n_exe.setBorder(BorderFactory.createTitledBorder(null, "Horas Não Executadas", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, null, Color.RED));
            PN_horas_n_exe.setBounds(10, 10, 400, 240);
            PN_horas_n_exe.setLayout(null);
            PN_horas_n_exe.setOpaque(false);
            //JLBbakgroud.add(PN_horas_n_exe);

                        tabela2 = new JTable();  
                        tabela2.setModel(new DefaultTableModel(  
                            new Object[][]{},  
                            //TITULOS DAS SUAS COLUNAS  
                            new String []{"Código","Nome","Tipo de Serviço"})  
                            {  
                                public boolean isCellEditable(int r, int c)  
                                {  
                                    return false;  
                                }  
                            } );  
                        tabela2.getTableHeader().setReorderingAllowed(false);  
                        tabela2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);  
                        tabela2.setSelectionMode(0);  
                        
                        dtm2 = (DefaultTableModel) tabela2.getModel();
                        
                        tabela2.getColumnModel().getColumn(0).setPreferredWidth(50); 
                        tabela2.getColumnModel().getColumn(1).setPreferredWidth(190); 
                        tabela2.getColumnModel().getColumn(2).setPreferredWidth(90); 
                        Scroll2 = new JScrollPane();  
                        Scroll2.setViewportView(tabela2);  
                        Scroll2.setBounds(20,30,350,150);  

                        PN_horas_n_exe.add(Scroll2); 
                        horas_n_exe();
                        
                        JBTimprimir_h_n_exe = new JButton("Imprimir");
                        JBTimprimir_h_n_exe.setBounds(125, 200, 150, 25);
                        PN_horas_n_exe.add(JBTimprimir_h_n_exe);

                        JBTatua_exe = new JButton();
                        JBTatua_exe.setBounds(300, 200, 50, 25);
                        PN_horas_n_exe.add(JBTatua_exe);
                        
            JLBbakgroud3.add(PN_horas_n_exe);
    }
    private void horas_n_exe(){
        ResultSet retorno_consulta;
            consultas_db.SQL_contribuinte contribuinte = new SQL_contribuinte();//cria objeto na classe consultas e já conecta
            consultas_db.SQL_relatorios rlt = new SQL_relatorios();
            consultas_db.conectar conc = new conectar();
            retorno_consulta = new consultas_db.SQL_relatorios().SQL_pesquisa_horas_n_exe();
            
            try{
                dtm2.setNumRows(0);
                    while(retorno_consulta.next()){
                        dtm2.addRow(new String []{
                            retorno_consulta.getString("cod_contribuinte"), 
                            retorno_consulta.getString("nome"),
                            retorno_consulta.getString("tipo_servico")
                        });
                        
                    }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro\n"+ex.getMessage());
            }finally{
                if(conc.Conectado == 0){
                    conc.desconetBase();                
                }
            }
    }
    
    private class evt_main_funcoes implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            
            if(ob.equals(iten_solicitar_doc)){
                new interface_sistema.servico_hora_maquina().interface_lancar_servico();
            }
            if(ob.equals(iten_confirmar_Pg)){
                new interface_sistema.servico_hora_maquina().interface_confirmar_01_horas_contribuinte();
            }
            if(ob.equals(iten_confirmar_exe)){
                new interface_sistema.servico_hora_efetuada().interface_confirmar_01_horas_exec();
            }
            if(ob.equals(iten_buscar_hora)){
                new interface_sistema.servico_hora_maquina().interface_busca_horas_contribuinte();
            }
            if(ob.equals(iten_excluir_hora)){
                new interface_sistema.servico_hora_maquina().interface_excluir_horas_contribuinte();
            }
            if(ob.equals(JBTimprimir_h_n_exe)){
                JOptionPane.showMessageDialog(null, "Impressão");
            }
            if(ob.equals(JBTimprimir_h_n_pg)){
                JOptionPane.showMessageDialog(null, "Impressão");
            }
            if(ob.equals(JBTatua_exe)){
                horas_n_exe();
            }
            if(ob.equals(JBTatua_pg)){
                horas_n_pg();
            }
            if(ob.equals(iten_cadastrar_contribuinte)){
                new contribuinte().interface_cadastrar();
            }
            if(ob.equals(iten_editar_contribuinte)){
                new contribuinte().interface_editar();
            }
            if(ob.equals(iten_relatorio)){
                select_relat();
            }
            if(ob.equals(iten_alterar_quant_horas)){
                new interface_contribuinte_altera_dados().interface_altera_valor();            
            }
            if(ob.equals(iten_novo_semestre)){
                int op = JOptionPane.showConfirmDialog(null, "Deseja iniciar outro semestre?");
                if(op == 0){
                    new consultas_db.SQL_novo_semestre().SQL_novo_semestre();
                }
            }
        }
    }
    
    
}
