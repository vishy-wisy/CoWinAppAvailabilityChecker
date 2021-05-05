package com.vish.cowin.app.conwinapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session
{
    String date;
    String vaccine;
    List<String> slots;
    String min_age_limit;
    String session_id;
    String available_capacity;
}
