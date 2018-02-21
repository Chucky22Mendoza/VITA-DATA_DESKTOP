/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import java.io.FileOutputStream;

/**
 *
 * @author mendo
 */
public class GenerarPDF {
    private Font fuenteBold = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
    private Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 18, Font.NORMAL);
    private Font fuenteItalic = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
    
    public void generarPDF(String header, String info, String footer, String img, String salida){
        try {
            Document doc = new Document(PageSize.A4, 50, 50, 36, 36);
            PdfWriter.getInstance(doc, new FileOutputStream(salida));
            doc.open();
            doc.add(getHeader(header));            
            Image imagen = Image.getInstance(img);
            imagen.scaleAbsolute(50,50);
            imagen.setAlignment(Element.ALIGN_CENTER);
            doc.add(imagen);
            doc.add(getInfo(info));
            doc.add(getInfo(" "));
            doc.add(getInfo(" "));
            doc.add(getInfo(""));
            doc.add(getFooter(footer));
            doc.close();
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
    }
    
    private Paragraph getHeader(String text){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(text);
        c.setFont(fuenteBold);
        p.add(c);
        return p;
    }
    
    private Paragraph getInfo(String text){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        c.append(text);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    
    private Paragraph getFooter(String text){
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(text);
        c.setFont(fuenteItalic);
        p.add(c);
        return p;
    }
}
