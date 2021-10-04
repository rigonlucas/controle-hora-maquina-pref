package interface_sistema;

import impressoes.ordem_servico;
import impressoes.solicitacao_recolhimento;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import sistema_agricultura.Principal;
//import sun.swing.table.DefaultTableCellHeaderRenderer;

public class servico_hora_efetuada {

    private JDialog JDservico;
    private JDialog JDservico2;
    //labels
    private JLabel JLBnome;
    private JLabel JLBnome2;
    private JLabel JLBapelido;
    private JLabel JLBrg;
    private JLabel JLBcpf;
    private JLabel JLBn_bloco;
    private JLabel JLBtotal_horas_pagas;
    private JLabel JLBendereco;
    private JLabel JLBtelefone;
    private JLabel JLBhoras_de_servico;
    private JLabel JLBhoras_executadas;
    //private JLabel JLBvalor_total;
    private JLabel JLBn_dam;
    private JLabel JLBdata_pagt;
    //--------------
    //text field
    private JFormattedTextField JTFquanti_horas_efetuadas;
    //------------------
    //button
    private JButton JBbuscar_contri;
    private JButton JBlancar_servico;
    private JButton JBcancelar;
    private JButton JBcancelar2;
    private JButton JBgerar_ordem;
    private JButton JBlimpar_campos;
    private JButton JBadicionar_hora;
    private JButton JBexcluir;
    private JButton JBimprimir;
    private JButton JBremover_valor;
    private JButton JBconfirmar;
    private JButton JBconfirmar2;
    //painel
    private JPanel JPNcontribuinte;
    private JPanel JPNhoras_maquina;
    private JPanel JPNvalor_horas;
    //Jtable
    private JTable tabela;
    private DefaultTableModel dtm;
    private JScrollPane Scroll;

    //radio buton
    private JRadioButton JRBcom_desconto;
    private JRadioButton JRBsem_desconto;
    //variáveis
    private float Public_total = 0;
    
    //result set
    private ResultSet retorno_consulta;
    //controle do contribuinte
    public int cod_contri;
    
    private String nome_contri;
    
    private int cod_servico;
    //classes de eventos genérico
  
    private JLabel background;
    private ImageIcon imTitle;
    //------------------------------------------------------------------------

