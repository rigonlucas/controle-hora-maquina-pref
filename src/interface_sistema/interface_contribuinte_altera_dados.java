/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author usuario
 */
public class interface_contribuinte_altera_dados {
    
    private JDialog JDialog;
    private JButton JBTconfirmar, JBcancelar, JBbuscar_contri;
    private JLabel JLB_nome_contri, JLBcpf, JLB_pagas, JLB_exec;
    private JTextField JTFpg, JTFexec;
    
    private int cod_contri;
    public void interface_altera_valor(){
        JDialog = new JDialog();
        JDialog.setBounds(200, 200, 470, 250);
        JDialog.setLocationRelativeTo(null);
        JDialog.setLayout(null);
        JDialog.setModal(true);
        JDialog.setResizable(true);
        ImageIcon titulo = new ImageIcon("imagens/icones/edit.png");
        JDialog.setIconImage(titulo.getImage());
        JDialog.setTitle("Alterar Horas Efetuadas/Pagas de Contruibuintes");
        
        ImageIcon imTitle = new ImageIcon("imagens/background/BG_Sistema_03.png");
        JLabel backgroud_cad = new JLabel();
        backgroud_cad.setIcon(imTitle);
        backgroud_cad.setBounds(0, 0, 470, 250);
        JDialog.add(backgroud_cad);
        
        JBbuscar_contri = new JButton("Buscar Contribuinte");
        ImageIcon busc = new ImageIcon("imagens/icones/btn-buscar.png");
        JBbuscar_contri.setIcon(busc);
        JBbuscar_contri.setBounds(120, 20, 200, 25);
        backgroud_cad.add(JBbuscar_contri);
        
        JLB_nome_contri = new JLabel("Contribuinte: ");
        JLB_nome_contri.setBounds(30, 50, 400, 25);
        backgroud_cad.add(JLB_nome_contri);
        
        JLBcpf = new JLabel("CPF: ");
        JLBcpf.setBounds(75, 75, 300, 25);
        backgroud_cad.add(JLBcpf);
        
        JLB_pagas = new JLabel("Horas Pagas: ");
        JLB_pagas.setBounds(60, 100, 90, 25);
        backgroud_cad.add(JLB_pagas);
        
        JLB_exec = new JLabel("Horas Executadas: ");
        JLB_exec.setBounds(30, 135, 120, 25);
        backgroud_cad.add(JLB_exec);
        
        JTFpg = new JTextField();
        JTFpg.setBounds(150, 100, 50, 25);
        backgroud_cad.add(JTFpg);
        
        JTFexec = new JTextField();
        JTFexec.setBounds(150, 135, 50, 25);
        
        backgroud_cad.add(JTFexec);
        
        JBTconfirmar = new JButton("Confirmar");
        ImageIcon ok = new ImageIcon("imagens/icones/ok.png");
        JBTconfirmar.setIcon(ok);
        JBTconfirmar.setBounds(20, 170, 200, 25);
        backgroud_cad.add(JBTconfirmar);
        
        JBcancelar = new JButton("Cancelar");
        ImageIcon cancelar = new ImageIcon("imagens/icones/cancelar.gif");
        JBcancelar.setIcon(cancelar);
        JBcancelar.setBounds(230, 170, 200, 25);
        backgroud_cad.add(JBcancelar);
        
        JBTconfirmar.setEnabled(false);
        
        JBTconfirmar.addActionListener(new evt_altera_horas());
        JBbuscar_contri.addActionListener(new evt_altera_horas());
        JBcancelar.addActionListener(new evt_altera_horas());
        
        JDialog.setVisible(true);
    }
    //mascara valor  
               
    private String nome, cpf;
    private class evt_altera_horas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            if(ob.equals(JBTconfirmar)){
                float exec,pg;
                exec = Float.parseFloat(JTFexec.getText().replace(",", "."));
                pg = Float.parseFloat(JTFpg.getText().replace(",", "."));                
                new consultas_db.SQL_contribuinte().SQL_altera_horas(cod_contri, pg, exec);
                //JOptionPane.showMessageDialog(null, cod_contri+"\n"+nome+"\n"+cpf+"\n"+exec+"\n"+pg);                
            }
            if(ob.equals(JBbuscar_contri)){
                cod_contri = 0;
                pesquisa ps = new pesquisa();
                ps.interface_pesquisa_contribuinte();
                cod_contri = ps.get_cod_contri;
                ResultSet rs = new consultas_db.SQL_contribuinte().SQL_pesquisa_contribuinte_completo(cod_contri);
                try {
                    if(JBTconfirmar!=null)
                        JBTconfirmar.setEnabled(true);
                    rs.next();
                    if(JLB_nome_contri!=null){
                        nome= rs.getString("nome");
                        JLB_nome_contri.setText("Contribuinte: "+nome);
                    }
                    if(JLBcpf!=null){
                        cpf = rs.getString("cpf");
                        JLBcpf.setText("CPF: "+cpf);
                    }
                    if(JTFexec != null){
                        JTFexec.setText(rs.getString("horas_executadas").replace(".", ","));
                    }
                    if(JTFpg != null){
                        JTFpg.setText(rs.getString("horas_pagas").replace(".", ","));                        
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro :(");
                }
            }
            if(ob.equals(JBcancelar)){
                JDialog.dispose();
            }
        }
        
    }
   
}
