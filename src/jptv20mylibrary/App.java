/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv20mylibrary;

import java.util.Scanner;
import myclasses.Author;
import myclasses.Book;
import myclasses.Reader;



/**
 *
 * @author Melnikov
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private Book[] books = new Book[10];
    private Reader[] readers = new Reader[10];
    public void run(){
        String repeat = "r";
        do{
            System.out.println("Выберите номер задачи: ");
            System.out.println("0: Выход из программы");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Список читателей");
            int task = scanner.nextInt(); scanner.nextLine();
            switch (task) {
                case 0:
                    repeat="q";
                    System.out.println("Пока! :)");
                    break;
                case 1:
                    System.out.println("---- Добавление книги ----");
                    for (int i = 0; i < books.length; i++) {
                        if(books[i] == null){
                            books[i] = addBook();
                            break;
                        }
                    }
                    System.out.println("-----------------------");
                    break;
                case 2:
                    System.out.println("---- Список книг -----");
                    for (int i = 0; i < books.length; i++) {
                        if(books[i] != null){
                            System.out.println(books[i].toString());
                        }
                    }
                    System.out.println("-----------------------");
                    break;
                case 3:
                    System.out.println("---- Добавление читателя ----");
                    for (int i = 0; i < readers.length; i++) {
                        if(readers[i] == null){
                            readers[i] = addReader();
                            break;
                        }
                    }
                    System.out.println("-----------------------");
                    break;
                case 4:
                    System.out.println("---- Список читателей -----");
                    for (int i = 0; i < readers.length; i++) {
                        if(readers[i] != null){
                            System.out.println(readers[i].toString());
                        }
                    }
                    System.out.println("-----------------------");
                    break;
                default:
                    System.out.println("Введите номер из списка!");;
            }
            
        }while("r".equals(repeat));
    }
    private Book addBook(){
        Book book = new Book();
        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        System.out.print("Введите год публикации книги: ");
        book.setPublishedYear(scanner.nextInt()); scanner.nextLine();
        System.out.println("Автор книги: ");
        System.out.print("Введите количество авторов: ");
        int countAutors = scanner.nextInt(); scanner.nextLine();
        Author[] authors = new Author[countAutors];
        for (int i = 0; i < authors.length; i++) {
            Author author = new Author();
            System.out.print("Введите имя автора "+(i+1)+": ");
            author.setFirstname(scanner.nextLine());
            System.out.print("Введите фамилию автора: ");
            author.setLastname(scanner.nextLine());
            System.out.print("Введите год рождения автора: ");
            author.setBirthYear(scanner.nextInt());scanner.nextLine();
            authors[i] = author;
        }
        book.setAuthor(authors);
        
        return book;
    }
    private Reader addReader(){
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        return reader;
    }
        
}
