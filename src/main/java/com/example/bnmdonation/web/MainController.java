package com.example.bnmdonation.web;

import com.example.bnmdonation.model.*;
import com.example.bnmdonation.repository.UserRepository;
import com.example.bnmdonation.service.BloodReqService;
import com.example.bnmdonation.service.BloodResponseService;
import com.example.bnmdonation.service.MedReqService;
import com.example.bnmdonation.service.MedResponseService;
import com.example.bnmdonation.web.dto.BloodDetails;
import com.example.bnmdonation.web.dto.BloodRegisterDto;
import com.example.bnmdonation.web.dto.MedDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

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

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		Authentication auto = SecurityContextHolder.getContext().getAuthentication();
		Object principle = auto.getPrincipal();
		String username =((UserDetails)principle).getUsername();
		User user = userRepository.findByEmail(username);
		System.out.println(user.toString());
		model.addAttribute("user",user);
		return "index";
	}
	@GetMapping("/profile/{name}")
	public String profile(Model model,@PathVariable String name){
//		Authentication auto = SecurityContextHolder.getContext().getAuthentication();
//		Object principle = auto.getPrincipal();
//		String username =((UserDetails)principle).getUsername();
		User user = userRepository.findByEmail(name);
		System.out.println(name);
		System.out.println(user.toString());
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
		return "profile";
	}
	@GetMapping("/update/{id}/{type}")
	public String update(@PathVariable int id, @PathVariable String type, Model model){
		String check="blood";
		if(type.equals(check)){
			BloodRequest bloodRequest = bloodReqService.getRequestbyID(id);
			System.out.println(bloodRequest.toString());
			System.out.println(bloodRequest.getId());
			model.addAttribute("blood",bloodRequest);
			return "/update_blood_Request";
		}else{
			MedRequest medRequest = medReqService.getRequestbyID(id);
			model.addAttribute("med",medRequest);
			return "redirect:/update";
		}
	}
}
