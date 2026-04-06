package com.cg.hibernate;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.hibernate.exception.EmployeeNotFoundException;

/**
 * Hello world!
 */
public class App {
	private EmployeeCG emp;
	private Address address;
	private Address address1;
	public ApplicationContext context;
	
	private Scanner sc;
	
	App(){		
		sc=new Scanner(System.in);
		context=new ClassPathXmlApplicationContext("spring.xml");
	}
	
	
		
    public static void main(String[] args) {
    	
    	
    	App a=new App();
    	a.CRUD();
    }
    
       public void CRUD() {
    	   int next=0;
    	   int choice=0;
       
       Session session=new Configuration()
    		   			.configure()
    		   			.addAnnotatedClass(com.cg.hibernate.EmployeeCG.class)
       		   			.addAnnotatedClass(com.cg.hibernate.Address.class)
    		   			.buildSessionFactory()
    		   			.openSession();
//       config.configure();
//  	 config.addAnnotatedClass(com.cg.hibernate.EmployeeCG.class);
//       SessionFactory sessionfactory =config.buildSessionFactory();
//       Session session=sessionfactory.openSession();       
       
      
       //session.persist(emp);
       
       do
       {	Transaction transaction=session.beginTransaction();  
       		emp=(EmployeeCG) context.getBean("employee");
       		address=(Address) context.getBean("address");
       		       			
       		System.out.println("1)Insert _______2)ViewById_______3)Update________4)Delete");
      		choice=sc.nextInt();
       		
       	switch(choice){		
       		case 1:
       
    	   	System.out.println("Enter your name: ");
       		emp.setName(sc.next());
       		
       		System.out.println("Enter your street: ");
       		address.setStreet(sc.next());
       		
       		System.out.println("Enter your 2nd street: ");
       		address1.setStreet(sc.next());
       		
       		
       		emp.setAddress(Arrays.asList(address));
       		address.setEmployeeCG(emp);
       		
       
       		try {
       		session.persist(address);    		
       		session.persist(emp);
       		transaction.commit();}
       		catch(Exception e) {System.out.println(e.getMessage());}
       		System.out.println("Saved Successfully");
       		break;
       		
       		case 2:
       			System.out.println("Enter the id");
       			
       			emp=session.find(EmployeeCG.class, sc.nextInt());
       			try {
       				 if(emp==null) 
       				 {throw new EmployeeNotFoundException("Employee Not found");}
       				 else {System.out.println(emp);}
       			   }
       			
       			catch(EmployeeNotFoundException e) {System.out.println(e.getMessage());}       			
       			break;
       			
       		case 3:
       			System.out.println("Enter Id:");
       			
       			emp.setId(sc.nextInt());
       					
       				  System.out.println("Enter updated name");
       				 		emp.setName(sc.next());
       				 		
       				 		session.merge(emp);
       				 		transaction.commit();
       				break;
       			   
       		case 4:
       			System.out.println("Enter the id");
       			
       			emp=session.get(EmployeeCG.class, sc.nextInt());
       			try {
       				 if(emp==null) 
       				 {throw new EmployeeNotFoundException("Employee Not found");}
       				 else {
       					 session.remove(emp.getAddress());
       					 session.remove(emp);
            			transaction.commit();
            			System.out.println("Employee deleted with "+emp.getId());
       				 }
       			   }
       			
       			catch(EmployeeNotFoundException e) {System.out.println(e.getMessage());}
       			break;
       			
           	 
       	}// Switch case
       	
       	System.out.println("1 to continue and any to exit");
       	next=sc.nextInt();


       }while(next==1);
       System.out.println("You have exited from system");           
       session.close();
        
    }
}
