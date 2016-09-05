package util;

public class Tools {

    public static java.sql.Date converterDateToSql(java.util.Date dataUtil) {

        java.sql.Date dataSql = null;

        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;

        } catch (Exception e) {
            System.err.println("ERRO - CONVERSÃO: " + e);
        }

        return dataSql;
    }

    public static java.util.Date converterSqlToDate(java.sql.Date dataUtil) {

        java.util.Date dataSql = null;

        try {

            dataSql = (java.util.Date) dataUtil;

        } catch (Exception e) {
            System.err.println("ERRO - CONVERSÃO: " + e);
        }

        return dataSql;
    }

    public static String formatDataHora(java.util.Date d) {

        try {
            if (d != null) {
                java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy '-' HH:mm:ss 'h'");
                return df.format(d);
            }
        } catch (Exception e) {
            System.err.println("ERRO - FORMATAÇÃO: " + e);
        }
        return "";

    }

    public static String formatInsertDataHora(java.util.Date d) {
        String r = "";

        if (d != null) {
            r = String.format("%s", d);
        }

        return r;
    }

}