    public void interface_confirmar_01_horas_exec() {

        JDservico = new JDialog();
        JDservico.setBounds(200, 200, 650, 500);
        JDservico.setLocationRelativeTo(null);
        JDservico.setLayout(null);
        JDservico.setResizable(true);
        JDservico.setModal(true);
        JDservico.setTitle("Confirmar Serviços de Horas Máquina");
        
        ImageIcon titulo = new ImageIcon("imagens/icones/ok.png");
        JDservico.setIconImage(titulo.getImage());
        
        imTitle = new ImageIcon("imagens/background/BG_Sistema_01.png");
        background = new JLabel();
        background.setIcon(imTitle);
        background.setBounds(0, 0, 650, 500);
        JDservico.add(background);
        
        //painel contribuinte
        JPNcontribuinte = new JPanel();
        JPNcontribuinte.setLayout(null);
        JPNcontribuinte.setBounds(10, 10, 610, 200);
        JPNcontribuinte.setBorder(BorderFactory.createTitledBorder("Dados do contribuinte"));
        JPNcontribuinte.setOpaque(false);
        background.add(JPNcontribuinte);
        //----------------
        //painel cadastro de horas
        JPNhoras_maquina = new JPanel();
        JPNhoras_maquina.setLayout(null);
        JPNhoras_maquina.setBounds(10, 220, 610, 200);
        JPNhoras_maquina.setBorder(BorderFactory.createTitledBorder("Cadastro de Horas Máquina"));
        JPNhoras_maquina.setOpaque(false);
        background.add(JPNhoras_maquina);
        //--------------------------


        //JLABELS
        JLBnome = new JLabel("Contribuinte: ");
        JLBnome.setBounds(10, 40, 350, 25);
        JPNcontribuinte.add(JLBnome);

        JLBapelido = new JLabel("Apelido:");
        JLBapelido.setBounds(360, 40, 250, 25);
        JPNcontribuinte.add(JLBapelido);

        JLBrg = new JLabel("RG: ");
        JLBrg.setBounds(10, 80, 120, 25);
        JPNcontribuinte.add(JLBrg);

        JLBcpf = new JLabel("CPF: ");
        JLBcpf.setBounds(150, 80, 150, 25);
        JPNcontribuinte.add(JLBcpf);

        JLBn_bloco = new JLabel("Nº Bloco de Produtor: ");
        JLBn_bloco.setBounds(320, 80, 300, 25);
        JPNcontribuinte.add(JLBn_bloco);

        JLBtotal_horas_pagas = new JLabel("Total de Horas Pagas: ");
        JLBtotal_horas_pagas.setBounds(10, 120, 240, 25);
        JPNcontribuinte.add(JLBtotal_horas_pagas);
        
        JLBhoras_executadas = new JLabel("Total de Horas Executadas: ");
        JLBhoras_executadas.setBounds(250, 120, 240, 25);
        JPNcontribuinte.add(JLBhoras_executadas);

        JLBendereco = new JLabel("Endereço: ");
        JLBendereco.setBounds(10, 160, 400, 25);
        JPNcontribuinte.add(JLBendereco);

        JLBtelefone = new JLabel("Telefone: ");
        JLBtelefone.setBounds(430, 160, 190, 25);
        JPNcontribuinte.add(JLBtelefone);

        
        // tabela
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(
                new Object[][]{},
                //TITULOS DAS SUAS COLUNAS  
                new String[]{"Código", "Nº DAM", "Data do Pagamento", "QuantidadeHoras", "Valor Total", "Executado"}) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(0);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
        



        Scroll = new JScrollPane();
        Scroll.setViewportView(tabela);
        Scroll.setBounds(10, 20, 590, 140);
        JPNhoras_maquina.add(Scroll);

        //dtm = (DefaultTableModel) tabela.getModel();
        //dtm.addRow(new Object[]{"1", "3256", "24/01/2014", "1", "40,07", "SIM"});


        //buttons
        JBbuscar_contri = new JButton("Buscar Contribuinte");
        JBbuscar_contri.setBounds(205, 10, 200, 25);
        JPNcontribuinte.add(JBbuscar_contri);

        JBgerar_ordem = new JButton("Sair");
        JBgerar_ordem.setBounds(150, 430, 150, 25);
        background.add(JBgerar_ordem);

        JBlimpar_campos = new JButton("Limpar Campos");
        JBlimpar_campos.setBounds(340, 430, 150, 25);
        background.add(JBlimpar_campos);

        JBconfirmar = new JButton("Confirmar Execução");
        JBconfirmar.setBounds(190, 165, 200, 25);
        JPNhoras_maquina.add(JBconfirmar);
        
        set_icons();
        JBconfirmar.setEnabled(false);
        JBbuscar_contri.addActionListener(new evt_confirma_01());
        JBconfirmar.addActionListener(new evt_confirma_01());
        JBlimpar_campos.addActionListener(new evt_confirma_01());
        JBgerar_ordem.addActionListener(new evt_confirma_011());
        
        JDservico.setVisible(true);
    }
    private class evt_confirma_011 implements ActionListener{

