/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv20mylibrary;

import java.util.Scanner;



/**
 *
 * @author Melnikov
 */
public class App {
    
    public void run(){
        String repeat = "r";
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите номер задачи: ");
            System.out.println("0: Выход из программы");
            int task = scanner.nextInt();
            switch (task) {
                case 0:
                    repeat="q";
                    System.out.println("Пока! :)");
                    break;
                default:
                    System.out.println("Введите номер из списка!");;
            }
            
        }while("r".equals(repeat));
    }

        
}
