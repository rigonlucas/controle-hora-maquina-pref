/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impressoes;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import consultas_db.conectar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistema_agricultura.Principal;

/**
 *
 * @author usuario
 */
public class relatorio_troca_ano {
    public void relatorio_ano(){
        
     retornCons = SQL_listar();
     Document document = new Document();
    SimpleDateFormat sdf_ano = new SimpleDateFormat("yyyy");  
    String ano = sdf_ano.format(new Date());
    
    SimpleDateFormat sdf_mes = new SimpleDateFormat("MM");  
    String mes = sdf_mes.format(new Date());
    String semestre;
    int mes_i = Integer.parseInt(mes);
        if(mes_i<6){
            semestre = "1";
        }else {
            semestre = "2";
        }
    
     String caminho = "PDF/Relatorios_semestre/"+ano+"-"+semestre+".pdf";
     //String obs;

                //obs = JOptionPane.showInputDialog("Obervação: ");
            
        try {
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            PdfWriter.getInstance(document, new FileOutputStream(caminho));
            document.open();
            Image firgura = Image.getInstance("imagens/pref.png");
                    firgura.setAlignment(Element.ALIGN_CENTER);
                    document.add(firgura);

                    // adicionando um parágrafo no documento 
                    Paragraph p_pref = new Paragraph("Prefeitura Municipal de Itacurubi",f);
                    p_pref.setAlignment(Element.ALIGN_CENTER);
                    document.add(p_pref);


                    Paragraph p_secr = new Paragraph("Secretaria Municipal da Agricultura",f);
                    p_secr.setAlignment(Element.ALIGN_CENTER);
                    //p_secr.setSpacingAfter(20);
                    document.add(p_secr);

                    Paragraph p_ordem = new Paragraph("Relatório de Horas Executadas do ano de "+ano+"-"+semestre+"º Semestre",f);
                    p_ordem.setAlignment(Element.ALIGN_CENTER);
                    p_ordem.setSpacingAfter(20);
                    document.add(p_ordem);
                    
                    Paragraph p__2 = new Paragraph("_____________________________________________________________________________");
                    p__2.setAlignment(Element.ALIGN_JUSTIFIED);
                    //p__.setSpacingAfter(30);
                    document.add(p__2); 
                    
            while(retornCons.next()){                    

                    Paragraph p_nome = new Paragraph("Nome: "+retornCons.getString("nome"),f);
                    p_nome.setAlignment(Element.ALIGN_JUSTIFIED);
                    document.add(p_nome);

                    Paragraph p_cpf = new Paragraph("CPF: "+retornCons.getString("cpf"),f);
                    p_cpf.setAlignment(Element.ALIGN_JUSTIFIED);
                    document.add(p_cpf);

                    Paragraph p_pg = new Paragraph("Quantidade Horas Executadas: "+retornCons.getString("horas_executadas"),f);
                    p_pg.setAlignment(Element.ALIGN_JUSTIFIED);
                    document.add(p_pg);


                    Paragraph p_end = new Paragraph("Quantidade Horas Pagas: "+retornCons.getString("horas_pagas"),f);
                    p_end.setAlignment(Element.ALIGN_JUSTIFIED);
                    document.add(p_end);

                    Paragraph p__ = new Paragraph("_____________________________________________________________________________");
                    p__.setAlignment(Element.ALIGN_JUSTIFIED);
                    document.add(p__);                    
            }
            Paragraph p = new Paragraph("");
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            p.setSpacingAfter(40);
            document.add(p);                    
            
            Paragraph p_Assinatura_f = new Paragraph("____________________________________");
            p_Assinatura_f.setAlignment(Element.ALIGN_CENTER);
            //p_Assinatura_f.setSpacingAfter(20);
            document.add(p_Assinatura_f);
            
            Paragraph p_nome_f = new Paragraph("Secretário ou Assessor\n",f);
            p_nome_f.setAlignment(Element.ALIGN_CENTER);
            p_nome_f.setSpacingAfter(50);
            document.add(p_nome_f);
            try {
                java.awt.Desktop.getDesktop().open( new File( caminho ) );
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally{
            if(document!=null){
                document.close();
                JOptionPane.showMessageDialog(null, "Relatórios Lançados!");
            }
        }
    }
    
    
    conectar conec = new conectar();
    
    private Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    
    
    //---------------------------------
    public ResultSet SQL_listar(){
        id_conexao = conec.conectBase();
        retornCons = null;
        id_conexao = conec.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT* FROM contribuinte ORDER BY nome");         
            retornCons = pComando.executeQuery();
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro x\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
            
        }
    }
}
