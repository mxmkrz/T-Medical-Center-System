package com.t_systems.t_medical_center_system.dto;


import java.util.ArrayList;
import java.util.List;


public class AppointmentListWrapper {
    private List<AppointmentDto> appointmentDtoArrayList;

    public AppointmentListWrapper() {
        this.appointmentDtoArrayList = new ArrayList<>();
    }

    public List<AppointmentDto> getAppointmentDtoArrayList() {
        return appointmentDtoArrayList;

    }

    public void setAppointmentDtoArrayList(List<AppointmentDto> appointmentDtoArrayList) {
        this.appointmentDtoArrayList = appointmentDtoArrayList;
    }


}
