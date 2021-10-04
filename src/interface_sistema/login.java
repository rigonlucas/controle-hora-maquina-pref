
package interface_sistema;

//import criptografia.criptografar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.*;
//import sun.security.provider.MD5;


public class login {
    public login(){
        log();
    }
    
    private JFrame JFrg;
    private JLabel JLBusu, JLBsenha;
    private JTextField JTFusu;
    private JPasswordField JPSsenha;
    private JButton JBTlogar, JBTsair;
    private void log(){
        JFrg = new JFrame();
        JFrg.setBounds(200, 200, 350, 180);
        JFrg.setLocationRelativeTo(null);
        JFrg.setLayout(null);
        JFrg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrg.setResizable(false);
        ImageIcon titulo = new ImageIcon("imagens/icones/login.png");
        JFrg.setIconImage(titulo.getImage());
        JFrg.setTitle("Login");
        
        ImageIcon imTitle2 = new ImageIcon("imagens/background/BG_Sistema_01.png");
        JLabel backgroud01 = new JLabel();
        backgroud01.setIcon(imTitle2);
        
        ImageIcon imTitle = new ImageIcon("imagens/background/LOCK.png");
        JLabel backgroud = new JLabel();
        backgroud.setIcon(imTitle);
        backgroud01.add(backgroud);

        backgroud01.setBounds(0, 0, 450, 400);
        backgroud.setBounds(-80, -30, 450, 400);
        JFrg.add(backgroud01);
        
        JLBusu = new JLabel("Usuário:");        
        JLBusu.setBounds(90, 60, 50, 25);
        backgroud.add(JLBusu);
        
        JLBusu = new JLabel("Senha:");        
        JLBusu.setBounds(95, 90, 50, 25);
        backgroud.add(JLBusu);
        
        JTFusu = new JTextField();
        JTFusu.setBounds(150, 60, 200, 25);
        backgroud.add(JTFusu);
        
        JPSsenha = new JPasswordField();
        JPSsenha.setBounds(150, 90, 200, 25);
        backgroud.add(JPSsenha);
        
        JBTlogar = new JButton("Logar");
        JBTlogar.setBounds(90, 130, 150, 25);
        backgroud.add(JBTlogar);
        
        ImageIcon OK = new ImageIcon("imagens/icones/ok.png");
        JBTlogar.setIcon(OK);
        
        JBTsair = new JButton("Cancelar");
        JBTsair.setBounds(255, 130, 150, 25);
        backgroud.add(JBTsair);
        
        ImageIcon sair = new ImageIcon("imagens/icones/remover.png");
        JBTsair.setIcon(sair);
        
        JBTlogar.addActionListener(new evt_log());
        JBTsair.addActionListener(new evt_log());
        JFrg.setVisible(true);
    }
    
    private class evt_log implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTlogar)){
               String senha = "2014", usu = "AGRICULTURA";
               String get_senha = JPSsenha.getText(), get_usu = JTFusu.getText();
                    if(get_senha.equals(senha)&&get_usu.equals(usu)){
                        new interface_sistema.main_interface();
                        JFrg.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
                        JPSsenha.setText(null);
                        JTFusu.setText(null);
                    }
            }
            if(ob.equals(JBTsair)){
                JFrg.dispose();
            }
        }
        
    }
}
