/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author usuario
 */
public class contribuinte{
    private JDialog JDialog_contribuinte;
    //Labels fixos
        private JLabel JLnome;
        private JLabel JLapelido;
        private JLabel JLrg;
        private JLabel JLcpf;
        private JLabel JLbloco_prod;
        private JLabel JLendereco;
        private JLabel JLtelefone;
    //textfield
        private JTextField JTFnome;
        private JTextField JTFapelido;
        private JFormattedTextField JTFrg;
        private JFormattedTextField JTFcpf;
        private JFormattedTextField JTFbloco_prod;
        private JFormattedTextField JTFtelefone;
     //combobox
        private JTextField JTFendereco;
     //buttons
         private JButton JBTconfirmar;
         private JButton JBTconfirmar2;
         private JButton JBTcancelar;
         private JButton JBTlimpar;
         private JButton JBT_buscar_cont;
         
         private JLabel backgroud_cad, background_edit;
         private ImageIcon imTitle;
         
    // Método cadastrar
    public void interface_cadastrar (){
        JDialog_contribuinte = new JDialog();
        JDialog_contribuinte.setBounds(200, 200, 650, 250);
        JDialog_contribuinte.setLocationRelativeTo(null);
        JDialog_contribuinte.setLayout(null);
        JDialog_contribuinte.setModal(true);
        JDialog_contribuinte.setResizable(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/cliente_add.gif");
        JDialog_contribuinte.setIconImage(titulo.getImage());
        JDialog_contribuinte.setTitle("Cadastro de Contruibuintes");
        //JFrame_contribuinte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setar_componentes (LABELS)
            
        
        imTitle = new ImageIcon("imagens/background/BG_Sistema_03.png");
        backgroud_cad = new JLabel();
        backgroud_cad.setIcon(imTitle);
        backgroud_cad.setBounds(0, 0, 650, 250);
        JDialog_contribuinte.add(backgroud_cad);
            JLnome = new JLabel();
            JLnome.setBounds(10, 10, 40, 25);
            JLnome.setText("Nome:");
            
            backgroud_cad.add(JLnome);
            //----------------
            JLapelido = new JLabel();
            JLapelido.setBounds(350, 10, 80, 25);
            JLapelido.setText("Apelido:");
            backgroud_cad.add(JLapelido);
            //---------------------
            JLrg = new JLabel();
            JLrg.setBounds(25, 45, 20, 25);
            JLrg.setText("RG:");
            backgroud_cad.add(JLrg);
            //---------------------            
            JLcpf = new JLabel();
            JLcpf.setBounds(200, 45, 25, 25);
            JLcpf.setText("CPF:");
            backgroud_cad.add(JLcpf);
            //---------------------            
            JLbloco_prod = new JLabel();
            JLbloco_prod.setBounds(350, 45, 120, 25);
            JLbloco_prod.setText("Nº Bloco de Produtor:");
            backgroud_cad.add(JLbloco_prod);
            //---------------------  
            JLendereco = new JLabel();
            JLendereco.setBounds(10, 80, 65, 25);
            JLendereco.setText("Endereço:");
            backgroud_cad.add(JLendereco);
            //-----------------------            
            JLtelefone = new JLabel();
            JLtelefone.setBounds(15, 115, 65, 25);
            JLtelefone.setText("Telefone:");
            backgroud_cad.add(JLtelefone);
            //------------------------
        //setar_componentes TEXT
            JTFnome = new JTextField();
            JTFnome.setBounds(60, 10, 270, 25);
            backgroud_cad.add(JTFnome);

            //----------------------
            JTFapelido= new JTextField();
            JTFapelido.setBounds(440, 10, 190, 25);
            backgroud_cad.add(JTFapelido);
            //-------------------------       
            MaskFormatter maskFrg = new MaskFormatter();
            maskFrg = mascaras_RG();
            JTFrg = new JFormattedTextField(maskFrg);
            JTFrg.setBounds(60, 45, 90, 25);
            backgroud_cad.add(JTFrg);
            //------------------------    
            MaskFormatter maskFcpf = new MaskFormatter();
            maskFcpf = mascaras_CPF();
            JTFcpf= new JFormattedTextField(maskFcpf);
            JTFcpf.setBounds(230, 45, 100, 25);
            backgroud_cad.add(JTFcpf);
            //-------------------------           
            MaskFormatter maskFbloco_p = new MaskFormatter();
            maskFbloco_p = mascaras_Bloco_P();
            JTFbloco_prod= new JFormattedTextField(maskFbloco_p);
            JTFbloco_prod.setBounds(475, 45, 100, 25);
            backgroud_cad.add(JTFbloco_prod);
            //------------------------            
            MaskFormatter maskFbloco_Telefone = new MaskFormatter();
            maskFbloco_Telefone = mascaras_Bloco_Telefone();
            JTFtelefone= new JFormattedTextField(maskFbloco_Telefone);
            JTFtelefone.setBounds(85, 115, 100, 25);
            backgroud_cad.add(JTFtelefone);
            //-------------
        //combo box endereço
            JTFendereco = new JTextField();
            JTFendereco.setBounds(85, 80, 350, 25);
            backgroud_cad.add(JTFendereco);
            //---------------
       
            //--------------
            JBTconfirmar = new JButton();
            JBTconfirmar.setBounds(90, 170, 150, 25);
            JBTconfirmar.setText("Cadastrar");
            backgroud_cad.add(JBTconfirmar);
            //---------------
            JBTcancelar = new JButton();
            JBTcancelar.setBounds(260, 170, 150, 25);
            JBTcancelar.setText("Cancelar");
            backgroud_cad.add(JBTcancelar);
            //----------------
            JBTlimpar = new JButton();
            JBTlimpar.setBounds(430, 170, 150, 25);
            JBTlimpar.setText("Limpar");
            backgroud_cad.add(JBTlimpar);
            
            set_icone_cad();
            
            JBTconfirmar.addActionListener(new evet_ActionListnerContribuinte());
            JBTcancelar.addActionListener(new evet_ActionListner());
            JBTlimpar.addActionListener(new evet_ActionListner());
            
            
        JDialog_contribuinte.setVisible(true);
    }
    private void set_icone_cad(){
        if(JBTconfirmar!=null){
        ImageIcon ok = new ImageIcon("imagens/icones/icon_ok.png");
        JBTconfirmar.setIcon(ok);
        }
        if(JBTcancelar!=null){
        ImageIcon cancelar = new ImageIcon("imagens/icones/cancelar.gif");
        JBTcancelar.setIcon(cancelar);
        }
        if(JBTlimpar!=null){
        ImageIcon clean = new ImageIcon("imagens/icones/clean.gif");
        JBTlimpar.setIcon(clean);
        }
        
        if(JBT_buscar_cont!=null){
            ImageIcon busca = new ImageIcon("imagens/icones/icone_busca.png");
            JBT_buscar_cont.setIcon(busca);
        }
        if(JBTconfirmar2!=null){
            ImageIcon ok2 = new ImageIcon("imagens/icones/icon_ok.png");
            JBTconfirmar2.setIcon(ok2);
        }
    }
    
    //---------------------------------------------------
    public void interface_editar (){
        JDialog_contribuinte = new JDialog();
        JDialog_contribuinte.setBounds(200, 200, 650, 300);
        JDialog_contribuinte.setLocationRelativeTo(null);
        JDialog_contribuinte.setResizable(true);
        JDialog_contribuinte.setLayout(null);
        JDialog_contribuinte.setModal(true);
        JDialog_contribuinte.setTitle("Editar de Contruibuintes");
        //JFrame_contribuinte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setar_componentes (LABELS)
        ImageIcon titulo = new ImageIcon("imagens/icones/editar_title.jpg");
        JDialog_contribuinte.setIconImage(titulo.getImage());
        
        imTitle = new ImageIcon("imagens/background/BG_Sistema_03.png");
        background_edit = new JLabel();
        background_edit.setIcon(imTitle);
        background_edit.setBounds(0, 0, 650, 300);
        JDialog_contribuinte.add(background_edit);
        
            JLnome = new JLabel();
            JLnome.setBounds(10, 50, 40, 25);
            JLnome.setText("Nome:");
            background_edit.add(JLnome);
            //----------------
            JLapelido = new JLabel();
            JLapelido.setBounds(350, 50, 80, 25);
            JLapelido.setText("Apelido:");
            background_edit.add(JLapelido);
            //---------------------
            JLrg = new JLabel();
            JLrg.setBounds(25, 85, 20, 25);
            JLrg.setText("RG:");
            background_edit.add(JLrg);
            //---------------------            
            JLcpf = new JLabel();
            JLcpf.setBounds(200, 85, 25, 25);
            JLcpf.setText("CPF:");
            background_edit.add(JLcpf);
            //---------------------            
            JLbloco_prod = new JLabel();
            JLbloco_prod.setBounds(350, 85, 120, 25);
            JLbloco_prod.setText("Nº Bloco de Produtor:");
            background_edit.add(JLbloco_prod);
            //---------------------  
            JLendereco = new JLabel();
            JLendereco.setBounds(10, 120, 65, 25);
            JLendereco.setText("Endereço:");
            background_edit.add(JLendereco);
            //-----------------------            
            JLtelefone = new JLabel();
            JLtelefone.setBounds(15, 155, 65, 25);
            JLtelefone.setText("Telefone:");
            background_edit.add(JLtelefone);
            //------------------------
        //setar_componentes TEXT
            JTFnome = new JTextField();
            JTFnome.setBounds(60, 50, 270, 25);
            background_edit.add(JTFnome);

            //----------------------
            JTFapelido= new JTextField();
            JTFapelido.setBounds(440, 50, 190, 25);
            background_edit.add(JTFapelido);
            //-------------------------       
            MaskFormatter maskFrg = new MaskFormatter();
            maskFrg = mascaras_RG();
            JTFrg = new JFormattedTextField(maskFrg);
            JTFrg.setBounds(60, 85, 90, 25);
            background_edit.add(JTFrg);
            //------------------------    
            MaskFormatter maskFcpf = new MaskFormatter();
            maskFcpf = mascaras_CPF();
            JTFcpf= new JFormattedTextField(maskFcpf);
            JTFcpf.setBounds(230, 85, 100, 25);
            background_edit.add(JTFcpf);
            //-------------------------           
            MaskFormatter maskFbloco_p = new MaskFormatter();
            maskFbloco_p = mascaras_Bloco_P();
            JTFbloco_prod= new JFormattedTextField(maskFbloco_p);
            JTFbloco_prod.setBounds(475, 85, 100, 25);
            background_edit.add(JTFbloco_prod);
            //------------------------            
            MaskFormatter maskFbloco_Telefone = new MaskFormatter();
            maskFbloco_Telefone = mascaras_Bloco_Telefone();
            JTFtelefone= new JFormattedTextField(maskFbloco_Telefone);
            JTFtelefone.setBounds(85, 155, 100, 25);
            background_edit.add(JTFtelefone);
            //-------------
        //combo box endereço
            JTFendereco = new JTextField();
            JTFendereco.setBounds(85, 120, 350, 25);
            background_edit.add(JTFendereco);
            //---------------
        //jbuton
            JBT_buscar_cont = new JButton();
            JBT_buscar_cont.setBounds(225, 10, 200, 25);
            JBT_buscar_cont.setText("Buscar Contribuinte");
            background_edit.add(JBT_buscar_cont);

            //--------------
            JBTconfirmar2 = new JButton();
            JBTconfirmar2.setBounds(90, 210, 150, 25);
            JBTconfirmar2.setText("Editar Cadastro");
            background_edit.add(JBTconfirmar2);
            //---------------
            JBTcancelar = new JButton();
            JBTcancelar.setBounds(260, 210, 150, 25);
            JBTcancelar.setText("Cancelar");
            background_edit.add(JBTcancelar);
            //----------------
            JBTlimpar = new JButton();
            JBTlimpar.setBounds(430, 210, 150, 25);
            JBTlimpar.setText("Limpar");
            background_edit.add(JBTlimpar);
            //-----------
            
            set_icone_cad();
            JBTconfirmar2.setEnabled(false);
            JBTcancelar.addActionListener(new evet_ActionListner());
            JBTlimpar.addActionListener(new evet_ActionListner());
            JBT_buscar_cont.addActionListener(new evet_ActionListner());
            JBTconfirmar2.addActionListener(new evet_ActionListnerEditar());
            
        JDialog_contribuinte.setVisible(true);
    }
    
    //MÉTODO DE EXCLUSÃO

    // máscaras
    public MaskFormatter mascaras_RG(){
        MaskFormatter mask_RG = null;
        try {
            mask_RG = new MaskFormatter("##########");
            mask_RG.setValidCharacters("1234567890");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mask_RG;
    }
     public MaskFormatter mascaras_CPF(){
        MaskFormatter mask_CPF = null;
        try {
            mask_CPF = new MaskFormatter("###.###.###-##");
            mask_CPF.setValidCharacters("1234567890");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mask_CPF;
    }
     public MaskFormatter mascaras_Bloco_P(){
        MaskFormatter mask_Bloco = null;
        try {
            mask_Bloco = new MaskFormatter("###/#######");
            mask_Bloco.setValidCharacters("1234567890");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mask_Bloco;
    }
      public MaskFormatter mascaras_Bloco_Telefone(){
        MaskFormatter mask_Bloco = null;
        try {
            mask_Bloco = new MaskFormatter("(##) ####-####");
            mask_Bloco.setValidCharacters("1234567890");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mask_Bloco;
    }
      //demais métodos 
      
      public void limpar_campos(){
          JTFbloco_prod.setText(null);
          JTFcpf.setText(null);
          JTFnome.setText(null);
          JTFapelido.setText(null);
          JTFrg.setText(null);
          JTFtelefone.setText(null);
          JTFendereco.setText(null);
          
      }
      public void limpar_labels(){
            JLnome.setText("Nome:");
            JLapelido.setText("Apelido:");
            JLrg.setText("RG:");
            JLcpf.setText("CPF:");
            JLbloco_prod.setText("Nº Bloco de Produtor:");
            JLendereco.setText("Endereço:");
            JLtelefone.setText("Telefone:");
      }
      
      
      //CLASSES DE EVENTOS PARA CONTRIBUINTE E OUTRAS INTERFACES
    private class evet_ActionListner implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            
            if(ob.equals(JBTcancelar)){
                JDialog_contribuinte.dispose();
            }
            if(ob.equals(JBTlimpar)){
                limpar_campos();
            }
            if(ob.equals(JBT_buscar_cont)){
                    cod_contribuinte = 0;
                    pesquisa ps = new pesquisa();
                    ps.interface_pesquisa_contribuinte();
                    cod_contribuinte = ps.get_cod_contri;
                    if(JBTconfirmar2!=null)
                        JBTconfirmar2.setEnabled(true);
                    ResultSet rs;
                    try {                     
                            rs = new consultas_db.SQL_contribuinte().SQL_pesquisa_contribuinte_completo(cod_contribuinte);
                            rs.next();
                    if(rs != null){                        
                        JTFnome.setText(rs.getString("nome"));
                        JTFbloco_prod.setText(rs.getString("n_bloco_produtor"));
                        JTFcpf.setText(rs.getString("cpf"));
                        JTFendereco.setText(rs.getString("endereco"));
                        JTFtelefone.setText(rs.getString("telefone"));
                        JTFrg.setText(rs.getString("rg"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(contribuinte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    private int cod_contribuinte;
    //CLASSE DE EVENTOS PARA CADASTRAR
    private class evet_ActionListnerContribuinte implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                if(!JTFnome.equals("")){
                    new consultas_db.SQL_contribuinte().SQL_insere_contribuinte_completo(JTFnome.getText(), JTFapelido.getText(), JTFcpf.getText(), JTFrg.getText(), JTFbloco_prod.getText(), JTFtelefone.getText(), JTFendereco.getText());
                    //JOptionPane.showMessageDialog(null, "\n"+JTFnome.getText()+"\n"+JTFapelido.getText()+"\n"+JTFcpf.getText()+"\n"+JTFrg.getText()+"\n"+JTFbloco_prod.getText()+"\n"+JTFtelefone.getText()+"\n"+JTFendereco.getText());
                }
                JDialog_contribuinte.dispose();
            }
            
    }
    }
    
    //CLASSE DE EVENTOS PARA EDITAR
    private class evet_ActionListnerEditar implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar2)){
                String nome = JTFnome.getText().toUpperCase(), apelido = JTFapelido.getText().toUpperCase()
                , cpf = JTFcpf.getText(), rg = JTFrg.getText(), n_bloco = JTFbloco_prod.getText(),
                telefone = JTFtelefone.getText(), endereco = JTFendereco.getText().toUpperCase();
                new consultas_db.SQL_contribuinte().SQL_altera_cad(nome, apelido, cpf, rg, n_bloco, telefone, endereco, cod_contribuinte);
                //JOptionPane.showMessageDialog(null, ""+nome+""+apelido+""+cpf+""+rg+""+n_bloco+""+telefone+""+endereco+""+cod_contribuinte);
                JDialog_contribuinte.dispose();
            }
        }
    }
    
   
}

