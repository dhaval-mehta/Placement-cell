/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfGenerator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dhaval
 */
public class PdfGenerator
{

    String fileName;

    public PdfGenerator(String fileName)
    {
	this.fileName = fileName;
    }

    /**
     * Creates a PDF with information about the movies
     *
     * @param filename the name of the PDF file that will be created.
     * @param detailsTable details of students
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(ArrayList<ArrayList<String>> detailsTable)
	    throws IOException, DocumentException
    {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(fileName));
	document.open();
	document.add(createTable(detailsTable));
	document.close();
    }

    private PdfPTable createTable(ArrayList<ArrayList<String>> detailsTable)
    {

	PdfPTable table = new PdfPTable(detailsTable.get(0).size());
	table.setWidthPercentage(100);

	List<String> headers = detailsTable.remove(0);

	for (String header : headers)
	{
	    table.addCell(header);
	}

	table.setHeaderRows(0);

	for (List<String> studentDetails : detailsTable)
	{
	    for (String studentDetail : studentDetails)
	    {
		PdfPCell cell = new PdfPCell(new Paragraph(studentDetail));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
	    }
	}
	return table;
    }

}
