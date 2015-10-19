/**
 * 
 */
package com.group9.bankofaz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.group9.bankofaz.dao.BankAccountDAO;
import com.group9.bankofaz.dao.TaskDAO;
import com.group9.bankofaz.dao.TransactionDAO;
import com.group9.bankofaz.service.LoginService;
import com.group9.bankofaz.service.TransactionManagerService;

/**
 * @author Anirudh Ruia Gali
 *
 */

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	BankAccountDAO bankAccountDao;
	
	@Autowired
	TaskDAO taskDao;
	
	@Autowired
	TransactionDAO transactionDao;
	
	@Autowired
	TransactionManagerService transactionManagerService;
	
	@RequestMapping("/login")  
	 public ModelAndView getLoginForm(  
	   @RequestParam(required = false) String authfailed, String logout,  
	   String denied) {  
	  String message = "";  
	  if (authfailed != null) {  
	   message = "Invalid username of password, try again !";  
	  } else if (logout != null) {  
	   message = "Logged Out successfully, login again to continue !";  
	  } else if (denied != null) {  
	   message = "Access denied for this user !";  
	  }  
	  return new ModelAndView("login", "message", message);  
	 }  
	
	@RequestMapping("/login/otp")  
	public String geOtpView() {  
	  return "otp";  
	 }  
	
	@RequestMapping("/403page")  
	public String ge403denied() {  
	  return "redirect:login?denied";  
	}
	
	@RequestMapping(value = "/forgotPassword" , method = RequestMethod.GET)
	public ModelAndView forgotPassword(HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		return null;
	} 
	
	@RequestMapping("/test")
    public ModelAndView handleRequest() throws Exception {
		
       /*List<BankAccount> accounts = bankAccountDao.findAccountsOfUser(1001);
        BankAccount fromacc = new BankAccount();
        BankAccount toacc = new BankAccount();
        
        
       for(BankAccount account: accounts){
        	if(account.getAcctype().equals("checking")){
        		fromacc = account;
        	}
        }
       
       accounts = bankAccountDao.findAccountsOfUser(1002);
       
       for(BankAccount account: accounts){
       	if(account.getAcctype().equals("checking")){
       		toacc = account;
       	}
       }
     
	    Date dateobj = new Date();
        
        Transaction transaction = new Transaction();
        transaction.setTid(34);
		transaction.setTransType("payment");
		transaction.setAmt(12);
		transaction.setTransStatus("processing");
		transaction.setFromacc(fromacc);
		transaction.setToacc(toacc);
		transaction.setTransDate(dateobj);
		transaction.setTransDesc("TACOBELL");
		
		boolean sucess = transactionManagerService.updateTransaction(transaction);
*/		
		int otp = loginService.generateOTP("gali.anirudh@gmail.com");
		
		boolean otp1 = loginService.validateOtp("gali.anirudh@gmail.com", otp);
		ModelAndView model = new ModelAndView("test_internalusers_list");
        model.addObject("userList", otp1);
        return model;
    }

}
