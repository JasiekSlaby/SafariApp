package com.MeetMe.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Event {
    private String title;
    private String date;
    private String location;
    private String description;

    public boolean isActual(){
        // Date to check
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(sdf.format(nowDate));

        String eventDate = date.substring(0,10);
        LocalDate nextDate = LocalDate.parse(eventDate);

        return (today.isBefore(nextDate)||today.equals(nextDate));
    }
}
