package com.t_systems.t_medical_center_system.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class Convertor<T,K> {

    private final ModelMapper modelMapper;

    @Autowired
    public Convertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public T convertToEntity(K dto,Class<T> tClass) {
        return modelMapper.map(dto, tClass);
    }
    public K convertToDto(T entity,Class<K> kClass) {
        return modelMapper.map(entity, kClass);
    }
//    public List<T> convertListDtoToEntity(List<K> entityList,Class<T> kClass) {
//        return entityList.stream()
//                .map(entity -> modelMapper.map(entity,kClass))
//                .collect(Collectors.toList());
//
//    }
    public List<K> convertLisToDto(List<T> entityList, Class<K> tClass) {
        return entityList.stream()
                .map(dto -> modelMapper.map(dto,tClass))
                .collect(Collectors.toList());
    }

}
