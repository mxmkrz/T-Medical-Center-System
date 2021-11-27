package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EventRepositoryTest {


    @Mock
    private EventRepository eventRepository;

    @Test
    public void findAllByPatientId() {
        assertTrue(eventRepository.findAll().iterator().hasNext());

        Patient patient = new Patient();
        patient.setId(1L);
        Event event = new Event();
        event.setPatient(patient);
        List<Event> eventList = List.of(event);

        eventRepository.save(event);

        assertSame(eventRepository.findAllByPatientId(patient.getId()), eventList);


    }

}