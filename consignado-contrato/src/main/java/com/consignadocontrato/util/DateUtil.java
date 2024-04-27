package com.consignadocontrato.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static LocalDateTime getFormatedActualDate() {
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dataHoraFormatada = now.format(formatter);
        return LocalDateTime.parse(dataHoraFormatada,formatter);
	}
	
	public static String getUnformattedTimestamp() {
		
	    LocalDateTime now = LocalDateTime.now();
	    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
	    Timestamp timestamp = Timestamp.valueOf(now);

	    return sdf.format(timestamp.getTime());
	    
	}

}
