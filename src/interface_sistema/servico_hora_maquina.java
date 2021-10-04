package interface_sistema;

import impressoes.ordem_servico;
import impressoes.solicitacao_recolhimento;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class servico_hora_maquina {
    
    public servico_hora_maquina(){
        imTitle = new ImageIcon("imagens/background/BG_Sistema_01.png");
        background = new JLabel();
        background.setIcon(imTitle);
        background.setBounds(0, 0, 650, 500);
        
        background2 = new JLabel();
        background2.setIcon(imTitle);
        background2.setBounds(0, 0, 650, 500);
        
        background3 = new JLabel();
        background3.setIcon(imTitle);
        background3.setBounds(0, 0, 280, 200);
    }
    
    private JDialog JDservico;
    private JDialog JDservico2;
    //labels
    private JLabel JLBnome;
    private JLabel JLBapelido;
    private JLabel JLBrg;
    private JLabel JLBcpf;
    private JLabel JLBn_bloco;
    private JLabel JLBtotal_horas_pagas;
    private JLabel JLBendereco;
    private JLabel JLBtelefone;
    private JLabel JLBhoras_de_servico;
    private JLabel JLBhoras_executadas;
    private JLabel JLBvalor_total;
    private JLabel JLBn_dam;
    private JLabel JLBdata_pagt;
    
    private JLabel JLBtipo_servico;

    //--------------
    //text field
    private JFormattedTextField JTF_quantidade_hora;
    private JFormattedTextField JTF_data;
    private JTextField JTFn_dam;
    private JTextField JTFtipo_servico;
    //------------------
    //button
    private JButton JBbuscar_contri;
    private JButton JBlancar_servico;
    private JButton JBcancelar;
    private JButton JBcancelar2;
    private JButton JBcancelar3;
    private JButton JBgerar_ordem;
    private JButton JBlimpar_campos;
    private JButton JBadicionar_hora;
    private JButton JBexcluir;
//    private JButton JBimprimir;
    private JButton JBremover_valor;
    private JButton JBconfirmar;
    private JButton JBconfirmar3;
    //painel
    private JPanel JPNcontribuinte;
    private JPanel JPNhoras_maquina;
    private JPanel JPNvalor_horas;
    
    //Jtable
    private JTable tabela;
    private DefaultTableModel dtm;
    private JScrollPane Scroll;
    private JScrollPane Scroll2;

    //radio buton
    private JRadioButton JRBcom_desconto;
    private JRadioButton JRBsem_desconto;
    //variáveis
    private float Public_total = 0;
    
    //result set
    private ResultSet retorno_consulta;
    //controle do contribuinte
    private int cod_contri, cod_hora_maquina;
    
    private String nome_contri;
    
    private JLabel background, background2,background3;
    private ImageIcon imTitle;
    
    public void interface_lancar_servico() {
        JDservico = new JDialog();
        JDservico.setBounds(200, 200, 650, 500);
        JDservico.setLocationRelativeTo(null);
        JDservico.setLayout(null);
        JDservico.setModal(true);
        JDservico.setResizable(true);
        JDservico.setTitle("Serviço de Horas Máquina");
        ImageIcon titulo = new ImageIcon("imagens/icones/time_machine.png");
        JDservico.setIconImage(titulo.getImage());
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
        //painel valor da hora


        //----------
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

        JLBhoras_de_servico = new JLabel("Horas de Serviço:");
        JLBhoras_de_servico.setBounds(10, 40, 110, 25);
        JPNhoras_maquina.add(JLBhoras_de_servico);

        JLBvalor_total = new JLabel("Valor do Serviço: ");
        JLBvalor_total.setBounds(10, 170, 250, 25);
        JPNhoras_maquina.add(JLBvalor_total);


        JPNvalor_horas = new JPanel();
        JPNvalor_horas.setBounds(200, 15, 150, 60);
        //JPNvalor_horas.setLayout(null);
        JPNvalor_horas.setBorder(BorderFactory.createTitledBorder("Valor da Hora"));
        JPNhoras_maquina.add(JPNvalor_horas);
        //radio butons
        //objetos do button
        JRBcom_desconto = new JRadioButton("40,07");
        JRBcom_desconto.setSelected(true);
        JPNvalor_horas.add(JRBcom_desconto);

        JRBsem_desconto = new JRadioButton("69,71");
        JPNvalor_horas.add(JRBsem_desconto);

        //group
        ButtonGroup BtBroup = new ButtonGroup();
        BtBroup.add(JRBcom_desconto);
        BtBroup.add(JRBsem_desconto);
        JRBcom_desconto.setEnabled(false);
        JRBsem_desconto.setEnabled(false);
        
        JRBcom_desconto.setOpaque(false);
        JRBsem_desconto.setOpaque(false);
        //máscara do valor
        MaskFormatter maskFvalor = new MaskFormatter();
        maskFvalor = mascaras_Valor();
        JTF_quantidade_hora = new JFormattedTextField(maskFvalor);
        JTF_quantidade_hora.setBounds(120, 40, 70, 25);
        JPNhoras_maquina.add(JTF_quantidade_hora);
        JTF_quantidade_hora.setEditable(false);
        // tabela
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(
                new Object[][]{},
                //TITULOS DAS SUAS COLUNAS  
                new String[]{"Horas", "Valor da Hora", "Total"}) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(0);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(95);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        Scroll = new JScrollPane();
        Scroll.setViewportView(tabela);
        Scroll.setBounds(10, 80, 250, 80);
        JPNhoras_maquina.add(Scroll);
        
        JLBtipo_servico = new JLabel("Tipo de Serviço: ");
        JLBtipo_servico.setBounds(250, 170, 110, 25);
        JPNhoras_maquina.add(JLBtipo_servico);
        
        JTFtipo_servico = new JTextField();
        JTFtipo_servico.setBounds(350, 170, 200, 25);
        JPNhoras_maquina.add(JTFtipo_servico);
        
        //buttons
        JBbuscar_contri = new JButton("Buscar Contribuinte");
        JBbuscar_contri.setBounds(205, 10, 200, 25);
        JPNcontribuinte.add(JBbuscar_contri);

        JBadicionar_hora = new JButton("Calcular Hora");
        JBadicionar_hora.setBounds(360, 40, 140, 25);
        JPNhoras_maquina.add(JBadicionar_hora);
        JBadicionar_hora.setEnabled(false);

        JBremover_valor = new JButton("Remover Valor");
        JBremover_valor.setBounds(270, 100, 140, 25);
        JPNhoras_maquina.add(JBremover_valor);

        JBlancar_servico = new JButton("Lançar Serviço");
        JBlancar_servico.setBounds(60, 430, 150, 25);
        background.add(JBlancar_servico);
        JBlancar_servico.setEnabled(false);

        JBcancelar = new JButton("Cancelar");
        JBcancelar.setBounds(230, 430, 150, 25);
        background.add(JBcancelar);

        JBlimpar_campos = new JButton("Limpar");
        JBlimpar_campos.setBounds(400, 430, 150, 25);
        background.add(JBlimpar_campos);
        JPNvalor_horas.setOpaque(false);
        
        set_icons();
        
        JBbuscar_contri.addActionListener(new evt_servico());
        JBcancelar.addActionListener(new evt_servico());
        JBlimpar_campos.addActionListener(new evt_servico_lancar());
        JBlancar_servico.addActionListener(new evt_servico_lancar());
        JBadicionar_hora.addActionListener(new evt_servico_lancar());
        JBremover_valor.addActionListener(new evt_servico_lancar());
        
        JDservico.setVisible(true);
        
    }
    //classes de eventos genérico
    private class evt_servico implements ActionListener {

        public void actionPerformed(ActionEvent evet_click) {
            Object ob;
            ob = evet_click.getSource();
            if (ob.equals(JBcancelar)) {
                JDservico.dispose();
            }
            if (ob.equals(JBbuscar_contri)) {

                cod_contri = 0;
                pesquisa ips = new pesquisa();
                ips.interface_pesquisa_contribuinte();
                cod_contri = ips.get_cod_contri;
                if(cod_contri>0){
                    JBadicionar_hora.setEnabled(true);
                    JTF_quantidade_hora.setEditable(true);
                    JRBcom_desconto.setEnabled(true);
                    JRBsem_desconto.setEnabled(true);
                                      
                }else{
                    JBadicionar_hora.setEnabled(false);
                    JTF_quantidade_hora.setEditable(false);
                    JRBcom_desconto.setEnabled(false);
                    JRBsem_desconto.setEnabled(false);
                    JBlancar_servico.setEnabled(false);
                }
                if(cod_contri>0){
                    set_dados_contribuinte();
                }
            }
        }
    }
    //classes de eventos lançar serviço

    private class evt_servico_lancar implements ActionListener {

        public void actionPerformed(ActionEvent evet_click) {
            Object ob;
            ob = evet_click.getSource();
            //float total
            if (ob.equals(JBlancar_servico)) {
                
                float quantidade_horas = Float.parseFloat(dtm.getValueAt(0, 0).toString().replace(",", "."));
                float total = Float.parseFloat(dtm.getValueAt(0, 2).toString().replace(",", "."));
                int tipo = 1;
                if(JRBcom_desconto.isSelected()){
                    tipo=1;
                }else if(JRBsem_desconto.isSelected()){
                    tipo = 2;
                }
                String type_servico = JTFtipo_servico.getText().toUpperCase();
                int rs = new consultas_db.SQL_lancar_servico().SQL_solicita_servico(cod_contri, quantidade_horas, total, tipo,type_servico);
                    if(rs == 1){            
                        Public_total = 0;
                        JOptionPane.showMessageDialog(null,"Serviço Lançado");
                        interface_imprimir_solicitacao(cod_contri);
                        JDservico.dispose();
                    }
                }
            if (ob.equals(JBlimpar_campos)) {
                limpar_lancar_servico();
            }
            
            if (ob.equals(JBadicionar_hora)) {
                float hora, valor = 0, total;
                String valorS = JTF_quantidade_hora.getText().trim();
                //System.err.println(valorS);
                    int quanti_char = valorS.length();
                    if(!valorS.equals(".") && quanti_char == 3){
                        hora = Float.parseFloat(JTF_quantidade_hora.getText().replace(",", "."));
                        if (JRBcom_desconto.isSelected()) {
                            valor = Float.parseFloat(JRBcom_desconto.getText().replace(",", "."));
                        } else if (JRBsem_desconto.isSelected()) {
                            valor = Float.parseFloat(JRBsem_desconto.getText().replace(",", "."));
                        }

                        DecimalFormat df = new DecimalFormat();
                        total = (hora * valor);
                        Public_total += total;
                        JLBvalor_total.setText("Valor do Serviço: "+Public_total);

                        dtm = (DefaultTableModel) tabela.getModel();
                        dtm.addRow(new Object[]{hora, valor, df.format(total)});
                        JBadicionar_hora.setEnabled(false);
                        JBlancar_servico.setEnabled(true);
                        //JTF_quantidade_hora.setText(null);
                    }else{
                           JOptionPane.showMessageDialog(null, "Preencha o Campo Quantidade de Horas Corretamente");
                    }

            }
            if(ob.equals(JBremover_valor)){
                try{
                    dtm.removeRow(tabela.getSelectedRow());
                    JBadicionar_hora.setEnabled(true);
                    Public_total = 0;
                    JLBvalor_total.setText("Valor do Serviço: ");
                    JBlancar_servico.setEnabled(false);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Selecione algum item para REMOVER");
                }
            }
        }
    }
    private JDialog JDimprmir;
    private JTable tabela_imprmir;
    private JScrollPane Scroll_imprimir;
    private DefaultTableModel dtm_impr;
    private JButton JBT_imprimir;
    
    public void interface_imprimir_solicitacao(int cod_contri){
        JDimprmir = new JDialog();
        JDimprmir.setBounds(200, 200, 280, 200);
        JDimprmir.setLocationRelativeTo(null);
        JDimprmir.setLayout(null);
        JDimprmir.setModal(true);
        JDimprmir.setResizable(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/imprimir.png");
        JDimprmir.setIconImage(titulo.getImage());
        JDimprmir.setTitle("Busca de Horas Efetuadas com Serviços de Horas Máquina");
        
        JDimprmir.add(background2);
        // tabela
        tabela_imprmir = new JTable();
        tabela_imprmir.setModel(new DefaultTableModel(
                new Object[][]{},
                //TITULOS DAS SUAS COLUNAS  
                new String[]{"Documento", "Valor da Hora", "Total"}) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
        tabela_imprmir.getTableHeader().setReorderingAllowed(false);
        tabela_imprmir.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabela_imprmir.setSelectionMode(0);

        tabela_imprmir.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabela_imprmir.getColumnModel().getColumn(1).setPreferredWidth(90);
        tabela_imprmir.getColumnModel().getColumn(2).setPreferredWidth(85);
        Scroll_imprimir = new JScrollPane();
        Scroll_imprimir.setViewportView(tabela_imprmir);
        Scroll_imprimir.setBounds(10, 20, 250, 80);
        background2.add(Scroll_imprimir);
                ResultSet rs;
                rs = new solicitacao_recolhimento().SQL_listar_doc(cod_contri);
                try{
                    //rs.next();
                    //int cod_doc = Integer.parseInt(rs.getString("cod_hora_maquina"));
                    
                    dtm_impr = (DefaultTableModel) tabela_imprmir.getModel();
                    dtm_impr.setNumRows(0);
                    //retorno_consulta.next();
                    while(rs.next()){
                        //JOptionPane.showMessageDialog(null, retorno_consulta.getString("n_dam"));
                        dtm_impr.addRow(new String []{                            
                            rs.getString("cod_hora_maquina"), 
                            rs.getString("quantidade_horas"),  
                            rs.getString("valor_servico"), 
                        });
                    }
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
        JBT_imprimir = new JButton("Imprimir");
        JBT_imprimir.setBounds(60, 120, 150,25);
        background2.add(JBT_imprimir);
        JBT_imprimir.addActionListener(new evt_imprimir());
        set_icons();
        JDimprmir.setVisible(true);
    }
    private class evt_imprimir implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBT_imprimir)){
                int row_sel = tabela_imprmir.getSelectedRow();
                if(row_sel>-1){
                    int cod_h_m = Integer.parseInt(dtm_impr.getValueAt(row_sel, 0).toString());
                    new solicitacao_recolhimento().solicita_doc_pdf(cod_h_m);
                    try{  
                        try {
                            java.awt.Desktop.getDesktop().open( new File( "PDF/solicitacao_recolhimento.pdf" ) );
                        } catch (IOException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }  
                    catch(IllegalArgumentException e1){  

                        System.out.println(e1);  
                    }finally{
                        JDimprmir.dispose();
                    } 
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione algum documento!");
                }
            }
        }
        
    }
    
    //------------------------------------------------------------------------

    public void interface_busca_horas_contribuinte() {
        JDservico = new JDialog();
        JDservico.setBounds(200, 200, 650, 500);
        JDservico.setLocationRelativeTo(null);
        JDservico.setLayout(null);
        JDservico.setModal(true);
        JDservico.setResizable(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/btn-buscar.png");
        JDservico.setIconImage(titulo.getImage());
        JDservico.setTitle("Busca de Horas Efetuadas com Serviços de Horas Máquina");
        
        JDservico.add(background);
        
        //painel contribuinte
        JPNcontribuinte = new JPanel();
        JPNcontribuinte.setLayout(null);
        JPNcontribuinte.setBounds(10, 10, 610, 200);
        JPNcontribuinte.setBorder(BorderFactory.createTitledBorder("Dados do contribuinte"));
        background.add(JPNcontribuinte);
        //----------------
        //painel cadastro de horas
        JPNhoras_maquina = new JPanel();
        JPNhoras_maquina.setLayout(null);
        JPNhoras_maquina.setBounds(10, 220, 610, 200);
        JPNhoras_maquina.setBorder(BorderFactory.createTitledBorder("Cadastro de Horas Máquina"));
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
                new String[]{"Código", "Nº DAM", "QuantidadeHoras", "Valor Total", "Pago"}) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(0);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(115);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(50);

        Scroll = new JScrollPane();
        Scroll.setViewportView(tabela);
        Scroll.setBounds(40, 20, 530, 140);
        JPNhoras_maquina.add(Scroll);

        //buttons
        JBbuscar_contri = new JButton("Buscar Contribuinte");
        JBbuscar_contri.setBounds(205, 10, 200, 25);
        JPNcontribuinte.add(JBbuscar_contri);

        JBcancelar3 = new JButton("Sair");
        JBcancelar3.setBounds(150, 430, 150, 25);
        background.add(JBcancelar3);

        JBlimpar_campos = new JButton("Limpar");
        JBlimpar_campos.setBounds(310, 430, 150, 25);
        background.add(JBlimpar_campos);
        
        JBT_imprimir = new JButton("Imprimir 2º via");
        JBT_imprimir.setBounds(205, 165, 200, 25);
        JPNhoras_maquina.add(JBT_imprimir);
        
        JPNcontribuinte.setOpaque(false);
        JPNhoras_maquina.setOpaque(false);
        
        JBcancelar3.addActionListener(new evt_busca_contri());
        JBbuscar_contri.addActionListener(new evt_busca_contri());
        JBlimpar_campos.addActionListener(new evt_confirma_01());
        JBT_imprimir.addActionListener(new evt_busca_contri());
        set_icons();
        JDservico.setVisible(true);
    }
    private class evt_busca_contri implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBbuscar_contri)){
                cod_contri = 0;
                pesquisa ips = new pesquisa();
                ips.interface_pesquisa_contribuinte();
                cod_contri = ips.get_cod_contri;
                set_dados_contribuinte();
                retorno_consulta = new consultas_db.SQL_lancar_servico().SQL_horas_solicitadas_por_contri(cod_contri);
                try{
                    dtm = (DefaultTableModel) tabela.getModel();
                    dtm.setNumRows(0);
                    while(retorno_consulta.next()){
                        //JOptionPane.showMessageDialog(null, retorno_consulta.getString("n_dam"));
                        dtm.addRow(new String []{                            
                            retorno_consulta.getString("cod_hora_maquina"), 
                            retorno_consulta.getString("n_dam"),  
                            retorno_consulta.getString("quantidade_horas"), 
                            retorno_consulta.getString("valor_servico"),
                            retorno_consulta.getString("pago")
                        });
                    }
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
            }
            if(ob.equals(JBcancelar3)){
                JDservico.dispose();
            }
            if(ob.equals(JBT_imprimir)){
                int row_sel = tabela.getSelectedRow();
                if(row_sel>-1){
                    int cod_h_m = Integer.parseInt(dtm.getValueAt(row_sel, 0).toString());
                    new solicitacao_recolhimento().solicita_doc_pdf(cod_h_m);
                    try{  
                        try {
                            java.awt.Desktop.getDesktop().open( new File( "C:/Users/usuario/Documents/NetBeansProjects/Sistema_Agricultura/PDF/solicitacao_recolhimento.pdf" ) );
                        } catch (IOException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }  
                    catch(IllegalArgumentException e1){  

                        System.out.println(e1);  
                    }finally{
                        JDimprmir.dispose();
                    } 
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione algum documento!");
                }
            }
        }
        
    }
    //método exlcuir
    public void interface_excluir_horas_contribuinte() {
        JDservico = new JDialog();
        JDservico.setBounds(200, 200, 650, 500);
        JDservico.setLocationRelativeTo(null);
        JDservico.setLayout(null);
        JDservico.setModal(true);
        JDservico.setResizable(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/remover.png");
        JDservico.setIconImage(titulo.getImage());
        JDservico.setTitle("Excluir Horas Efetuadas com Serviços de Horas Máquina");
        
        JDservico.add(background);
        
        //painel contribuinte
        JPNcontribuinte = new JPanel();
        JPNcontribuinte.setLayout(null);
        JPNcontribuinte.setBounds(10, 10, 610, 200);
        JPNcontribuinte.setBorder(BorderFactory.createTitledBorder("Dados do contribuinte"));
        background.add(JPNcontribuinte);
        //----------------
        //painel cadastro de horas
        JPNhoras_maquina = new JPanel();
        JPNhoras_maquina.setLayout(null);
        JPNhoras_maquina.setBounds(10, 220, 610, 200);
        JPNhoras_maquina.setBorder(BorderFactory.createTitledBorder("Cadastro de Horas Máquina"));
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

        JLBvalor_total = new JLabel("Valor do Serviço: ");
        JLBvalor_total.setBounds(10, 170, 250, 25);
        JPNhoras_maquina.add(JLBvalor_total);

        // tabela
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(
                new Object[][]{},
                //TITULOS DAS SUAS COLUNAS  
                new String[]{"Código", "Nº DAM", "Data do Pagamento", "QuantidadeHoras", "Valor Total", "Pago"}) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(0);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(95);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(50);



        Scroll = new JScrollPane();
        Scroll.setViewportView(tabela);
        Scroll.setBounds(40, 20, 530, 140);
        JPNhoras_maquina.add(Scroll);

        //dtm = (DefaultTableModel) tabela.getModel();
        //dtm.addRow(new Object[]{"1", "3256", "24/01/2014", "1", "40,07", "SIM"});


        //buttons
        JBbuscar_contri = new JButton("Buscar Contribuinte");
        JBbuscar_contri.setBounds(205, 10, 200, 25);
        JPNcontribuinte.add(JBbuscar_contri);

        JBcancelar2 = new JButton("Sair");
        JBcancelar2.setBounds(150, 430, 150, 25);
        background.add(JBcancelar2);

        JBlimpar_campos = new JButton("Limpar");
        JBlimpar_campos.setBounds(310, 430, 150, 25);
        background.add(JBlimpar_campos);

        JBexcluir = new JButton("Excluir");
        JBexcluir.setBounds(220, 165, 150, 25);
        JPNhoras_maquina.add(JBexcluir);
        
        JPNcontribuinte.setOpaque(false);
        JPNhoras_maquina.setOpaque(false);
        
        JBcancelar2.addActionListener(new evt_excluir_hora());
        JBexcluir.addActionListener(new evt_excluir_hora());
        JBbuscar_contri.addActionListener(new evt_excluir_hora());
        JBlimpar_campos.addActionListener(new evt_confirma_01());
        set_icons();
        JDservico.setVisible(true);
    }
    
    private class evt_excluir_hora implements ActionListener{
        Object ob;
        public void actionPerformed(ActionEvent e) {
            ob = e.getSource();
            if(ob.equals(JBbuscar_contri)){
                cod_contri = 0;
                pesquisa ips = new pesquisa();
                ips.interface_pesquisa_contribuinte();
                cod_contri = ips.get_cod_contri;
                set_dados_contribuinte();
                retorno_consulta = new consultas_db.SQL_lancar_servico().SQL_horas_solicitadas_por_contri(cod_contri);
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
                            retorno_consulta.getString("pago")
                        });
                    }
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
            }if(ob.equals(JBcancelar2)){
                JDservico.dispose();
            }if(ob.equals(JBexcluir)){
                int row_selected;
                row_selected = tabela.getSelectedRow();
                if(row_selected>-1){
                    cod_hora_maquina = Integer.parseInt(tabela.getValueAt(row_selected, 0).toString());
                    int prox;
                    prox = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Serviço?", "Excluir", JOptionPane.OK_CANCEL_OPTION);
                    if(prox == 0){
                        new consultas_db.SQL_lancar_servico().SQL_servico_excluir(cod_hora_maquina);
                    }
                    retorno_consulta = new consultas_db.SQL_lancar_servico().SQL_horas_solicitadas_por_contri(cod_contri);
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
                            retorno_consulta.getString("pago")
                        });
                    }
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione um Serviço");                }
            }
        }
        
    }
    //método confirmar serviço
    public void interface_confirmar_01_horas_contribuinte() {
        
        JDservico = new JDialog();
        JDservico.setBounds(200, 200, 650, 500);
        JDservico.setLocationRelativeTo(null);
        JDservico.setLayout(null);
        JDservico.setResizable(true);
        JDservico.setModal(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/golds.png");
        JDservico.setIconImage(titulo.getImage());
        JDservico.setTitle("Confirmar Serviços de Horas Máquina");

        JDservico.add(background);
        
        //painel contribuinte
        JPNcontribuinte = new JPanel();
        JPNcontribuinte.setLayout(null);
        JPNcontribuinte.setBounds(10, 10, 610, 200);
        JPNcontribuinte.setBorder(BorderFactory.createTitledBorder("Dados do contribuinte"));
        background.add(JPNcontribuinte);
        //----------------
        //painel cadastro de horas
        JPNhoras_maquina = new JPanel();
        JPNhoras_maquina.setLayout(null);
        JPNhoras_maquina.setBounds(10, 220, 610, 200);
        JPNhoras_maquina.setBorder(BorderFactory.createTitledBorder("Cadastro de Horas Máquina"));
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

        JLBvalor_total = new JLabel("Valor do Serviço: ");
        JLBvalor_total.setBounds(10, 170, 250, 25);
        JPNhoras_maquina.add(JLBvalor_total);
        
               
        // tabela
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(
                new Object[][]{},
                //TITULOS DAS SUAS COLUNAS  
                new String[]{"Código", "Nº DAM", "Data do Pagamento", "QuantidadeHoras", "Valor Total", "Pago"}) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(0);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(95);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(50);



        Scroll2 = new JScrollPane();
        Scroll2.setViewportView(tabela);
        Scroll2.setBounds(40, 20, 530, 140);
        JPNhoras_maquina.add(Scroll2);

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

        JBconfirmar = new JButton("Confirmar Pagamento");
        JBconfirmar.setBounds(190, 165, 200, 25);
        JBconfirmar.setEnabled(false);
        JPNhoras_maquina.add(JBconfirmar);

        JPNcontribuinte.setOpaque(false);
        JPNhoras_maquina.setOpaque(false);
        
        set_icons();
        
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
                retorno_consulta = new consultas_db.SQL_lancar_servico().SQL_horas_solicitadas_por_contri(cod_contri);
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
                            retorno_consulta.getString("pago")
                        });
                    }
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
            }if(ob.equals(JBconfirmar)){
                int row_selected;
                row_selected = tabela.getSelectedRow();
                if(row_selected>-1){
                    cod_hora_maquina = Integer.parseInt(tabela.getValueAt(row_selected, 0).toString());
                    interface_confirmar_02_horas_contribuinte(cod_hora_maquina, cod_contri);
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione um Serviço");                
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

    public void interface_confirmar_02_horas_contribuinte(int n_doc, int cod_contribuinte) {
        
        JDservico2 = new JDialog();
        JDservico2.setBounds(200, 200, 350, 220);
        JDservico2.setLocationRelativeTo(null);
        JDservico2.setLayout(null);
        JDservico2.setModal(true);
        JDservico2.setResizable(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/golds.png");
        JDservico.setIconImage(titulo.getImage());
        JDservico2.setTitle("Confirmar Serviços de Horas Máquina");
        
        JDservico2.add(background2);
        
        JLBn_dam = new JLabel("Número do Documento: "+n_doc);
        JLBn_dam.setBounds(10, 10, 170, 25);
        background2.add(JLBn_dam);

        JLBnome = new JLabel("Contribuinte: "+nome_contri);
        JLBnome.setBounds(10, 40, 300, 25);
        background2.add(JLBnome);

        JLBn_dam = new JLabel("Número DAM:");
        JLBn_dam.setBounds(10, 70, 100, 25);
        background2.add(JLBn_dam);

        JLBdata_pagt = new JLabel("Data do Pagamento: ");
        JLBdata_pagt.setBounds(10, 100, 120, 25);
        background2.add(JLBdata_pagt);

        JTFn_dam = new JTextField();
        JTFn_dam.setBounds(100, 70, 80, 25);
        background2.add(JTFn_dam);
        //máscara do valor
        MaskFormatter maskFdata = new MaskFormatter();
        maskFdata = mascaras_data();
        JTF_data = new JFormattedTextField(maskFdata);
        JTF_data.setBounds(140, 100, 70, 25);
        background2.add(JTF_data);

        JBconfirmar3 = new JButton("Confirmar");
        JBconfirmar3.setBounds(10, 130, 150, 25);
        background2.add(JBconfirmar3);

        JBcancelar = new JButton("Cancelar");
        JBcancelar.setBounds(180, 130, 150, 25);
        background2.add(JBcancelar);

        set_icons();
        
        JBcancelar.addActionListener(new evt_confirma_02());
        JBconfirmar3.addActionListener(new evt_confirma_02());
        JDservico2.setVisible(true);
    }
    
    private class evt_confirma_02 implements ActionListener{

        Object ob;
        public void actionPerformed(ActionEvent e) {
            ob = e.getSource();
            if(ob.equals(JBconfirmar3)){
                String n_doc = JTFn_dam.getText(), data_pgt = JTF_data.getText();
                new consultas_db.SQL_lancar_servico().SQL_servico_confirma_pagamento(cod_hora_maquina, n_doc, data_pgt);
                
                retorno_consulta = new consultas_db.SQL_lancar_servico().SQL_horas_solicitadas_por_contri(cod_contri);
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
                            retorno_consulta.getString("pago")
                        });
                    }
                    interface_imprimir_ordem_servico(cod_contri);
                    //new interface_sistema.main_interface().exec_relat(1);
                    //new main_interface().horas_n_pg();
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
                JDservico2.dispose();
                JDservico.dispose();
            }
            if(ob.equals(JBcancelar)){
                JDservico2.dispose();
            }
            
        }
        
    }
    
    //--------------------
    private JDialog JDimprmir_ordem;
    private JTable tabela_imprmir_ordem;
    private JScrollPane Scroll_imprimir_ordem;
    private DefaultTableModel dtm_impr_ordem;
    private JButton JBT_imprimir_ordem;
    
    public void interface_imprimir_ordem_servico(int cod_contri){
        JDimprmir_ordem = new JDialog();
        JDimprmir_ordem.setBounds(200, 200, 280, 200);
        JDimprmir_ordem.setLocationRelativeTo(null);
        JDimprmir_ordem.setLayout(null);
        JDimprmir_ordem.setModal(true);
        JDimprmir_ordem.setResizable(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/imprimir.png");
        JDimprmir_ordem.setIconImage(titulo.getImage());
        JDimprmir_ordem.setTitle("Busca de Horas Efetuadas com Serviços de Horas Máquina");
        
        JDimprmir_ordem.add(background3);
        
        // tabela
        tabela_imprmir_ordem = new JTable();
        tabela_imprmir_ordem.setModel(new DefaultTableModel(
                new Object[][]{},
                //TITULOS DAS SUAS COLUNAS  
                new String[]{"Documento", "Valor da Hora", "Total"}) {

            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });
        tabela_imprmir_ordem.getTableHeader().setReorderingAllowed(false);
        tabela_imprmir_ordem.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabela_imprmir_ordem.setSelectionMode(0);

        tabela_imprmir_ordem.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabela_imprmir_ordem.getColumnModel().getColumn(1).setPreferredWidth(90);
        tabela_imprmir_ordem.getColumnModel().getColumn(2).setPreferredWidth(85);
        Scroll_imprimir_ordem = new JScrollPane();
        Scroll_imprimir_ordem.setViewportView(tabela_imprmir_ordem);
        Scroll_imprimir_ordem.setBounds(10, 20, 250, 80);
        background3.add(Scroll_imprimir_ordem);
                ResultSet rs;
                rs = new ordem_servico().SQL_listar_doc(cod_contri);
                try{
                    dtm_impr_ordem = (DefaultTableModel) tabela_imprmir_ordem.getModel();
                    dtm_impr_ordem.setNumRows(0);
                    //retorno_consulta.next();
                    while(rs.next()){
                        //JOptionPane.showMessageDialog(null, retorno_consulta.getString("n_dam"));
                        dtm_impr_ordem.addRow(new String []{                            
                            rs.getString("cod_hora_maquina"), 
                            rs.getString("quantidade_horas"),  
                            rs.getString("valor_servico"), 
                        });
                    }
                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Erro Na inserção\n"+ex.getMessage());
                    }
        JBT_imprimir_ordem = new JButton("Imprimir");
        JBT_imprimir_ordem.setBounds(60, 120, 150,25);
        background3.add(JBT_imprimir_ordem);
        
        set_icons();
        
        JBT_imprimir_ordem.addActionListener(new evt_imprimir_ordem());
        JDimprmir_ordem.setVisible(true);
    }
    private class evt_imprimir_ordem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBT_imprimir_ordem)){
                int row_sel = tabela_imprmir_ordem.getSelectedRow();
                if(row_sel>-1){
                    int cod_h_m = Integer.parseInt(dtm_impr_ordem.getValueAt(row_sel, 0).toString());
                    new impressoes.ordem_servico().ordem_servico_doc_pdf(cod_h_m);
                    try{  
                        try {
                            java.awt.Desktop.getDesktop().open( new File( "PDF/ordem_servico.pdf" ) );
                        } catch (IOException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }  
                    catch(IllegalArgumentException e1){  

                        System.out.println(e1);  
                    }finally{
                        JDimprmir_ordem.dispose();
                        JDservico2.dispose();
                    } 
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione algum documento!");
                }
            }
        }
        
    }
    
    //--------------------

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

    private MaskFormatter mascaras_data() {
        MaskFormatter mask_CPF = null;
        try {
            mask_CPF = new MaskFormatter("##/##/####");
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
            if(!retorno_consulta.equals(null)){
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
                        //JLBhoras_executadas.setFont(new Font("Courier New", Font.ITALIC, 20));
                    }
                    if(h_pg>=8){
                        if(JRBcom_desconto!=null && JRBsem_desconto!=null){
                            JRBcom_desconto.setEnabled(false);
                            JRBsem_desconto.setSelected(true);
                        }
                    }else{
                        if(JRBcom_desconto!=null && JRBsem_desconto!=null){
                            JRBcom_desconto.setEnabled(true);
                            JRBsem_desconto.setSelected(false);
                            JRBsem_desconto.setEnabled(false);
                        }
                    }
            }
            
        } catch(Exception exx){
            Logger.getLogger(pesquisa.class.getName()).log(Level.SEVERE, null, exx);
            //JOptionPane.showMessageDialog(null, "");
        }

    }
    
    //métodos de lipeza de campos do lançar serviço

    private void limpar_lancar_servico() {
        limpar_labels();
        JTF_quantidade_hora.setText(null);
        JLBtotal_horas_pagas.setText("Total de Horas Pagas:");
        JLBvalor_total.setText("Valor do Serviço:");
        
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
    }
    
    
    private void set_icons(){
        ImageIcon imprmir = new ImageIcon("imagens/icones/imprimir.png");
        
        if(JBT_imprimir!=null){            
            JBT_imprimir.setIcon(imprmir);            
        }
        if(JBT_imprimir_ordem!=null){
            JBT_imprimir_ordem.setIcon(imprmir);
        }
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
        if(JBcancelar3!=null){
            ImageIcon cancelar3 = new ImageIcon("imagens/icones/cancelar.gif");
            JBcancelar3.setIcon(cancelar3); 
        }
        if(JBconfirmar!=null){
            ImageIcon confirmar = new ImageIcon("imagens/icones/ok.png");
           JBconfirmar .setIcon(confirmar); 
        }
        if(JBconfirmar3!=null){
            ImageIcon confirmar = new ImageIcon("imagens/icones/ok.png");
            JBconfirmar3.setIcon(confirmar); 
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
        
    }    
}
