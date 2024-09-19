package views;

import DAO.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.Userservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomescreen(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 0 to exit");
        int choice = sc.nextInt();
        switch(choice){
            case 1: login();
            break;
            case 2: signUp();
            break;
            case 0: System.exit(0);
            break;
        }

    }
    private void login(){
       Scanner sc=new Scanner(System.in);
       System.out.println("Enter your email: ");
       String email=sc.nextLine();
        try {
            if(UserDAO.isExists(email)){
                String genOTP= GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter your OTP ");
                String otp=sc.nextLine();
                if(otp.equals(genOTP)){
                    System.out.println("OTP verified");
                    new UserView(email).home();
                }
                else{
                    System.out.println("Wrong OTP");
                }
            }else {
                System.out.println("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void signUp(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name ");
        String name=sc.nextLine();
        System.out.println("Enter your email: ");
        String email=sc.nextLine();
        String genotp=GenerateOTP.getOTP();
        System.out.println(" OTP generated ");
        SendOTPService.sendOTP(email,genotp);
        System.out.println("OTP sent");
        System.out.println("Enter your OTP ");
        String otp=sc.nextLine();
        if(otp.equals(genotp)){
            User user=new User(name,email);
            int exist= Userservice.saveUser(user);
            if(exist==0){
                System.out.println("User already exists");
            }
            else{
                System.out.println("User saved succesfully");
            }
        }
        else{
            System.out.println("Wrong OTP");
        }


    }
}
