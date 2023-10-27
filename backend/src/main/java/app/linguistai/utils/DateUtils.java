package app.linguistai.utils;

public class DateUtils {
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
