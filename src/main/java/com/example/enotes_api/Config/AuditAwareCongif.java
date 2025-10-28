package com.example.enotes_api.Config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditAwareCongif implements AuditorAware{



    @Override
    public Optional<Integer> getCurrentAuditor() { //sets updated by in database
        return Optional.of(1);  //sending who update
    }
    
}
