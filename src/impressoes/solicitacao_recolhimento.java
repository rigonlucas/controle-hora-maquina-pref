/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impressoes;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
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
public class solicitacao_recolhimento {
    public void solicita_doc_pdf(int cod_hora_maquina){

     String nome, cpf, rg, n_bloco, endereco, tel;
     Document document = new Document();
     String caminho = "PDF/solicitacao_recolhimento.pdf";
        try {
            SimpleDateFormat sdf_ano = new SimpleDateFormat("yyyy");  
            String ano = sdf_ano.format(new Date());
           
            
            
            retornCons = SQL_horas_efetuadas(cod_hora_maquina);
            retornCons.next();
            cod_hora_maquina = Integer.parseInt(retornCons.getString("cod_hora_maquina"));
            nome = retornCons.getString("nome");
            cpf = retornCons.getString("cpf");
            rg = retornCons.getString("rg");
            n_bloco  = retornCons.getString("n_bloco_produtor");
            endereco = retornCons.getString("endereco");
            tel = retornCons.getString("telefone");
            PdfWriter.getInstance(document, new FileOutputStream(caminho));
            document.open();
            Font f = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            Paragraph p_doc = new Paragraph("Documento: "+cod_hora_maquina+" /"+ano,f);
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
            p_secr.setSpacingAfter(20);
            document.add(p_secr);
            
                        
            Paragraph p_nome = new Paragraph("Nome: "+nome,f);
            p_nome.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_nome);
            
            Paragraph p_cpf = new Paragraph("CPF: "+cpf,f);
            p_cpf.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_cpf);
            
            Paragraph p_rg = new Paragraph("RG: "+rg,f);
            p_rg.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_rg);
            
            Paragraph p_bloco = new Paragraph("Bloco de Produtor: "+n_bloco,f);
            p_bloco.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_bloco);
            
            Paragraph p_end = new Paragraph("Endereço: "+endereco,f);
            p_end.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p_end);
            
            Paragraph p_tel = new Paragraph("Telefone: "+tel,f);
            p_tel.setAlignment(Element.ALIGN_JUSTIFIED);
            p_tel.setSpacingAfter(20);
            document.add(p_tel);
            
                        
            PdfPTable table = new PdfPTable(new float[] { 0.6f, 0.6f, 0.4f, 0.4f });
            
            PdfPCell header = new PdfPCell(new Paragraph("O contribuinte está autorizado a recolher aos cofres municipais a importância contida no documento presente.",f));
            header.setColspan(4);
            table.addCell(header);
            table.addCell("Horas Solicitadas");
            table.addCell("Serviço");
            table.addCell("Valor Hora");
            table.addCell("Valor Total");
            
            float quanti_hora_sol = Float.parseFloat(retornCons.getString("quantidade_horas")), valor = Float.parseFloat(retornCons.getString("valor")), valor_servico = Float.parseFloat(retornCons.getString("valor_servico"));
            
            table.addCell(""+quanti_hora_sol);
            table.addCell("Hora-Máquina trator");
            table.addCell(""+valor);
            table.addCell(""+valor_servico);
            table.setSpacingAfter(40);
            document.add(table);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm");  
            String data = sdf.format(new Date());
            Paragraph p_data_sol = new Paragraph("Itacurubi-RS "+data,f);
            p_data_sol.setAlignment(Element.ALIGN_CENTER);
            //p_data_sol.setSpacingAfter(120);
            document.add(p_data_sol);

            Paragraph p_total = new Paragraph("Total a Recolher: "+valor_servico,f);
            p_total.setAlignment(Element.ALIGN_CENTER);
            p_total.setSpacingAfter(50);
            document.add(p_total);
            
            Paragraph p_Assinatura_f = new Paragraph("____________________________________");
            p_Assinatura_f.setAlignment(Element.ALIGN_CENTER);
            //p_Assinatura_f.setSpacingAfter(20);
            document.add(p_Assinatura_f);
            
            Paragraph p_nome_f = new Paragraph("Responsável no Setor\n",f);
            p_nome_f.setAlignment(Element.ALIGN_CENTER);
            p_nome_f.setSpacingAfter(50);
            document.add(p_nome_f);
            
            
            Paragraph p_Assinatura_c = new Paragraph("____________________________________");
            p_Assinatura_c.setAlignment(Element.ALIGN_CENTER);
            //p_Assinatura_c.setSpacingAfter(20);
            document.add(p_Assinatura_c);
            
            Paragraph p_nome_c = new Paragraph("Contribuinte",f);
            p_nome_c.setAlignment(Element.ALIGN_CENTER);
            p_nome_c.setSpacingAfter(70);
            document.add(p_nome_c);            
            
           /* document.addSubject("Gerando PDF em Java"); 
            document.addKeywords("www.devmedia.com.br"); 
            document.addCreator("iText"); 
            document.addAuthor("Davi Gomes da Costa");
            * 
            *///assinatura do documento
            
            PdfPTable table_trib = new PdfPTable(new float[] { 1.0f, 1.2f});
            
            PdfPCell header_trib = new PdfPCell(new Paragraph("INFORMAÇÕES PARA EFETUAR O PAGAMENTO (USO EXCLUSIVO DO SETOR DE TRIBUTAÇÃO",f));
            header.setColspan(2);
            table_trib.addCell(header);
            table_trib.addCell("Recebimento:        /      /      ");
            table_trib.addCell("Número da DAM: ");
            table_trib.addCell("Observação Documento: ");
            table_trib.addCell("");

            table_trib.setSpacingAfter(40);
            document.add(table_trib);
            
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
            pComando = id_conexao.prepareStatement("SELECT cont.nome, cont.cpf, cont.rg, cont.n_bloco_produtor, cont.endereco, cont.telefone, hm.cod_hora_maquina, hm.quantidade_horas, hm.cod_tipo, hm.valor_servico, tp.valor FROM contribuinte cont, hora_maquina hm, tipo_hora tp WHERE cont.cod_contribuinte = hm.cod_contribuinte AND cod_hora_maquina = ? AND tp.cod_tipo = hm.cod_tipo");
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
            pComando = id_conexao.prepareStatement("SELECT hm.cod_hora_maquina, hm.quantidade_horas, hm.valor_servico FROM hora_maquina hm WHERE hm.cod_contribuinte = ? and pago = 'NÃO'");
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
