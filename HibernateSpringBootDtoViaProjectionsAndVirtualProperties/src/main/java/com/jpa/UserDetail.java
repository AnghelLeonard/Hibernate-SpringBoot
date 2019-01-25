package com.jpa;

import org.springframework.beans.factory.annotation.Value;

public interface UserDetail {
    
    String getName();
    
    @Value("#{target.city}")
    String livingin();
    
    @Value("#{ T(java.lang.Math).random() * 10000 }")
    int sessionid();
            
    @Value("online")        
    String status();
}
