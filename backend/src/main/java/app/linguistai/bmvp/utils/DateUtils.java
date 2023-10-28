package app.linguistai.bmvp.utils;

public class DateUtils {
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        return utilDate != null ? new java.sql.Date(utilDate.getTime()) : null;
    }

    public static java.util.Date convertSqlDateToUtilDate(java.sql.Date sqlDate) {
        return sqlDate != null ? new java.util.Date(sqlDate.getTime()) : null;
    }
}
