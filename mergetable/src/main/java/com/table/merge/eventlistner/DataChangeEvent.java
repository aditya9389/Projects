package com.table.merge.eventlistner;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class DataChangeEvent extends ApplicationEvent {

    private String action;
    private Object data;
    private String table;

    public DataChangeEvent(String action, Object data, String table) {
        super(data);
        this.action = action;
        this.data = data;
        this.table = table;
        System.out.println("-----------data change event constructor called-----------");
    }

}
