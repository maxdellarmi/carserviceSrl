package com.nesea.exercise.carservicesrl.configurations;

import com.nesea.exercise.carservicesrl.model.LookupEntity;
import org.springframework.core.convert.converter.Converter;

public class LookupConverter implements Converter<LookupEntity, String> {
    public String convert(LookupEntity source) {
        return String.valueOf(source.getId());
    }
}
