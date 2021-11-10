package com.t_systems.t_medical_center_system.mapper;


import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.dto.EventBoardDto;

import com.t_systems.t_medical_center_system.entity.Event;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;


@Component
public class EventMapper {

    private final ModelMapper modelMapper;
    private final Converter<String, String> date = MappingContext::getSource;

    @Autowired
    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.createTypeMap(Event.class, EventDto.class).addMappings(mapper -> mapper.using(date).map(Event::getDate, EventDto::setEventDateTime));
    }



    public EventDto toDto(Event event) {
        return modelMapper.map(event,EventDto.class);
    }


    public EventBoardDto toStringDto(Event event){
        return modelMapper.map(event, EventBoardDto.class);
    }

    public List<EventBoardDto> toStringDtoList(List<Event> events){
        List<EventBoardDto> eventSpringDtos = new ArrayList<>();
        for (Event e:events) {
            eventSpringDtos.add(toStringDto(e));
        }
        return eventSpringDtos;
    }


    public Event toEntity(EventDto eventDto){
        return modelMapper.map(eventDto,Event.class);
    }
}
