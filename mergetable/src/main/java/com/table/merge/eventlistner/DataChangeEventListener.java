package com.table.merge.eventlistner;

import com.table.merge.Model.Address;
import com.table.merge.Model.OneForAll;
import com.table.merge.Model.User;
import com.table.merge.repository.AllRepository;
import com.table.merge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;


@Component
public class DataChangeEventListener {

    @Autowired
    private AllRepository allRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleDataChangeEvent(DataChangeEvent event) {
        System.out.println("-----------checking event is there or not-----------");
        if (event != null && "CREATE".equals(event.getAction())) {
            handleCreate(event);
        }
    }


    private void handleCreate(DataChangeEvent event) {
        System.out.println("-----------checking data is there in the event or not-----------");
        if (event.getData() == null) {
            throw new IllegalArgumentException("Event data cannot be null");
        }
        System.out.println("-----------if data is there getting all that and putting in all table-----------");
        if (event.getTable().equals("User"))
        {
            User user= (User) event.getData();
            OneForAll consolidated = new OneForAll();
            System.out.println("-----------user username----------->"+user.getUsername());
            consolidated.setUsername(user.getUsername());
            System.out.println("-----------user empId----------->"+user.getEmpId());
            consolidated.setEmpId(user.getEmpId());
            System.out.println("-----------user PhoneNumber----------->"+user.getPhoneNumber());
            consolidated.setPhoneNumber(user.getPhoneNumber());
            System.out.println("-----------user userId----------->"+user.getUserId());
            consolidated.setUserId(user.getUserId());
            System.out.println("-----------creating all table-----------");
            try
            {
                OneForAll done=allRepository.save(consolidated);
                System.out.println("-----entry in oneforall is done:--->"+done);
                allRepository.flush();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else if(event.getTable().equals("Address")) {
            System.out.println("-----------getting user data from all to put address-----------");
            Address address= (Address) event.getData();
            OneForAll consolidated = allRepository.findByUserId(address.getUserId())
                    .orElseThrow(()->new RuntimeException("user not found to put this address"));
            System.out.println("-----------user address details----------->"+address.toString());
            consolidated.setPlace(address.getPlace());
            consolidated.setAltNumber(address.getAltNumber());
            System.out.println("-----------updating all table user data to put address----------");
            OneForAll done=allRepository.save(consolidated);
            System.out.println("-----entry in oneforall is done:--->"+done);
        }
    }
}
