package com.reactive.josh;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Event {

    private Long id;
    private Date date;

    public Event(Long id, Date date) {
        this.id = id;
        this.date = date;
    }
}