        Object ob;
        public void actionPerformed(ActionEvent e) {
            ob = e.getSource();
            if(ob.equals(JBgerar_ordem)){                
                JDservico.dispose();
            }
        }
        
    }
    private class evt_confirma_01 implements ActionListener{
        Object ob;
        public void actionPerformed(ActionEvent e) {
            ob = e.getSource();
            if(ob.equals(JBbuscar_contri)){
                cod_contri = 0;
                pesquisa ips = new pesquisa();
                ips.interface_pesquisa_contribuinte();
                cod_contri = ips.get_cod_contri;
                set_dados_contribuinte();
                JBconfirmar.setEnabled(true);
                retorno_consulta = new consultas_db.SQL_lancar_horas_efetuadas().SQL_horas_efetuadas(cod_contri);
                try{
                    dtm = (DefaultTableModel) tabela.getModel();
                    dtm.setNumRows(0);
                    while(retorno_consulta.next()){
                        //JOptionPane.showMessageDialog(null, retorno_consulta.getString("n_dam"));
                        dtm.addRow(new String []{                            
                            retorno_consulta.getString("cod_hora_maquina"), 
                            retorno_consulta.getString("n_dam"), 
                            retorno_consulta.getString("data_pagamento"), 
                            retorno_consulta.getString("quantidade_horas"), 
                            retorno_consulta.getString("valor_servico"),
                            retorno_consulta.getString("executado")
                        });
                    }
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
            }if(ob.equals(JBconfirmar)){
                int row_selected;
                row_selected = tabela.getSelectedRow();
                if(row_selected>-1){
                    String DAM = tabela.getValueAt(row_selected, 0).toString();
                    cod_servico = Integer.parseInt(tabela.getValueAt(row_selected, 0).toString());
                    interface_confirmar_02_horas_exec(DAM, cod_contri);
                    //new interface_sistema.main_interface().exec_relat(0);
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione um Serviço");  
                    cod_servico = 0;
                }
            }
            if(ob.equals(JBlimpar_campos)){
                limpar_labels();
                dtm = (DefaultTableModel) tabela.getModel();
                dtm.setNumRows(0);
            }
        }
        
    }
    //------------------------------------------------
    
    //interface_confirmar_02_horas_contribuinte

    public void interface_confirmar_02_horas_exec(String dam, int cod_contribuinte) {

        JDservico2 = new JDialog();
        JDservico2.setBounds(200, 200, 350, 220);
        JDservico2.setLocationRelativeTo(JPNcontribuinte);
        JDservico2.setLayout(null);
        JDservico2.setModal(true);
        JDservico2.setResizable(true);
        JDservico2.setTitle("Confirmar Serviços de Horas Máquina Efetuado");
        
        ImageIcon titulo = new ImageIcon("imagens/icones/ok.png");
        JDservico2.setIconImage(titulo.getImage());
        
        imTitle = new ImageIcon("imagens/background/BG_Sistema_01.png");
        background = new JLabel();
        background.setIcon(imTitle);
        background.setBounds(0, 0, 350, 220);
        JDservico2.add(background);        
        
        JLBn_dam = new JLabel("DAM: "+dam);
        JLBn_dam.setBounds(10, 10, 170, 25);
        background.add(JLBn_dam);

        JLBnome2 = new JLabel("Contribuinte: "+nome_contri);
        JLBnome2.setBounds(10, 40, 300, 25);
        background.add(JLBnome2);

        JLBn_dam = new JLabel("Horas Efetuadas:");
        JLBn_dam.setBounds(10, 70, 100, 25);
        background.add(JLBn_dam);


        MaskFormatter maskFquant = new MaskFormatter();
        maskFquant = mascaras_Valor();
        JTFquanti_horas_efetuadas = new JFormattedTextField(maskFquant);
        JTFquanti_horas_efetuadas.setBounds(120, 70, 80, 25);
        background.add(JTFquanti_horas_efetuadas);
        //máscara do valor
        
        JBconfirmar2 = new JButton("Confirmar");
        JBconfirmar2.setBounds(10, 130, 150, 25);
        background.add(JBconfirmar2);

        JBcancelar = new JButton("Cancelar");
        JBcancelar.setBounds(180, 130, 150, 25);
        background.add(JBcancelar);
        set_icons();
        JBcancelar.addActionListener(new evt_confirma_02());
        JBconfirmar2.addActionListener(new evt_confirma_02());
        JDservico2.setVisible(true);
    }
    
    private class evt_confirma_02 implements ActionListener{

