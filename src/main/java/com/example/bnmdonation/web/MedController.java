package com.example.bnmdonation.web;

import com.example.bnmdonation.model.BloodRequest;
import com.example.bnmdonation.model.MedRequest;
import com.example.bnmdonation.model.MedResponse;
import com.example.bnmdonation.model.User;
import com.example.bnmdonation.repository.UserRepository;
import com.example.bnmdonation.service.BloodReqService;
import com.example.bnmdonation.service.BloodResponseService;
import com.example.bnmdonation.service.MedReqService;
import com.example.bnmdonation.service.MedResponseService;
import com.example.bnmdonation.web.dto.*;
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
import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/med_update")
    public String update(@ModelAttribute UpdateMedRegisterDto updateMedRegisterDto, Model model, HttpSession session){
        Authentication auto = SecurityContextHolder.getContext().getAuthentication();
        Object principle = auto.getPrincipal();
        String username =((UserDetails)principle).getUsername();
        User user = userRepository.findByEmail(username);
        long userid = user.getId();
        MedRequest medRequest = new MedRequest();
        medRequest.setId(updateMedRegisterDto.getId());
        medRequest.setUserid(userid);
        medRequest.setName(updateMedRegisterDto.getName());
        medRequest.setContact(updateMedRegisterDto.getContact());
        medRequest.setLocation(updateMedRegisterDto.getLocation());
        medRequest.setMedName(updateMedRegisterDto.getMedName());
        medRequest.setCompany(updateMedRegisterDto.getCompany());
        medRequest.setQuantity(updateMedRegisterDto.getQuantity());
        medReqService.addmedReq(medRequest);
        model.addAttribute("details",user);
        List<BloodRequest> bloodRequests = bloodReqService.getRequestByUserID(user.getId());
        List<BloodDetails> blood = new ArrayList<>(bloodRequests.size());
        for(int i = 0 ; i < bloodRequests.size();i++){
            BloodDetails x = new BloodDetails();
            x.setId(bloodRequests.get(i).getId());
            x.setBgn(bloodRequests.get(i).getBgn());
            x.setHospital(bloodRequests.get(i).getHospital());
            x.setHosLocation(bloodRequests.get(i).getHosLocation());
            x.setQuantity(bloodRequests.get(i).getQuantity());
            blood.add(x);
        }
        List<MedRequest> medRequests = medReqService.getRequestByUserID(user.getId());
        System.out.println(medRequests.size());
        List<MedDetails> med = new ArrayList<>(medRequests.size());
        for(int i = 0 ; i < medRequests.size();i++){
            MedDetails x = new MedDetails();
            x.setId(medRequests.get(i).getId());
            x.setMedName(medRequests.get(i).getMedName());
            x.setCompany(medRequests.get(i).getCompany());
            x.setQuantity(medRequests.get(i).getQuantity());
            med.add(x);
        }
        model.addAttribute("med",med);
        model.addAttribute("blood",blood);
        session.setAttribute("msg","Request Update successfully........");
        //model.addAttribute("user",user);
        return "profile";
    }

}
