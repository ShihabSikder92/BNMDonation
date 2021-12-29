package com.example.bnmdonation.web;

import com.example.bnmdonation.model.MedRequest;
import com.example.bnmdonation.model.MedResponse;
import com.example.bnmdonation.model.User;
import com.example.bnmdonation.repository.UserRepository;
import com.example.bnmdonation.service.BloodReqService;
import com.example.bnmdonation.service.BloodResponseService;
import com.example.bnmdonation.service.MedReqService;
import com.example.bnmdonation.service.MedResponseService;
import com.example.bnmdonation.web.dto.MedRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MedController {

    @Autowired
    private BloodReqService bloodReqService;

    @Autowired
    private BloodResponseService bloodResponseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedReqService medReqService;

    @Autowired
    private MedResponseService medResponseService;

    @GetMapping("/med_request")
    public String MedRequest(Model model){
        model.addAttribute("medReq",medReqService.getallMedReq());
        return "med_request";
    }
    @GetMapping("/addmedrequest")
    public String addmedReqForm(){
        return "add_med_req";
    }
    @GetMapping("/homepage")
    public String homepage(){
        return "index";
    }

    @PostMapping("/medreqregister")
    public String medReqRegister(@ModelAttribute MedRegisterDto medRegisterDto, HttpSession session){
        Authentication auto = SecurityContextHolder.getContext().getAuthentication();
        Object details = auto.getDetails();
        Object principle = auto.getPrincipal();
        String username =((UserDetails)principle).getUsername();
        User user = userRepository.findByEmail(username);
        long userid = user.getId();
        //System.out.println(medRequest);
        //medReqService.addmedReq(medRequest);
        MedRequest medRequest = new MedRequest();
        medRequest.setName(medRegisterDto.getName());
        medRequest.setContact(medRegisterDto.getContact());
        medRequest.setLocation(medRegisterDto.getLocation());
        medRequest.setMedName(medRegisterDto.getMedName());
        medRequest.setCompany(medRegisterDto.getCompany());
        medRequest.setQuantity(medRegisterDto.getQuantity());
        medRequest.setUserid(userid);
        medReqService.addmedReq(medRequest);
        session.setAttribute("msg","Request added successfully........");
        return "redirect:/med_request";
    }

}
