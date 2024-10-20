package com.example.todo.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		// For nested objects
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        
        return modelMapper;
	}
}
