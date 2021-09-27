package com.Hibernate.SignUp.Dao;
import java.util.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.Hibernate.SignUp.Pojo.SignUpPojo;
public class SignUpDao {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scobj = new Scanner(System.in);
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfref = cfg.buildSessionFactory();
		Session sref = sfref.openSession();
		Transaction tref = sref.beginTransaction();
		
		System.out.println("Are you an existing user? " + "\n" + "1. 'Yes' - LogIn" + "\n" + "2. 'No' - SignUp");
		System.out.println("Enter your choice");
		int uchoice = scobj.nextInt();
		if(uchoice == 1) {
			System.out.println("Enter email:");
			String email = scobj.next();
			System.out.println("Enter Password");
			String pass = scobj.next();
			Query qref = sref.createQuery("from SignUpPojo where email = :eref and password = :pref");
			qref.setParameter("eref", email);
			qref.setParameter("pref", pass);
			List lref = qref.list();
			Iterator itref = lref.iterator();
			if(itref.hasNext()) {
				SignUpPojo sp = (SignUpPojo)itref.next();
				int uid = sp.getId();
				System.out.println("Valid details");
				System.out.println("What operation woul d you like to perform?" +"\n"+"1.Select" + "\n" +"2.Update" + "\n"+ "3.Delete");
				int ochoice = scobj.nextInt();
				if(ochoice == 1) {
				Query oref = sref.createQuery("from SignUpPojo where id = :uid");
				oref.setParameter("uid", uid);
				List lsref = oref.list();
				Iterator itsref = lref.iterator();
				while(itsref.hasNext()) {
					
					SignUpPojo uref = (SignUpPojo)itsref.next();
					System.out.println(uref.getName() + " " + uref.getEmail() + " " + uref.getPassword() +" " + uref.getSalary());
					
				}
				}
				if(ochoice == 2) {
					Query uqref = sref.createQuery("update from SignUpPojo set name = :nref, salary = :slref where id = :uid ");
					System.out.println("Enter name and salary to Update in DataBase!");
					String nref = scobj.next();
					float slref = scobj.nextFloat();
					uqref.setParameter("nref", nref);
					uqref.setParameter("slref", slref);
					uqref.setParameter("uid", uid);
					uqref.executeUpdate();
					System.out.println("values Updated!");
					
				}
				if(ochoice ==3) {
					Query dqref = sref.createQuery("delete from SignUpPojo where id = :uid");
					
					System.out.println("Your data is deleted from the table");
					
				}
			}
			else {
				System.out.println("Invalid Details!");
			}
		}
		if(uchoice ==2) {
			System.out.println("Welcome to Sign Up Page");
			System.out.println("Enter id, name , email, password, salary");
			int id = scobj.nextInt();
			String name = scobj.next();
			String email = scobj.next();
			String password = scobj.next();
			Float salary = scobj.nextFloat();
			SignUpPojo spobj = new SignUpPojo();
			spobj.setId(id);
			spobj.setName(name);
			spobj.setEmail(email);
			spobj.setPassword(password);
			spobj.setSalary(salary);
			sref.save(spobj);
			System.out.println("Sign Up done!");
			
		}
		
		tref.commit();
		sref.close();
		sfref.close();
		
		
	}

}
