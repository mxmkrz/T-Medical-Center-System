package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;


public interface MedicalStaffService {

    void saveStaff(MedicalStaff doctor);

    void sendLinkOnEmail(String email);

    void changePassword(MedicalStaff medicalStaff);



}
