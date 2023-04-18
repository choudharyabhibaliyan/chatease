package org.durga.utilites;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public class ResponceHandler {
    public static ResponseEntity<Object> generateResponce(HttpStatus status, boolean isSuccess , Object responce){
        long dateTime = LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        result.put("data", responce);
        result.put("status", status.value());
        result.put("dateTime", dateTime);
        return new ResponseEntity<>(result,status);
    }
}
