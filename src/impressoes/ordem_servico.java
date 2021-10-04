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
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class ordem_servico {
    public void ordem_servico_doc_pdf(int cod_hora){
        
     retornCons = SQL_horas_efetuadas(cod_hora);
     Document document = new Document();
     String caminho = "PDF/ordem_servico.pdf";
     //String obs;

                //obs = JOptionPane.showInputDialog("Obervação: ");
            
        try {
            retornCons.next();
            PdfWriter.getInstance(document, new FileOutputStream(caminho));
            document.open();
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            
            SimpleDateFormat sdf_ano = new SimpleDateFormat("yyyy");  
            String ano = sdf_ano.format(new Date());
            int doc = 1;
            Paragraph p_doc = new Paragraph("Serviço Número: "+cod_hora+" /"+ano,f);
            p_doc.setAlignment(Element.ALIGN_RIGHT);
            document.add(p_doc);
            
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
            
            Paragraph p_ordem = new Paragraph("Ordem de Serviço",f);
            p_ordem.setAlignment(Element.ALIGN_CENTER);
            p_ordem.setSpacingAfter(20);
            document.add(p_ordem);
            
                        
            Paragraph p_nome = new Paragraph("Nome: "+retornCons.getString("nome"),f);
            p_nome.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_nome);
            
            Paragraph p_n_dam = new Paragraph("DAM: "+retornCons.getString("n_dam"),f);
            p_n_dam.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_n_dam);
            
            Paragraph p_pg = new Paragraph("Data de Pagamento: "+retornCons.getString("data_pagamento"),f);
            p_pg.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_pg);
            
                        
            Paragraph p_end = new Paragraph("Endereço: "+retornCons.getString("endereco"),f);
            p_end.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_end);
            
            Paragraph p_tel = new Paragraph("Telefone: "+retornCons.getString("telefone"),f);
            p_tel.setAlignment(Element.ALIGN_JUSTIFIED);
            p_tel.setSpacingAfter(30);
            document.add(p_tel);            
                       
                        
            PdfPTable table = new PdfPTable(new float[] { 1.6f, 1.0f, 1.6f, 1.6f });
            
            PdfPCell header = new PdfPCell(new Paragraph("Serviço Solicitado pelo Contribuinte",f));
            header.setColspan(1);
            table.addCell(header);
            table.addCell("Horas Pagas");
            table.addCell("Horas Executadas");
            table.addCell("Data da Execução");
            table.addCell("Observações");            
            
            
            table.addCell(""+retornCons.getString("quantidade_horas"));
            table.addCell("");
            table.addCell("         /          /          ");
            table.addCell("");
            table.addCell("");
            
            table.setSpacingAfter(20);
            document.add(table);
            
            String TIPO = retornCons.getString("tipo_servico");
            
            if(TIPO == null){
                TIPO = "Não Informado pelo Produtor";
            }                
            
            Paragraph p_TIPO = new Paragraph("Serviço Solicitado: "+TIPO,f);
            p_TIPO.setAlignment(Element.ALIGN_JUSTIFIED);
            //p_TIPO.setSpacingAfter(30);
            document.add(p_TIPO);
            
            Paragraph p_obs = new Paragraph("Observação:_____________________________",f);
            p_obs.setAlignment(Element.ALIGN_JUSTIFIED);
            //p_obs.setSpacingAfter(30);
            document.add(p_obs);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm");  
            String data = sdf.format(new Date());
            Paragraph p_data_sol = new Paragraph("Itacurubi-RS "+data,f);
            p_data_sol.setAlignment(Element.ALIGN_CENTER);
            p_data_sol.setSpacingAfter(20);
            document.add(p_data_sol);
            
            Paragraph p_auto = new Paragraph("Em caso de exceder o número de horas pagas na execução do serviço, autorizo o lançamento das horas excedidas junto ao setor de tributação, com presente assinaturas de responsáveis pelo serviço",f);
            p_auto.setAlignment(Element.ALIGN_LEFT);
            p_auto.setSpacingAfter(40);
            document.add(p_auto);
            
            
            
            Paragraph p_Assinatura_f = new Paragraph("____________________________________           ____________________________________");
            p_Assinatura_f.setAlignment(Element.ALIGN_CENTER);
            //p_Assinatura_f.setSpacingAfter(20);
            document.add(p_Assinatura_f);
            
            Paragraph p_nome_f = new Paragraph("        Operador Responsável pelo Serviço                                                        Contribuinte",f);
            p_nome_f.setAlignment(Element.ALIGN_LEFT);
            p_nome_f.setSpacingAfter(40);
            document.add(p_nome_f);
            
            Paragraph p_Assinatura_c = new Paragraph("____________________________________");
            p_Assinatura_c.setAlignment(Element.ALIGN_CENTER);
            //p_Assinatura_f.setSpacingAfter(20);
            document.add(p_Assinatura_c);
            
            Paragraph p_nome_c = new Paragraph("Secretário ou Assessor Responsável",f);
            p_nome_c.setAlignment(Element.ALIGN_CENTER);
            p_nome_c.setSpacingAfter(50);
            document.add(p_nome_c);
            
            Paragraph p_Assinatura_receb = new Paragraph("____________________________________");
            p_Assinatura_receb.setAlignment(Element.ALIGN_CENTER);
            //p_Assinatura_f.setSpacingAfter(20);
            document.add(p_Assinatura_receb);
            
            Paragraph p_nome_receb = new Paragraph("Setor de Tributação",f);
            p_nome_receb.setAlignment(Element.ALIGN_CENTER);
            //p_nome_c.setSpacingAfter(50);
            document.add(p_nome_receb);
            
            Paragraph p_nome_dt_recb = new Paragraph("Data de Recebimento: ________/________/_________",f);
            p_nome_dt_recb.setAlignment(Element.ALIGN_CENTER);
            //p_nome_c.setSpacingAfter(50);
            document.add(p_nome_dt_recb);
            
                       
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally{
            if(document!=null){
                document.close();
            }
        }
    }
    
    
    conectar conec = new conectar();
    
    private Connection id_conexao;
    private PreparedStatement pComando;
    private ResultSet retornCons;
    
    public ResultSet SQL_horas_efetuadas(int cod_hora){
        id_conexao = conec.conectBase();
        retornCons = null;
        id_conexao = conec.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT cont.nome, cont.endereco, cont.telefone, hm.* FROM contribuinte cont, hora_maquina hm WHERE cont.cod_contribuinte = hm.cod_contribuinte and hm.cod_hora_maquina = ? and hm.executado = 'NÃO'");
            pComando.setInt(1, cod_hora);
         
            retornCons = pComando.executeQuery();
            return retornCons;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro x\n"+ex.getMessage());
            return null;
        }finally{
            conec.desconetBase();
            
        }
    }
    //---------------------------------
    public ResultSet SQL_listar_doc(int cod){
        id_conexao = conec.conectBase();
        retornCons = null;
        id_conexao = conec.conectBase();
        try {
            pComando = id_conexao.prepareStatement("SELECT hm.cod_hora_maquina, hm.quantidade_horas, hm.valor_servico FROM hora_maquina hm WHERE hm.cod_contribuinte = ?");
            pComando.setInt(1, cod);
         
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
