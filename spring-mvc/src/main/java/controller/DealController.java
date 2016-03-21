package controller;

import com.becomejavasenior.spring.interfaces.AbstractService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newDeal")
public class DealController {

    private static final Logger LOGGER = Logger.getLogger(DealController.class);

    @Autowired
    AbstractService<Object> abstractService;

//    @RequestMapping(value = "/newDeal", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public Deal saveDeal(@RequestBody Deal deal) {
//        return (Deal) abstractService.createEntity(deal);
//    }
//
//    @RequestMapping
//    public void getCompany(@RequestBody Company company) {
//        abstractService.objectByPK(company.getId());
//    }
}