        Object ob;
        public void actionPerformed(ActionEvent e) {
            ob = e.getSource();
            if(ob.equals(JBconfirmar2)){
                int row_selected;
                row_selected = tabela.getSelectedRow();
                String quantidade_horas_eft = JTFquanti_horas_efetuadas.getText().trim().replace(",", ".");
                int quanti_char = quantidade_horas_eft.length();
                if(!quantidade_horas_eft.equals(".") && quanti_char == 3){
                    float quantidade_horas_eftF = Float.parseFloat(quantidade_horas_eft);

                    new consultas_db.SQL_lancar_horas_efetuadas().SQL_servico_confirma_execucao(cod_servico, quantidade_horas_eftF, cod_contri);
                    retorno_consulta = new consultas_db.SQL_lancar_horas_efetuadas().SQL_horas_efetuadas(cod_contri);
                    try{
                        dtm = (DefaultTableModel) tabela.getModel();
                        dtm.setNumRows(0);
                        while(retorno_consulta.next()){
                            //JOptionPane.showMessageDialog(null, retorno_consulta.getString("n_dam"));
                            dtm.addRow(new String []{                            
                                retorno_consulta.getString("cod_hora_maquina"), 
                                retorno_consulta.getString("n_dam"), 
                                retorno_consulta.getString("data_pagamento"), 
                                retorno_consulta.getString("quantidade_horas"), 
                                retorno_consulta.getString("valor_servico"),
                                retorno_consulta.getString("executado")
                            });
                        }
                        //new main_interface().horas_n_pg();
                        set_dados_contribuinte();
                        }catch (SQLException ex){
                            JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                        }

                    JDservico2.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Digite corretamente os dados no campo!");
                }
                    
            }
            if(ob.equals(JBcancelar)){
                JDservico2.dispose();
            }
            
        }
        
    }
    
    
    //mascara valor
    private MaskFormatter mascaras_Valor() {
        MaskFormatter mask_CPF = null;
        try {
            mask_CPF = new MaskFormatter("#,#");
            mask_CPF.setValidCharacters("1234567890");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mask_CPF;
    }

    
    
    private void set_dados_contribuinte() {
        String nome , apelido , RG, CPF  , n_bloco  , endereco  , Tel  , horas_pagas , horas_exe ;
        //String nome;
            //JOptionPane.showMessageDialog(null, cod_contri);
            retorno_consulta = new consultas_db.SQL_contribuinte().SQL_pesquisa_contribuinte_completo(cod_contri);

        try {
            if(cod_contri>0){
                retorno_consulta.next();
                nome = retorno_consulta.getString("nome");
                nome_contri = nome;
                apelido = retorno_consulta.getString("apelido");
                RG = retorno_consulta.getString("rg");
                CPF = retorno_consulta.getString("cpf");
                n_bloco = retorno_consulta.getString("n_bloco_produtor");
                Tel = retorno_consulta.getString("telefone");
                horas_pagas = retorno_consulta.getString("horas_pagas");
                horas_exe = retorno_consulta.getString("horas_executadas");
                endereco = retorno_consulta.getString("endereco");
                //System.out.println(nome);
                JLBnome.setText("Cntribuinte: "+nome);
                JLBapelido.setText("Apelido: "+apelido);
                JLBrg.setText("RG: "+RG);
                JLBcpf.setText("CPF: "+CPF);
                JLBn_bloco.setText("Nº Bloco de Produtor: "+n_bloco);
                JLBendereco.setText("Endereço: "+endereco);
                JLBtelefone.setText("Telefone:"+Tel);
                JLBtotal_horas_pagas.setText("Total de Horas Pagas: "+horas_pagas);
                JLBhoras_executadas.setText("Total de Horas Executadas: "+horas_exe);
                float h_exe = Float.parseFloat(horas_exe), h_pg = Float.parseFloat(horas_pagas);
                if(h_exe>h_pg){
                    JLBhoras_executadas.setForeground(Color.red);
                }else if(h_exe<h_pg){
                    JLBhoras_executadas.setForeground(Color.blue);
                }else{
                    //JLBhoras_executadas.setForeground(Color.);
                    JLBhoras_executadas.setForeground(Color.decode("#006400"));
                    //JLBhoras_executadas.setFont(new Font("Courier New", Font.BOLD, 12));
                }
            }
            
        } catch(Exception exx){
            Logger.getLogger(pesquisa.class.getName()).log(Level.SEVERE, null, exx);
        }finally{
            
            
        }

    }
    
    //métodos de lipeza de campos do lançar serviço

    private void limpar_lancar_servico() {
        limpar_labels();

        JLBtotal_horas_pagas.setText("Total de Horas Pagas:");
        
        int row = 0;
        int linhas = tabela.getRowCount();
        if(linhas>0){
            for(int i = 0; i < linhas; i++){
                dtm.removeRow(0);
            }
        }
            
    }

    private void limpar_labels() {

        JLBnome.setText("Contribuinte:");
        JLBapelido.setText("Apelido:");
        JLBrg.setText("RG:");
        JLBcpf.setText("CPF:");
        JLBn_bloco.setText("Nº Bloco de Produtor:");
        JLBendereco.setText("Endereço:");
        JLBtelefone.setText("Telefone:");
        if(!JLBhoras_executadas.equals(null)){
            JLBhoras_executadas.setText("Total de Horas Executadas: ");
        }
        if(!JLBtotal_horas_pagas.equals(null)){
            JLBtotal_horas_pagas.setText("Total de Horas Pagas:");
        }
    }
    
    private void set_icons(){
        ImageIcon imprmir = new ImageIcon("imagens/icones/imprimir.png");

        if(JBadicionar_hora!=null){
            ImageIcon add_hira = new ImageIcon("imagens/icones/add.png");
            JBadicionar_hora.setIcon(add_hira); 
        }
        if(JBbuscar_contri!=null){
            ImageIcon busca  = new ImageIcon("imagens/icones/btn-buscar.png");
            JBbuscar_contri.setIcon(busca); 
        }
        if(JBcancelar!=null){
           ImageIcon cancelar = new ImageIcon("imagens/icones/cancelar.gif");
            JBcancelar.setIcon(cancelar);  
        }
        if(JBcancelar2!=null){
            ImageIcon cancelar2 = new ImageIcon("imagens/icones/cancelar.gif");
            JBcancelar2.setIcon(cancelar2); 
        }
        if(JBconfirmar!=null){
            ImageIcon confirmar = new ImageIcon("imagens/icones/ok.png");
           JBconfirmar .setIcon(confirmar); 
        }
        if(JBexcluir!=null){
            ImageIcon excluir  = new ImageIcon("imagens/icones/remover.png");
            JBexcluir.setIcon(excluir); 
        }
        if(JBgerar_ordem!=null){
            ImageIcon ordem = new ImageIcon("imagens/icones/ok.png");
            JBgerar_ordem.setIcon(ordem); 
        }
        if(JBlancar_servico!=null){
            ImageIcon lanca_servico  = new ImageIcon("imagens/icones/ok.png");
            JBlancar_servico.setIcon(lanca_servico); 
        }
        if(JBlimpar_campos!=null){
            ImageIcon limpar = new ImageIcon("imagens/icones/clean.gif");
            JBlimpar_campos.setIcon(limpar); 
        }
        if(JBremover_valor!=null){
            ImageIcon remove_val = new ImageIcon("imagens/icones/remover.png");
            JBremover_valor.setIcon(remove_val); 
        }
        if(JBimprimir != null){
            ImageIcon imp = new ImageIcon("imagens/icones/imprimir.png");
            JBimprimir.setIcon(imp); 
        }
        if(JBconfirmar2 != null){
            ImageIcon confirmar = new ImageIcon("imagens/icones/ok.png");
           JBconfirmar2 .setIcon(confirmar); 
        }
        
    }  

    
}
