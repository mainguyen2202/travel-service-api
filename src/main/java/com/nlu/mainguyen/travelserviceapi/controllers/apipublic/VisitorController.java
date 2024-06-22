package com.nlu.mainguyen.travelserviceapi.controllers.apipublic;

// import javax.validation.Valid;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.mainguyen.travelserviceapi.entities.VisitorTModel;
import com.nlu.mainguyen.travelserviceapi.services.VisitorService;

@Controller
@RequestMapping(path = "/visitor")
public class VisitorController {

    private VisitorService visitorService;// thuộc tính
    // constuctor

    public VisitorController(VisitorService initialVisitorSer) {
        // Khởi tạo serice
        this.visitorService = initialVisitorSer;
    }

    @GetMapping("/list")
    public @ResponseBody List<VisitorTModel> showAllVisitors(Model model) {
        return this.visitorService.showAllUser();
    }

    @PostMapping("/register")
    public @ResponseBody String registrationViewForm(@RequestBody VisitorTModel visitor) {
        // TODO
        VisitorTModel result = this.visitorService.createAVisitor(visitor);
        System.out.println(result);
        return "success";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody VisitorTModel viewAVisitorByID(@PathVariable("id") Long id) {
        VisitorTModel aVisitor = visitorService.getByIdAVisitor(id);
        return aVisitor;
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody String editAVisitor(@PathVariable("id") Long id,
            @Valid @RequestBody VisitorTModel updateCurrentUser) {
        visitorService.updateAVisitor(updateCurrentUser);
        return "success";

    }

    @PostMapping("/remove/{id}")
    public String removeAVisitor(@PathVariable("id") Long id) {
        visitorService.deleteByID(id);
        return "success";
    }

}