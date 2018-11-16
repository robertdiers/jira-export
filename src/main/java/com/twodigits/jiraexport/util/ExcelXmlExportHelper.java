package com.twodigits.jiraexport.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Excel export helper
 * @author robert.diers
 */
public class ExcelXmlExportHelper {
	
	public static String line_separator = System.getProperty("line.separator");
	public static int maxcolorcount = 2;
	
	//2009-04-20T00:00:00.000
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	/**
	 * Excel XML header
	 * @param buf
	 * @param worksheetcount
	 */
	public static void writeXmlHeader(StringWriter buf, int worksheetcount) {
		buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    	buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("<?mso-application progid=\"Excel.Sheet\"?>");
    	buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
    	buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
    	buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
    	buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
    	buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
    	buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("  <ActiveSheet>"+worksheetcount+"</ActiveSheet>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" </ExcelWorkbook>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" <Styles>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("  <Style ss:ID=\"Default\" ss:Name=\"Normal\">");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("   <Alignment ss:Vertical=\"Bottom\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("   <Borders/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("   <Interior/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("   <NumberFormat/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("   <Protection/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sHeader\">");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);		         
	    buf.append("   <Interior ss:Color=\"#A6A6A6\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);			    
	    buf.append("   <Font ss:Bold=\"1\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  <Style ss:ID=\"sGreyInterior\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Interior ss:Color=\"#D9D9D9\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  <Style ss:ID=\"sBLACK\">");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Font ss:Color=\"#000000\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sGreyIntBLACK\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Interior ss:Color=\"#D9D9D9\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Font ss:Color=\"#000000\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  </Style>");	    	
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sRED\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Font ss:Color=\"#9C0006\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sGreyIntRED\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Interior ss:Color=\"#D9D9D9\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Font ss:Color=\"#9C0006\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  <Style ss:ID=\"sGREEN\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	    
	    buf.append("   <Font ss:Color=\"#006100\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sGreyIntGREEN\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Interior ss:Color=\"#D9D9D9\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <Font ss:Color=\"#006100\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("  <Style ss:ID=\"sDateBLACK\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <NumberFormat ss:Format=\"dd/mm/yyyy\\ hh:mm:ss;@\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Font ss:Color=\"#000000\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sDateRED\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <NumberFormat ss:Format=\"dd/mm/yyyy\\ hh:mm:ss;@\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Font ss:Color=\"#9C0006\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sDateGREEN\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <NumberFormat ss:Format=\"dd/mm/yyyy\\ hh:mm:ss;@\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Font ss:Color=\"#006100\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sDateGreyIntBLACK\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <NumberFormat ss:Format=\"dd/mm/yyyy\\ hh:mm:ss;@\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Interior ss:Color=\"#D9D9D9\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Font ss:Color=\"#000000\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sDateGreyIntRED\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <NumberFormat ss:Format=\"dd/mm/yyyy\\ hh:mm:ss;@\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Interior ss:Color=\"#D9D9D9\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Font ss:Color=\"#9C0006\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  <Style ss:ID=\"sDateGreyIntGREEN\">");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	    buf.append("   <NumberFormat ss:Format=\"dd/mm/yyyy\\ hh:mm:ss;@\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Interior ss:Color=\"#D9D9D9\" ss:Pattern=\"Solid\"/>");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("   <Font ss:Color=\"#006100\" />");
	    buf.write(ExcelXmlExportHelper.line_separator);
	    buf.append("  </Style>");
	    buf.write(ExcelXmlExportHelper.line_separator);
    	buf.append(" </Styles>");
	    buf.write(ExcelXmlExportHelper.line_separator);	
	}
	
	/**
	 * Excel XML footer
	 * @param buf
	 * @param columncount
	 */
	public static void writeXmlFooter(StringWriter buf) { 
    	buf.append("</Workbook>");
    	buf.write(ExcelXmlExportHelper.line_separator);
	}
	
	/**
	 * Excel XML-Worksheet Header
	 * @param buf
	 * @param sheetname
	 * @param headlines
	 * @param rowcount
	 */
	public static void writeXmlWorksheetHeader(StringWriter buf, String sheetname, List<String> headlines, int maxrows) {
		buf.append(" <Worksheet ss:Name=\""+sheetname+"\">");
    	buf.write(ExcelXmlExportHelper.line_separator);    	    	
    	buf.append("  <Table ss:ExpandedColumnCount=\""+headlines.size()+"\" ss:ExpandedRowCount=\""+(maxrows+1)+"\" x:FullColumns=\"1\" x:FullRows=\"1\" ss:DefaultRowHeight=\"15\">");
    	buf.write(ExcelXmlExportHelper.line_separator);
		//width
    	for (String headline : headlines) {
			double width = 5.25 * (headline.length()+2) + 25; //added filter dropdown
	    	buf.append("   <Column ss:Width=\""+width+"\"/>");  	
	        buf.write(ExcelXmlExportHelper.line_separator);
		}
    	//meta
		buf.append("   <Row ss:AutoFitHeight=\"0\" ss:StyleID=\"sHeader\">");
        buf.write(ExcelXmlExportHelper.line_separator);
        //column names
        for (String headline : headlines) {
        	buf.append("    <Cell><Data ss:Type=\"String\">"+headline+"</Data></Cell>");    	        	
            buf.write(ExcelXmlExportHelper.line_separator);
		}
        //meta
        buf.append("   </Row>");
        buf.write(ExcelXmlExportHelper.line_separator);
	}
	
	/**
	 * Excel XML-Worksheet Footer
	 * @param buf
	 * @param headlinescount
	 */
	public static void writeXmlWorksheetFooter(StringWriter buf, int headlinescount) {
		buf.append("  </Table>");
        buf.write(ExcelXmlExportHelper.line_separator);
        buf.append("  <AutoFilter x:Range=\"R1C1:R1C"+headlinescount+"\" xmlns=\"urn:schemas-microsoft-com:office:excel\">");
        buf.write(ExcelXmlExportHelper.line_separator);
        buf.append("  </AutoFilter>");
        buf.write(ExcelXmlExportHelper.line_separator);
		buf.append(" </Worksheet>");
    	buf.write(ExcelXmlExportHelper.line_separator);
	}
	
	/**
	 * Excel XML Row header
	 * @param buf
	 * @param colorcount
	 */
	public static void writeXmlRowHeader(StringWriter buf) {
		buf.append("   <Row ss:AutoFitHeight=\"0\">");
    	buf.write(ExcelXmlExportHelper.line_separator);
	}
	
	/**
	 * Excel XML Row footer
	 * @param buf
	 * @param colorcount
	 */
	public static void writeXmlRowFooter(StringWriter buf) {
		buf.append("   </Row>");
    	buf.write(ExcelXmlExportHelper.line_separator);
	}
	
	/**
	 * Excel XML Row Cell
	 * @param buf
	 * @param type
	 * @param data
	 * @param colorcount (0,1)
	 * @param fontcolor (black, red, green)
	 */
	public static void writeXmlRowCell(StringWriter buf, String type, Object data, int actual_rowcount, int fontcolor) {
		StringBuffer style = new StringBuffer();
		style.append("s");
		if (type.equals("DateTime")) {		
			style.append("Date");			
		}
		if (actual_rowcount % 2 == 0) {
			style.append("GreyInt");
		}
		style.append(getColor(fontcolor).toUpperCase());
		if (data == null) data = "";
		buf.append("    <Cell ss:StyleID=\""+style.toString()+"\"><Data ss:Type=\""+type+"\">"+escapeSpecialCharactersXls(data.toString())+"</Data></Cell>");
		buf.write(ExcelXmlExportHelper.line_separator);
	}
	
	/**
	 * get color for XLS
	 * @return
	 */
	public static String getColor(int compvalue) {
		try {
			return getColorWithThrows(compvalue);
		} catch (Exception e) {
			e.printStackTrace();
			return "black"; //use black as default
		}
	}
	
	/**
	 * get color for GUI
	 * @return
	 * @throws ValidationException 
	 */
	public static String getColorWithThrows(int compvalue) throws Exception {
		//after adding colors you also have to add more styles in ExcelXmlExportHelper
		if (compvalue == -1) return "black";
		if (compvalue == 0) return "red";
		if (compvalue == 1) return "green";		
		throw new Exception("unknown color in Constants.getColor(...)");
	}
	
	/**
	 * Excel XML Row Cell
	 * @param buf
	 * @param type
	 * @param data
	 * @param colorcount (0,1)	 
	 */
	public static void writeXmlRowCell(StringWriter buf, String type, Object data, int actual_rowcount) {
		writeXmlRowCell(buf, type, data, actual_rowcount, -1);
	}
	
	/**
     * XML has got predefined signs
     * @return
     */
    public static String escapeSpecialCharacters(String input) {
    	/*
	    	"   &quot;
			'   &apos;
			<   &lt;
			>   &gt;
			&   &amp;
    	*/
    	input = input.replaceAll("&", "&amp;");
    	input = input.replaceAll("\"", "&quot;");
    	input = input.replaceAll("'", "&apos;");
    	input = input.replaceAll("<", "&lt;");
    	input = input.replaceAll(">", "&gt;");
    	return input;
    }
	
	/**
     * Excel XML is not accepting all signs
     * @return
     */
    public static String escapeSpecialCharactersXls(String input) {
    	input = escapeSpecialCharacters(input);
    	//XML cannot use German special signs
    	input = input.replaceAll("ü", "&uuml;");
    	input = input.replaceAll("Ü", "&Uuml;");
    	input = input.replaceAll("ö", "&ouml;");
    	input = input.replaceAll("Ö", "&Ouml;");
    	input = input.replaceAll("ä", "&auml;");
    	input = input.replaceAll("Ä", "&Auml;");
    	input = input.replaceAll("ß", "&szlig;");	
    	return input;
    }
    
    /**
     * format Date output for XML
     * @param millis
     * @return
     */
    public static String formatDate(long millis) {    	
    	return sdf.format(new java.util.Date(millis)) + ".000";
    }

    /**
     * format Date output for XML
     * @param millis
     * @return
     */
	public static String formatDate(Date date_value) {
		if (date_value == null) return "";		
		String result = sdf.format(date_value) + ".000";
		//0001-01-01
		if (result.startsWith("0001-01-01")) return "";
		return result;
	}

}
