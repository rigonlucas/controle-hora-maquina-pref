package interface_sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class tipo_hora {
private JDialog JDialog_tipo_hora;
    //Labels fixos
        private JLabel JLtipo;
        private JLabel JLvalor;
       
    //textfield
        private JTextField JTFtipo;
        private JFormattedTextField JTFvalor;

     //buttons
         private JButton JBTconfirmar;
         private JButton JBTcancelar;
         private JButton JBTbuscar;
    //VARIÁVEIS DE CONTROLE DA INTERFACE (POSIÇÃO)
         //private int x1 = 10;
         //private int y1 = 10;
         
         //private int x2 = 100;
         
         private int tLBtamanho_tipo_h = 80;
    // Método cadastrar
    public void interface_cadastrar (){
        JDialog_tipo_hora = new JDialog();
        JDialog_tipo_hora.setBounds(200, 200, 390, 170);
        JDialog_tipo_hora.setLocationRelativeTo(null);
        JDialog_tipo_hora.setLayout(null);
        JDialog_tipo_hora.setResizable(true);
        JDialog_tipo_hora.setModal(true);
        JDialog_tipo_hora.setTitle("Cadastro de Tipo de Hora");
        //JFrame_contribuinte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setar_componentes (LABELS)
            JLtipo = new JLabel();
            JLtipo.setBounds(10, 10, tLBtamanho_tipo_h, 25);
            JLtipo.setText("Tipo de Hora:");
            JDialog_tipo_hora.add(JLtipo);
            //----------------
            
            JLvalor = new JLabel();
            JLvalor.setBounds(50, 45, 45, 25);
            JLvalor.setText("Valor:");
            JDialog_tipo_hora.add(JLvalor);
           
        //setar_componentes TEXT
            JTFtipo = new JTextField();
            JTFtipo.setBounds(100, 10, 270, 25);
            JDialog_tipo_hora.add(JTFtipo);

            //----------------------
            MaskFormatter maskFvalor = new MaskFormatter();
            maskFvalor = mascaras_Valor();
            JTFvalor= new JFormattedTextField(maskFvalor);
            JTFvalor.setBounds(100, 40, 60, 25);
            JDialog_tipo_hora.add(JTFvalor);
            
        //butons
            JBTconfirmar = new JButton();
            JBTconfirmar.setBounds(30, 80, 150, 25);
            JBTconfirmar.setText("Cadastrar");
            JDialog_tipo_hora.add(JBTconfirmar);
            //---------------
            JBTcancelar = new JButton();
            JBTcancelar.setBounds(200, 80, 150, 25);
            JBTcancelar.setText("Cancelar");
            JDialog_tipo_hora.add(JBTcancelar);
            //----------------
            
            JBTcancelar.addActionListener(new evt_tipo_hora());
            JBTconfirmar.addActionListener(new evt_tipo_hora_cadastrar());
            
        JDialog_tipo_hora.setVisible(true);
    }
    //método editar
    
    public void interface_editar (){
        JDialog_tipo_hora = new JDialog();
        JDialog_tipo_hora.setBounds(200, 200, 390, 210);
        JDialog_tipo_hora.setLocationRelativeTo(null);
        JDialog_tipo_hora.setLayout(null);
        JDialog_tipo_hora.setModal(true);
        JDialog_tipo_hora.setResizable(true);
        JDialog_tipo_hora.setTitle("Editar Tipo de Hora");
        //JFrame_contribuinte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setar_componentes (LABELS)
            JLtipo = new JLabel();
            JLtipo.setBounds(10, 50, tLBtamanho_tipo_h, 25);
            JLtipo.setText("Tipo de Hora:");
            JDialog_tipo_hora.add(JLtipo);
            //----------------
            
            JLvalor = new JLabel();
            JLvalor.setBounds(50, 85, 45, 25);
            JLvalor.setText("Valor:");
            JDialog_tipo_hora.add(JLvalor);
           
        //setar_componentes TEXT
            JTFtipo = new JTextField();
            JTFtipo.setBounds(100, 50, 270, 25);
            JDialog_tipo_hora.add(JTFtipo);

            //----------------------
            MaskFormatter maskFvalor = new MaskFormatter();
            maskFvalor = mascaras_Valor();
            JTFvalor= new JFormattedTextField(maskFvalor);
            JTFvalor.setBounds(100, 85, 60, 25);
            JDialog_tipo_hora.add(JTFvalor);

            
        //butons
            JBTconfirmar = new JButton();
            JBTconfirmar.setBounds(30, 120, 150, 25);
            JBTconfirmar.setText("Editar Cadastro");
            JDialog_tipo_hora.add(JBTconfirmar);
            //---------------
            JBTcancelar = new JButton();
            JBTcancelar.setBounds(200, 120, 150, 25);
            JBTcancelar.setText("Cancelar");
            JDialog_tipo_hora.add(JBTcancelar);
            //----------------
            JBTbuscar = new JButton();
            JBTbuscar.setBounds(120, 10, 150, 25);
            JBTbuscar.setText("Buscar");
            JDialog_tipo_hora.add(JBTbuscar);
            //----------------
            
            JBTbuscar.addActionListener(new evt_tipo_hora());
            JBTcancelar.addActionListener(new evt_tipo_hora());
            JBTconfirmar.addActionListener(new evt_tipo_hora_editar());
            
        JDialog_tipo_hora.setVisible(true);
    }
    //método de exclusão
    public void interface_excluir (){
        JDialog_tipo_hora = new JDialog();
        JDialog_tipo_hora.setBounds(200, 200, 390, 210);
        JDialog_tipo_hora.setLocationRelativeTo(null);
        JDialog_tipo_hora.setLayout(null);
        JDialog_tipo_hora.setResizable(true);
        JDialog_tipo_hora.setModal(true);
        JDialog_tipo_hora.setTitle("Excluir Tipo de Hora");
        //JFrame_contribuinte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setar_componentes (LABELS)
            JLtipo = new JLabel();
            JLtipo.setBounds(10, 50, tLBtamanho_tipo_h, 25);
            JLtipo.setText("Tipo de Hora:");
            JDialog_tipo_hora.add(JLtipo);
            //----------------
            
            JLvalor = new JLabel();
            JLvalor.setBounds(50, 85, 45, 25);
            JLvalor.setText("Valor:");
            JDialog_tipo_hora.add(JLvalor);
           
        
        //butons
            JBTconfirmar = new JButton();
            JBTconfirmar.setBounds(30, 120, 150, 25);
            JBTconfirmar.setText("Excluir Cadastro");
            JDialog_tipo_hora.add(JBTconfirmar);
            //---------------
            JBTcancelar = new JButton();
            JBTcancelar.setBounds(200, 120, 150, 25);
            JBTcancelar.setText("Cancelar");
            JDialog_tipo_hora.add(JBTcancelar);
            //----------------
            JBTbuscar = new JButton();
            JBTbuscar.setBounds(120, 10, 150, 25);
            JBTbuscar.setText("Buscar");
            JDialog_tipo_hora.add(JBTbuscar);
            //----------------
            JBTbuscar.addActionListener(new evt_tipo_hora());
            JBTcancelar.addActionListener(new evt_tipo_hora());
            JBTconfirmar.addActionListener(new evt_tipo_hora_excluir());
        JDialog_tipo_hora.setVisible(true);
    }
    public void interface_mostrar (){
        JDialog_tipo_hora = new JDialog();
        JDialog_tipo_hora.setBounds(200, 200, 390, 210);
        JDialog_tipo_hora.setLocationRelativeTo(null);
        JDialog_tipo_hora.setLayout(null);
        JDialog_tipo_hora.setResizable(true);
        JDialog_tipo_hora.setModal(true);
        JDialog_tipo_hora.setTitle("Mostrar Dados do Tipo de Hora");
        //JFrame_contribuinte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setar_componentes (LABELS)
            JLtipo = new JLabel();
            JLtipo.setBounds(10, 50, tLBtamanho_tipo_h, 25);
            JLtipo.setText("Tipo de Hora:");
            JDialog_tipo_hora.add(JLtipo);
            //----------------
            
            JLvalor = new JLabel();
            JLvalor.setBounds(50, 85, 45, 25);
            JLvalor.setText("Valor:");
            JDialog_tipo_hora.add(JLvalor);
                      
        //butons
            //---------------
            JBTcancelar = new JButton();
            JBTcancelar.setBounds(120, 120, 150, 25);
            JBTcancelar.setText("Cancelar");
            JDialog_tipo_hora.add(JBTcancelar);
            //----------------
            JBTbuscar = new JButton();
            JBTbuscar.setBounds(120, 10, 150, 25);
            JBTbuscar.setText("Buscar");
            JDialog_tipo_hora.add(JBTbuscar);
            //----------------
            JBTbuscar.addActionListener(new evt_tipo_hora());
            JBTcancelar.addActionListener(new evt_tipo_hora());
            
        JDialog_tipo_hora.setVisible(true);
    }    
    
    //mascara valor
    public MaskFormatter mascaras_Valor(){
        MaskFormatter mask_CPF = null;
        try {
            mask_CPF = new MaskFormatter("##,##");
            mask_CPF.setValidCharacters("1234567890");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mask_CPF;
    }
    
    //CLASSES DE EVENTOS
    
    //classe de evento genérica
    private class evt_tipo_hora implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTbuscar)){
                pesquisa ips = new pesquisa();
                ips.interface_pesquisa_tipo_horas();
            }
            if(ob.equals(JBTcancelar)){
                JDialog_tipo_hora.hide();
            }
        }
        
    }
    //classe de evento cadastrar
    private class evt_tipo_hora_cadastrar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                JDialog_tipo_hora.hide();
            }
           
        }
        
    }
    //classe de evento editar
    private class evt_tipo_hora_editar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                JDialog_tipo_hora.hide();
            }
           
        }
        
    }
    //classe de evento excluir
    private class evt_tipo_hora_excluir implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                JDialog_tipo_hora.hide();
            }
           
        }
        
    }
}
