package br.unipe.mlp.banco.conta.dados;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.Conta;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GerarPDF {
	private Banco banco;
	private Document doc = null;
	private FileOutputStream os = null;
	
	public GerarPDF (ArrayList<Conta>contasLista) throws DocumentException, IOException{
		try {
			banco = new Banco();
			doc = new Document(PageSize.A4);
			os = new FileOutputStream("C:/Users/A.Jr/Desktop/Contas.pdf");
			
			PdfWriter.getInstance(doc,os) ;

			doc.open();	

            doc.newPage();      
            
            doc.add(new Paragraph(banco.listar()));
		
		
        }finally{
        	if (doc != null)
				doc.close();
        	if (os != null)
				os.close();
        }
		

	
	}
}
