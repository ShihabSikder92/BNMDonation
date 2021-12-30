package com.example.bnmdonation.web;

import com.example.bnmdonation.model.*;
import com.example.bnmdonation.repository.BloodReqRepo;
import com.example.bnmdonation.repository.UserRepository;
import com.example.bnmdonation.service.BloodReqService;
import com.example.bnmdonation.service.BloodResponseService;
import com.example.bnmdonation.service.MedReqService;
import com.example.bnmdonation.service.MedResponseService;
import com.example.bnmdonation.web.dto.BloodRegisterDto;
import com.example.bnmdonation.web.dto.UpdateBloodRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BloodController {
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

    @GetMapping("/blood_request")
    public String MedRequest(Model model){
        model.addAttribute("medReq",bloodReqService.getallBloodReq());
        return "blood_request";
    }
    @GetMapping("/addbloodrequest")
    public String addbloodReqForm(){
        return "add_blood_request";
    }
//    @GetMapping("/homepage")
//    public String homepage(){
//        return "index";
//    }
    @PostMapping("/bloodreqregister")
    public String medReqRegister(@ModelAttribute BloodRegisterDto bloodRegisterDto, HttpSession session){
        Authentication auto = SecurityContextHolder.getContext().getAuthentication();
        Object principle = auto.getPrincipal();
        String username =((UserDetails)principle).getUsername();
        User user = userRepository.findByEmail(username);
        long userid = user.getId();
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setNickName(bloodRegisterDto.getNickName());
        bloodRequest.setContact(bloodRegisterDto.getContact());
        bloodRequest.setBgn(bloodRegisterDto.getBgn());
        bloodRequest.setHospital(bloodRegisterDto.getHospital());
        bloodRequest.setHosLocation(bloodRegisterDto.getHosLocation());
        bloodRequest.setQuantity(bloodRegisterDto.getQuantity());
        bloodRequest.setUserid(userid);
        bloodReqService.addbloodReq(bloodRequest);
//        bloodRequest.s(user);
        session.setAttribute("msg","Request added successfully........");
        return "redirect:/blood_request";
    }
    @GetMapping("/response/{id}/{type}")
    public String response(@PathVariable int id,@PathVariable String type,HttpSession session ){
//        System.out.println(type);
        Authentication auto = SecurityContextHolder.getContext().getAuthentication();
        Object details = auto.getDetails();
        Object principle = auto.getPrincipal();
        String username =((UserDetails)principle).getUsername();
        User user = userRepository.findByEmail(username);
        long userid = user.getId();
        //String userName = userRepository.find;
        //User user = userRepository;
        String check="blood";
        if(type.equals(check)){
            BloodRequest bloodRequest = bloodReqService.getRequestbyID(id);
            BloodResponse bloodResponse = new BloodResponse();
            bloodResponse.setBloodRequest(bloodRequest);
            bloodResponse.setUser(user);
            if(userid==bloodRequest.getUserid()){
                session.setAttribute("msg", "You can't response to your own request........");
                return "redirect:/blood_request";
            }
            if (bloodResponseService.findBloodResponseByUserID(userid,id)) {
                session.setAttribute("msg", "You Have already added a  response........");
            } else {
                bloodResponseService.addResponse(bloodResponse);
                session.setAttribute("msg", "Response added successfully........");
            }
            return "redirect:/blood_request";
        }else{
            MedRequest medRequest = medReqService.getRequestbyID(id);
            MedResponse medResponse = new MedResponse();
            medResponse.setMedRequest(medRequest);
            medResponse.setUser(user);
            if(userid==medRequest.getUserid()){
                session.setAttribute("msg", "You can't response to your own request........");
                return "redirect:/med_request";
            }
            if(medResponseService.findMedResponseByUserID(userid,id)){
                session.setAttribute("msg", "You Have already added a  response........");
            }else{
                medResponseService.addResponse(medResponse);
                session.setAttribute("msg", "Response added successfully........");
            }
            return "redirect:/med_request";
        }
    }
    @GetMapping("/view/{id}/{type}")
    public String view(@PathVariable int id,@PathVariable String type,Model model){
        String check = "blood";
        if(type.equals(check)) {
            List<BloodResponse> list = bloodResponseService.findUserByRequestID(id);
            List<User> users = new ArrayList<>(list.size());
            for (int i = 0; i < list.size(); i++) {
                users.add(list.get(i).getUser());
            }
            BloodRequest bloodRequest = bloodReqService.getRequestbyID(id);
            model.addAttribute("request",bloodRequest);
            model.addAttribute("response",users);
            return "/bloodview";
        }else{
            List<MedResponse> list = medResponseService.findUserByRequestID(id);
            List<User> users = new ArrayList<>(list.size());
            for(int i = 0 ; i < list.size() ; i++){
                users.add(list.get(i).getUser());
            }
            MedRequest medRequest = medReqService.getRequestbyID(id);
            System.out.println(medRequest.getName());
            model.addAttribute("request",medRequest);
            model.addAttribute("response",users);
            return "view";
        }
    }
    @PostMapping("/blood_update")
    public String update(@ModelAttribute UpdateBloodRegisterDto updateBloodRegisterDto, Model model,HttpSession session){
        Authentication auto = SecurityContextHolder.getContext().getAuthentication();
        Object principle = auto.getPrincipal();
        String username =((UserDetails)principle).getUsername();
        User user = userRepository.findByEmail(username);
        long userid = user.getId();
        BloodRequest bloodRequest = new BloodRequest();
        bloodRequest.setId(updateBloodRegisterDto.getId());
        bloodRequest.setNickName(updateBloodRegisterDto.getNickName());
        bloodRequest.setContact(updateBloodRegisterDto.getContact());
        bloodRequest.setBgn(updateBloodRegisterDto.getBgn());
        bloodRequest.setHospital(updateBloodRegisterDto.getHospital());
        bloodRequest.setHosLocation(updateBloodRegisterDto.getHosLocation());
        bloodRequest.setQuantity(updateBloodRegisterDto.getQuantity());
        bloodRequest.setUserid(userid);
        bloodReqService.addbloodReq(bloodRequest);
//        bloodRequest.s(user);
        session.setAttribute("msg","Request Update successfully........");
        model.addAttribute("user",user);
        return "index";
    }
    @GetMapping("/delete/{id}/{type}")
    public String delete(@PathVariable int id,Model model,@PathVariable String type){
        Authentication auto = SecurityContextHolder.getContext().getAuthentication();
        Object principle = auto.getPrincipal();
        String username =((UserDetails)principle).getUsername();
        User user = userRepository.findByEmail(username);
        String check="blood";
        if(type.equals(check)){
            bloodReqService.delete(id);
            model.addAttribute("user",user);
            return "/index";
        }else{
            medReqService.delete(id);
            model.addAttribute("user",user);
            return "/index";
        }
    }
}
