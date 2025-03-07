package com.table.merge.eventlistner;

import com.table.merge.Model.Address;
import com.table.merge.Model.OneForAll;
import com.table.merge.Model.User;
import com.table.merge.repository.AllRepository;
import com.table.merge.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Slf4j
@Service
@Component
public class DataChangeEventListener {

    @Autowired
    private AllRepository allRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleDataChangeEvent(DataChangeEvent event) {
        log.info("-----------checking event is there or not-----------");
        if (event != null && "CREATE".equals(event.getAction())) {
            handleCreate(event);
        }
    }


    private void handleCreate(DataChangeEvent event) {
        log.info("-----------checking data is there in the event or not-----------");
        if (event.getData() == null) {
            throw new IllegalArgumentException("Event data cannot be null");
        }
        log.info("-----------if data is there getting all that and putting in all table-----------");
        if (event.getTable().equals("User"))
        {
            User user= (User) event.getData();
            OneForAll consolidated = new OneForAll();
            log.info("-----------user username----------->{}", user.getUsername());
            consolidated.setUsername(user.getUsername());
            log.info("-----------user empId----------->{}", user.getEmpId());
            consolidated.setEmpId(user.getEmpId());
            log.info("-----------user PhoneNumber----------->{}", user.getPhoneNumber());
            consolidated.setPhoneNumber(user.getPhoneNumber());
            log.info("-----------user userId----------->{}", user.getUserId());
            consolidated.setUserId(user.getUserId());
            log.info("-----------creating all table-----------");
            try
            {
                OneForAll done=allRepository.save(consolidated);
                log.info("-----entry in oneforall is done:--->"+done);
                allRepository.flush();
            }
            catch (Exception e)
            {
                log.info(e.getMessage());
            }
        }
        else if(event.getTable().equals("Address")) {
            log.info("-----------getting user data from all to put address-----------");
            Address address= (Address) event.getData();
            OneForAll consolidated = allRepository.findByUserId(address.getUserId())
                    .orElseThrow(()->new RuntimeException("user not found to put this address"));
            log.info("-----------user address details----------->{}", address.toString());
            consolidated.setPlace(address.getPlace());
            consolidated.setAltNumber(address.getAltNumber());
            log.info("-----------updating all table user data to put address----------");
            OneForAll done=allRepository.save(consolidated);
            log.info("-----entry in oneforall is done:--->"+done);
        }
    }
}
