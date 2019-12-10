
package com.proba.service;

import com.proba.model.Admin;
import com.proba.model.UserRole;
import com.proba.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
import com.proba.repository.UserRoleRepository;
import java.util.List;




@Service("adminService")
public class AdminService {
    
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRoleRepository userroleRepository;
    
   
    
    /**
     *
     * @param adminRepository
     * @param userroleRepository
     * @param bCryptPasswordEncoder
     */
    @Autowired
    public AdminService(AdminRepository adminRepository,UserRoleRepository userroleRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
            this.adminRepository = adminRepository;
            this.userroleRepository = userroleRepository;
             this.bCryptPasswordEncoder = bCryptPasswordEncoder;    
    }
    public Admin findAdminByEmail(String email) {
        return adminRepository.findByEmail(email) ; //To change body of generated methods, choose Tools | Templates.
    }

    public Admin saveAdmin(Admin admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setActive(1);
        UserRole adminStatus = userroleRepository.findByRole("ADMIN");
       return adminRepository.save(admin); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
   /**) public Admin saveAdmin(Admin admin){
    admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
    admin.setActive(1);
    Status adminStatus = statusRepository.findByStatus("ADMIN");
    admin.setStatus(new HashSet<Status>(Array.asList(adminStatus)));
    return adminRepository.save(admin);
    */

