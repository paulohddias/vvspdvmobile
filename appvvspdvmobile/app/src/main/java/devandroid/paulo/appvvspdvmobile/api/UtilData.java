package devandroid.paulo.appvvspdvmobile.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilData {

    public static java.sql.Date stringToDate(String dataStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date dataUtil = df.parse(dataStr);
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(UtilData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String dateToString(java.util.Date dataUtil) {
        if (dataUtil == null) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(dataUtil);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("ERRO");
        }
        return null;
    }

    public static String stringToDataBD(String dataUtil) {
        String data= null;
        if (dataUtil == null) {
            return null;
        }
        try {
            SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
            data = out.format(in.parse(dataUtil));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("ERROR");
        }
        return null;
    }

    public static String stringToDataBR(String dataUtil) {
        String data= null;
        if (dataUtil == null) {
            return null;
        }
        try {
            SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
            data = out.format(in.parse(dataUtil));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("ERROR");
        }
        return null;
    }

    public static String stringToDataBrMes(String dataUtil) {
        String data= null;
        if (dataUtil == null) {
            return null;
        }
        try {
            SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat out = new SimpleDateFormat("dd LLL yyyy");
            data = out.format(in.parse(dataUtil));
            return data.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("ERROR");
        }
        return null;
    }

}
