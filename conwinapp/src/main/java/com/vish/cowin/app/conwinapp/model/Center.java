package com.vish.cowin.app.conwinapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Center
{

    String pincode;
    String address;
    String fee_type;
    String district_name;
    String block_name;
    String name;
    String center_id;

    List<Session> sessions;
    String from;
    String to;
    String lat;
}