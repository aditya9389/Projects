package com.table.merge.eventlistner;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EntityChangeListener {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostPersist
    public void onPostPersist(Object entity) {
        System.out.println("Entity Created: " + entity);
        eventPublisher.publishEvent(new DataChangeEvent("CREATE", entity, entity.getClass().getSimpleName()));
    }
}
