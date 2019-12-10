/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba;

/**
 *
 * @author Lenovo
 */


import com.proba.model.Admin;
import com.proba.model.Parents;
import com.proba.model.Teachers;
import com.proba.model.Messages;
import com.proba.model.News;
import com.proba.model.Opendoor;
import com.proba.service.AdminService;
import com.proba.service.ParentsService;
import com.proba.service.TeachersServices;
import com.proba.service.MessagesService;
import com.proba.service.NewsService;
import com.proba.service.OpenDoorServices;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
//import org.apache.commons.collections.ListUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
   
    
    
       @Autowired
       private AdminService adminService;
       @Autowired
       private ParentsService parentsService;
       @Autowired
       private TeachersServices teachersServices;
       @Autowired
       private MessagesService messagesService;
       @Autowired
       private NewsService newsService;
       @Autowired
       private OpenDoorServices openDoorServices;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value="error" , required =false) String error ){
        ModelAndView modelAndView = new ModelAndView();
        if(error != null){
            modelAndView.setViewName("error");
        } else
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home1(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    
    @RequestMapping(value="/teachers/home", method = RequestMethod.GET)
    public ModelAndView home2(@Param ("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
         Parents parents = parentsService.findParentsByEmail(auth.getName()); //email Parents dobijen
        modelAndView.setViewName("/teachers/home");
        return modelAndView;
    }
     @RequestMapping(value="/parents/home", method = RequestMethod.GET)
    public ModelAndView home3( @Param ("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
         Parents parents = parentsService.findParentsByEmail(auth.getName()); //email Parents dobijen    
        modelAndView.setViewName("/parents/home");
        return modelAndView;
    }
    
     
    
     @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView home8(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }
    
  
    
   //prikaz poruka za roditelje
    @RequestMapping(value="/parents/messages", method = RequestMethod.GET)
    public ModelAndView showMessagesP( @Param ("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
         Parents parents = parentsService.findParentsByEmail(auth.getName()); //email Parents dobijen    
     List<Messages> messages = messagesService.findMessagesByParentsId(parents.getIdParents());
       modelAndView.addObject("messages", messages);  
        modelAndView.setViewName("/parents/messages");
        return modelAndView;
    }
    // messages za dodavanje i brisanje poruka roditelji
    
    @RequestMapping("/parents/deletemessage/{id}")
    public String deleteMessages(@PathVariable(name = "id") Integer id) {
        messagesService.delete(id);
        return "redirect:/parents/messages";       
    }
    @RequestMapping(value="/parents/addmessages", method = RequestMethod.GET)
    public ModelAndView addMessages(){
        ModelAndView modelAndView = new ModelAndView();    
        List<Teachers> teachers = teachersServices.findTeachers();
        modelAndView.addObject("teachers", teachers);
        Messages messages = new Messages();
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }
    @RequestMapping(value="/parents/addmessages", method = RequestMethod.POST)
    public ModelAndView addMessages(@Valid Messages m){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        Parents parents = parentsService.findParentsByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        m.setMessageIdParent(parents);
        messagesService.save(m);
        modelAndView.setViewName("/parents/messages");
        return modelAndView;
    }
      
   //prikaz poruka za ucitelja
    @RequestMapping(value="/teachers/messages", method = RequestMethod.GET)
    public ModelAndView showMessagesT( @Param ("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
         Teachers teacher = teachersServices.findByEmail(auth.getName()); //email Parents dobijen    
     List<Messages> messages = messagesService.findMessagesByTeachersId(teacher.getIdTeachers());
       modelAndView.addObject("messages", messages);  
        modelAndView.setViewName("/teachers/messages");
        return modelAndView;
    }
    // za brisanje dodavanje poruka za ucitelja
    @RequestMapping("/teachers/deletemessage/{id}")
    public String deleteMessageT(@PathVariable(name = "id") Integer id) {
        messagesService.delete(id);
        return "redirect:/teachers/messages";       
    }
    @RequestMapping(value="/teachers/addmessages", method = RequestMethod.GET)
    public ModelAndView addMessagesT(){
        ModelAndView modelAndView = new ModelAndView();    
        List<Parents> parents = parentsService.findAll();
        modelAndView.addObject("parents", parents);
        Messages messages = new Messages();
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }
    @RequestMapping(value="/teachers/addmessages", method = RequestMethod.POST)
    public ModelAndView addMessagesT(@Valid Messages m){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        Teachers teacher = teachersServices.findByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        m.setMessageIdTeacher(teacher);
        messagesService.save(m);
        modelAndView.setViewName("/teachers/messages");
        return modelAndView;
    }
    
    
      
   //prikaz obavestenja za roditelje
    @RequestMapping(value="/parents/news", method = RequestMethod.GET)
    public ModelAndView showNewsP(){
        ModelAndView modelAndView = new ModelAndView();    
        List<News> news = newsService.findNews();
        modelAndView.addObject("news", news);  
        modelAndView.setViewName("/parents/news");
        return modelAndView;
    }
      
   //prikaz obavestenja za admine
    @RequestMapping(value="/admin/news", method = RequestMethod.GET)
    public ModelAndView showNewsA(){
        ModelAndView modelAndView = new ModelAndView();    
        List<News> news = newsService.findNews();
        modelAndView.addObject("news", news);  
        modelAndView.setViewName("/admin/news");
        return modelAndView;
    }
    // za obavestenja dodavanje, izmenu i brisanje
    @RequestMapping(value="/admin/addnews", method = RequestMethod.GET)
    public ModelAndView addNews(){
        ModelAndView modelAndView = new ModelAndView();    
        News news = new News();
        modelAndView.addObject("news", news);
        return modelAndView;
    }
    @RequestMapping(value="/admin/addnews", method = RequestMethod.POST)
    public ModelAndView addNews(@Valid News n){
        ModelAndView modelAndView = new ModelAndView();    
        newsService.save(n);  
        modelAndView.setViewName("/admin/news");
        return modelAndView;
    }
    @RequestMapping("/admin/editnews/{id}")
    public ModelAndView showEditNewsPage(@PathVariable(name = "id") Integer id) {
    ModelAndView modelAndView = new ModelAndView("/admin/editnews");
    News news = newsService.get(id);
    modelAndView.addObject("news", news);
    return modelAndView;
}
    @RequestMapping("/admin/deletenews/{id}")
    public String deleteNews(@PathVariable(name = "id") Integer id) {
        newsService.delete(id);
        return "redirect:/admin/news";       
    }
    

      
       // da mu izadje lista roditelja
          @GetMapping("/teachers/plista")
	public String listRoditelja(Model model, @RequestParam(defaultValue="")  String name) {
		model.addAttribute("parents",  parentsService.findByName(name));
		 List <Parents> parenti = parentsService.listaroditelja();
                 
                model.addAttribute("parenti", parenti);
                return "/teachers/plista";
	}
       // nastavnik zakazuje roditeljski
        @GetMapping("/teachers/opendoor")
        public String OpenDoorForm(String email, Model model, HttpSession session){
        session.setAttribute("email", email);
         model.addAttribute("opendoor",new Opendoor());  
        return "/teachers/opendoor";
        }
        @PostMapping("/teachers/opendoor")
        public String addOpendoor(@Valid Opendoor opendoor,BindingResult bindingResult, HttpSession session ) {
           if(bindingResult.hasErrors()) {
            return "/teachers/opendoor";
           }
            String email = (String) session.getAttribute("email");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
            Teachers teachers = teachersServices.findByEmail(auth.getName()); //email Parents dobijen    
            teachers.getIdTeachers();
            openDoorServices.addOpenDoor(opendoor, parentsService.findParentsByEmail(email),teachersServices.findByEmail(auth.getName()));
           return "redirect:/teachers/opendlista";
        }
        //da vide otvorena vrata
        @RequestMapping(value="/teachers/opendlista", method = RequestMethod.GET)
    public ModelAndView home31( @Param ("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
       Teachers teachers = teachersServices.findByEmail(auth.getName());   //email Parents dobijen    
        List <Opendoor> otvorenav= openDoorServices.findOpenDoorByTeachersEmail(teachers.getEmail()); 
        modelAndView.addObject("otvorenav", otvorenav);
        modelAndView.setViewName("/teachers/opendlista");
        return modelAndView;
    }
    
    
     
  
   //da mu izadje lista nastavnika
          @GetMapping("/parents/lista")
	public String listNastavnika(Model model, @RequestParam(defaultValue="")  String name) {
		model.addAttribute("teachers",  teachersServices.findByNameTeachers(name));
		 List <Teachers> nastavnici = teachersServices.findTeachers();   
                model.addAttribute("nastavnici", nastavnici);
                return "/parents/lista";
	}
         //roditelj zakazuje otvorena vrata
          @GetMapping("/parents/opendoor")
        public String OpenDoorForm2(String email, Model model, HttpSession session){
        session.setAttribute("email", email);
         model.addAttribute("opendoor",new Opendoor());  
        return "/parents/opendoor";
        }
        
         @PostMapping("/parents/opendoor")
        public String addOpendoor2(@Valid Opendoor opendoor,BindingResult bindingResult, HttpSession session ) {
           if(bindingResult.hasErrors()) {
            return "/teachers/opendoor";
           }
           String email = (String) session.getAttribute("email");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
         Parents parents = parentsService.findParentsByEmail(auth.getName()); //email Parents dobijen    
           openDoorServices.addOpenDoor(opendoor, parentsService.findParentsByEmail(auth.getName()),teachersServices.findByEmail(email));
           return "redirect:/parents/opendlista";
        }
        
        //da vide otvorena vrata
         @RequestMapping(value="/parents/opendlista", method = RequestMethod.GET)
    public ModelAndView home35( @Param ("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
       Parents parents = parentsService.findParentsByEmail(auth.getName()); //email Parents dobijen    
        List <Opendoor> otvorena= openDoorServices.findopenDoorsByParentsEmail(parents.getEmail());
        modelAndView.addObject("otvorena", otvorena);
        modelAndView.setViewName("/parents/opendlista");
        return modelAndView;
    }
    
    //NOVI RODITELJ
    @RequestMapping(value="/admin/novikorisnik", method = RequestMethod.GET)
    public ModelAndView novikorisnik(){
        ModelAndView modelAndView = new ModelAndView();
        Parents parents = new Parents();
        modelAndView.addObject("parents", parents);
        return modelAndView;
    }
    
    @RequestMapping(value = "/admin/novikorisnik", method = RequestMethod.POST)
    public ModelAndView createNewKorisnik(@Valid Parents parents, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        Parents parentsExists = parentsService.findParentsByEmail(parents.getEmail());
        if (parentsExists != null) {
           bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/novikorisnik");
        } else {
            parentsService.saveParents(parents);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.setViewName("/admin/novikorisnik");}
        return modelAndView;
    }
 //NOVI NASTAVNIK  
    @RequestMapping(value="/admin/novinastavnik", method = RequestMethod.GET)
    public ModelAndView novinastavnik(){
        ModelAndView modelAndView = new ModelAndView();
        Teachers teachers = new Teachers();
        modelAndView.addObject("teachers", teachers);  
        return modelAndView;
    }
    
    @RequestMapping(value = "/admin/novinastavnik", method = RequestMethod.POST)
    public ModelAndView createNewKorisnik(@Valid Teachers teachers, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        Teachers teachersExists = teachersServices.findByEmail(teachers.getEmail());
        if (teachersExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/novinastavnik");
        } else {
           teachersServices.saveTeacher(teachers);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.setViewName("/admin/novinastavnik");

        }
        return modelAndView;
    }


}
    


