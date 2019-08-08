/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiber.hibernet.maven.demo1.driver;

import com.hiber.hibernet.maven.demo1.dao.HelloWorldDao;
import com.hiber.hibernet.maven.demo1.dao.UserProfileDao;
import com.hiber.hibernet.maven.demo1.model.CompositeKey;
import com.hiber.hibernet.maven.demo1.model.HelloWorld;
import com.hiber.hibernet.maven.demo1.model.UserProfile;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author yash
 */
public class Driver {

    public static void main(String[] args) {
        HelloWorldDao helloWorldDao = new HelloWorldDao();
        UserProfileDao userProfileDao = new UserProfileDao();
        Scanner sc = new Scanner(System.in);
        //getInputsForMessage(helloWorldDao, sc);
        getInputsForUser(userProfileDao,sc);
    }

    public static void getInputsForUser(UserProfileDao userProfileDao, Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the choice:");
            System.out.println("1 to add user:");
            System.out.println("2 to read all user:");
            System.out.println("3 to exit:");

            int n = sc.nextInt();
            switch (n) {
                case 1:
                    String name = new String();
                    String email = new String();
                    Long phone;
                    System.out.println("Enter user detail:");
                    System.out.println("Enter name:");
                    name = sc.next();
                    System.out.println("Enter phone:");
                    phone = sc.nextLong();
                    System.out.println("Enter email:");
                    email = sc.next();
                    if (name == null || email == null || phone == null) {
                        System.out.println("Provide valid details..");
                    } else {
                        CompositeKey ck = new CompositeKey(name,phone);
                        UserProfile up = new UserProfile();
                        up.setCompositeKey(ck);
                        up.setEmail(email);
                        userProfileDao.saveUser(up);
                        System.out.println("Message added..");
                    }
                    break;
                case 2:
                    List<UserProfile> userProfiles = userProfileDao.getUsers();
                    userProfiles.forEach(s -> System.out.println(s.toString()));
                    break;
                case 3:
                    flag = false;
                    break;
            }

        }
    }

    public static void getInputsForMessage(HelloWorldDao helloWorldDao, Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the choice:");
            System.out.println("1 to add message:");
            System.out.println("2 to real all message:");
            System.out.println("3 to exit:");

            int n = sc.nextInt();
            switch (n) {
                case 1:
                    String msg = new String();
                    System.out.println("Enter message:");
                    msg = sc.next();
                    if (msg == null) {
                        System.out.println("Provide valid message..");
                    } else {
                        HelloWorld helloWorld = new HelloWorld(msg);
                        helloWorldDao.saveMessage(helloWorld);
                        System.out.println("Message added..");
                    }
                    break;
                case 2:
                    List<HelloWorld> messages = helloWorldDao.getMessage();
                    messages.forEach(s -> System.out.println(s.getMessage()));
                    break;
                case 3:
                    flag = false;
                    break;
            }

        }
    }

}
