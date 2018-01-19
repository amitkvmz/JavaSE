
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class XLS {

    public static Cell dt;
    public static int Counter20;
    public static int Counter11;
    public static int unknownC;
    public static String unknown;
    public static int rowIndex = 1;
    public static String output;

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, InvalidFormatException {

        System.out.println("Please write the path for input file with extenstion , Use '\\' for directory change." );
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println("Enter path for output file with extension, use '\\' for directory change ");
        output = sc.nextLine();
        Workbook wbfactory = WorkbookFactory.create(new File(input));

        DataFormatter df = new DataFormatter();

        Sheet sh = wbfactory.getSheetAt(0);

        changeDateFormat(sh);


        /*
         ArrayList <String> arlst = new ArrayList<>();
        for(Row rw : sh) {
            arlst.add(rw.getCell(11).toString());
        }
         */
        //System.out.println("cell type : "+dt.toString().length());
        /*
        if (DateUtil.isCellDateFormatted(dt)) {
            System.out.println(dt.getDateCellValue());
        } else {
            System.out.println(dt.getNumericCellValue());

            System.out.println(dt.getBooleanCellValue());

        }
         */

 /* Cell dt = rw.getCell(6);
        System.out.println("data at 12 "+df.formatCellValue(dt));
        if(dt != null ) {
            System.out.println(dt.getStringCellValue());
        }
         */
    }

    public static void writeNewDateFile(ArrayList<String> s) throws FileNotFoundException, IOException {
        try (FileOutputStream fileOut = new FileOutputStream(output)) {
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("dates");

            CreationHelper createHelper = wb.getCreationHelper();
            Row rw;
            Cell cll;
            Iterator itr = s.iterator();
            while (itr.hasNext()) {
                rw = sheet.createRow(rowIndex++);
                cll = rw.createCell(3);
                cll.setCellValue(itr.next().toString());
                System.out.println("completed " + rowIndex);
            }
            // Row row = sheet.createRow(rowIndex);

            // Create a cell and put a date value in it.  The first cell is not styled
            // as a date.
            //Cell cell = row.createCell(1);
            // cell.setCellValue(s);
            /*
            // cell.setCellValue(new Date());

            // we style the second cell as a date (and time).  It is important to
            // create a new cell style from the workbook otherwise you can end up
            // modifying the built in style and effecting not only this cell but other cells.
            CellStyle cellStyle = wb.createCellStyle();
         /*
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
            cell = row.createCell(1);
            cell.setCellValue(new Date());
            cell.setCellStyle(cellStyle);
             */
            //you can also set date as java.util.Calendar
            // cell = row.createCell(2);
            /* working current date insertion 
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
            // System.out.println(cal.getTime());
            String formatted = format1.format(cal.getTime());
            System.out.println(formatted);
            
            
            // Output "2012-09-26"

            //  System.out.println(format1.parse(formatted));   
            cell.setCellValue(formatted);
            
             */
            // cell.setCellValue(Calendar.getInstance());
            // cell.setCellStyle(cellStyle);
            wb.write(fileOut);
        }

    }

    public static void changeDateFormat(Sheet sh) throws IOException, InvalidFormatException {
        ArrayList<String> arrlst = new ArrayList<>();
        //perfect
        for (Row rw : sh) {
            dt = rw.getCell(11);

            if (dt.toString().contains("/")) {
                Counter20++;
                // nDFormat(dt.toString());
                arrlst.add(nDFormat(dt.toString()));
                // writeNewDateFile( nDFormat(dt.toString()));
            } else if (dt.toString().contains("-")) {
                Counter11++;
                // System.out.println(dt.toString());
                // writeNewUpdateDateFile(dt.toString());
                arrlst.add(dt.toString());
            } else {
                unknownC++;
                unknown = dt.toString();
                arrlst.add(dt.toString());
                //  writeNewUpdateDateFile(dt.toString());
            }

            /*
            if (dt.toString().length() == 20 || dt.toString().length() == 22 ) {
                Counter20++;
                nDFormat(dt.toString());

//System.out.println(dt.toString());
            }
            else if(dt.toString().length() == 11 || dt.toString().length() == 10 ) {
                Counter11++;
                
               System.out.println(dt.toString());
            }
            else 
                unknownC++;
                
             */
        }

        //Row rw = sh.getRow(5408);
        //System.out.println("no of digits at 5408 : "+rw.getCell(11).toString().length());
       
        writeNewDateFile(arrlst);
         System.out.println("unknown : " + unknownC + ", counter 11 & 10: " + Counter11 + ", counter 20 & 22 : " + Counter20);
        System.out.println("unknown value is " + unknown);

    }

    public static String nDFormat(String st) {
        //perfect

        StringBuilder ns = new StringBuilder();
        for (int i = 0; i < st.length() - 11; i++) {
            ns.append(st.charAt(i));
        }
        String s = ns.toString();
        //int lnth = ns.length();
        String[] xdate = s.split("/");

        //String xdate = s.substring(0, 10);
        // System.out.println(xdate);
        String ndate = xdate[1] + "-" + month(xdate[0]) + "-" + xdate[2];    //+st.substring(ns.length(),ns.length()+11);
        System.out.println(ndate);
     //   cDateFormat();
        return ndate;
    }
    
    public static Date cDateFormat(String str) throws ParseException {
        
        Date date1=new SimpleDateFormat("dd-Mon-yyyy").parse(str);
        return date1;
    }

    public static String month(String s) {
        //perfect
        String arr[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        return arr[Integer.parseInt(s) - 1];
    }

}
