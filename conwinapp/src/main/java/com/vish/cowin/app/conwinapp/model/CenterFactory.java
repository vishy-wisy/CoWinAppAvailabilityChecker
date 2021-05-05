package com.vish.cowin.app.conwinapp.model;

import lombok.Data;

@Data
public class CenterFactory
{
    public static Center getCenter(String cen)
    {
        return new Center.CenterBuilder()
                       .center_id(cen)
                .build();

    }

}
