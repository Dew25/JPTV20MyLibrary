/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv20mylibrary;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tools.SaverToFile;



/**
 *
 * @author Melnikov
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private Keeping keeper = new SaverToFile();
   // private Keeping keeper = new SaverToBase();
   // private SaverToBase saverToBase = new SaverToBase();

    public App() {
        books = keeper.loadBooks();
        readers = keeper.loadReaders();
        histories = keeper.loadHistories();
    }
    
    public void run(){
        String repeat = "r";
        do{
            System.out.println("Выберите номер задачи: ");
            System.out.println("0: Выход из программы");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Список читателей");
            System.out.println("5: Выдать книгу");
            System.out.println("6: Список выданных книг");
            System.out.println("7: Возврат книги");
            int task = getNumber();
            switch (task) {
                case 0:
                    repeat="q";
                    System.out.println("Пока! :)");
                    break;
                case 1:
                    System.out.println("---- Добавление книги ----");
                    addBook();
                    System.out.println("-----------------------");
                    break;
                case 2:
                    System.out.println("---- Список книг -----");
                    printListBooks();
                    System.out.println("-----------------------");
                    break;
                case 3:
                    addReader();
                    break;
                case 4:
                    printListReaders();
                    break;
                case 5:
                    addHistory();
                    System.out.println("-----------------------");
                    break;
                case 6:
                    System.out.println("---- Список выданных книг -----");
                    printGivenBooks();
                    System.out.println("-----------------------");
                    break;
                case 7:
                    System.out.println("---- Возврат книги -----");
                    returnBook();
                    break;
                default:
                    System.out.println("Введите номер из списка!");;
            }
            
        }while("r".equals(repeat));
    }
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\", для продолжения любой другой символ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
      return false;
    }
    private void returnBook(){
        System.out.println("Вернуть книгу: ");
        if(quit()) return;
        Set<Integer> numbersGivenBooks = printGivenBooks();
        if(numbersGivenBooks.isEmpty()){
            return;
        }
        int historyNumber = insertNumber(numbersGivenBooks);
        Calendar c = new GregorianCalendar();
        histories.get(historyNumber - 1).setReturnedDate(c.getTime());
//      Здесь объясняется что значит передача по ссылке в Java
//      https://coderoad.ru/40480/%D0%AD%D1%82%D0%BE-Java-pass-by-reference-%D0%B8%D0%BB%D0%B8-pass-by-value
//      Постарайтесь понять почему работает неправильно следующий код.
//         histories.get(historyNumber - 1).getBook().setCount(
//                histories.get(historyNumber-1).getBook().getCount() + 1
//         );
        
//      а следующий работает правильно.
        for (int i = 0; i < books.size(); i++) {
          if(books.get(i).getBookName().equals(histories.get(historyNumber-1).getBook().getBookName())){
            books.get(i).setCount(books.get(i).getCount()+1);
          }
        }
        keeper.saveBooks(books);
        keeper.saveHistories(histories);
    }
    
    private Set<Integer> printGivenBooks(){
        System.out.println("Список выданных книг: ");
        Set<Integer> setNumberGivenBooks = new HashSet<>();
        for (int i = 0; i < histories.size(); i++) {
            //если history не null и книга не возварщена и книг в наличии меньше
            // чем записано в quantity -
            // печатаем книгу
            if(histories.get(i) != null 
                    && histories.get(i).getReturnedDate() == null
                    && histories.get(i).getBook().getCount()
                        <histories.get(i).getBook().getQuantity()
                    ){
                System.out.printf("%d. Книгу: %s читает %s %s%n",
                        i+1,
                        histories.get(i).getBook().getBookName(),
                        histories.get(i).getReader().getFirstname(),
                        histories.get(i).getReader().getLastname()
                );
                setNumberGivenBooks.add(i+1);
            }
        }
        if(setNumberGivenBooks.isEmpty()){
            System.out.println("Выданных книг нет");
        }
        return setNumberGivenBooks;
    }
    private void addBook(){
        Book book = new Book();
        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        System.out.print("Введите год публикации книги: ");
        book.setPublishedYear(getNumber());
        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());
        System.out.println("Автор книги: ");
        System.out.print("Введите количество авторов: ");
        int countAutors = getNumber();
        Author[] authors = new Author[countAutors];
        for (int i = 0; i < authors.length; i++) {
            Author author = new Author();
            System.out.print("Введите имя автора "+(i+1)+": ");
            author.setFirstname(scanner.nextLine());
            System.out.print("Введите фамилию автора: ");
            author.setLastname(scanner.nextLine());
            System.out.print("Введите год рождения автора: ");
            author.setBirthYear(getNumber());
            authors[i] = author;
        }
        
        book.setAuthor(authors);
        books.add(book);
        keeper.saveBooks(books);
       
    }
    private void addReader(){
        System.out.println("---- Добавление читателя ----");
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        readers.add(reader);
        keeper.saveReaders(readers);
        System.out.println("-----------------------");
    }

    private void addHistory() {
        History history = new History();
        /**
         * 1. Вывести пронумерованный список книг библиотеки
         * 2. Попросить пользователя выбрать номер книги
         * 3. Вывести пронумерованный список читателей
         * 4. Попрость пользователя выбрать номер читателя
         * 5. Генерироват текущую дату
         * 6. инициировать (задать состояние) объект history
         */
        System.out.println("------------ Выдать книгу читателю ----------");
        System.out.println("Список книг: ");
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.print("Введите номер книги: ");
        int numberBook = insertNumber(setNumbersBooks);
        System.out.println("Список читателей: ");
        Set<Integer> setNumbersReaders = printListReaders();
        System.out.print("Введите номер читателя: ");
        int numberReader = insertNumber(setNumbersReaders);
        history.setBook(books.get(numberBook-1));
        if(books.get(numberBook - 1).getCount() > 0){
            books.get(numberBook - 1).setCount(books.get(numberBook - 1).getCount()-1);
        }
        history.setReader(readers.get(numberReader-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        keeper.saveBooks(books);
        histories.add(history);
        keeper.saveHistories(histories);
        System.out.println("========================");
    }

    private Set<Integer> printListBooks() {
        System.out.println("Список книг: ");
        books = keeper.loadBooks();
        Set<Integer> setNumbersBooks = new HashSet<>();
        for (int i = 0; i < books.size(); i++) {
            StringBuilder cbAutors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().length; j++) {
                cbAutors.append(books.get(i).getAuthor()[j].getFirstname())
                        .append(" ")
                        .append(books.get(i).getAuthor()[j].getLastname())
                        .append(". ");
            }
            if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%d. %s. %s В наличии экземпряров: %d%n"
                        ,i+1
                        ,books.get(i).getBookName()
                        ,cbAutors.toString()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(i+1);
            }else if(books.get(i) != null){
                System.out.printf("%d. %s. %s Нет в наличии. Будет возвращена: %s%n"
                        ,i+1
                        ,books.get(i).getBookName()
                        ,cbAutors.toString()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        return setNumbersBooks;
    }
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    } 
   
    private int getNumber() {
        do{
            try {
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            } catch (Exception e) {
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        do{
            int historyNumber = getNumber();
            if(setNumbers.contains(historyNumber)){
                return historyNumber;
            }
            System.out.println("Попробуй еще раз: ");
        }while(true);
    }
    private String getReturnDate(Book book){
        
        for (int i = 0; i < histories.size(); i++) {
            if(book.getBookName().equals(histories.get(i).getBook().getBookName())){
                LocalDate localGivenDate = histories.get(i).getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        }
        return "";
    }
    private Set<Integer> printListReaders() {
        Set<Integer> setNumbersReaders = new HashSet<>();
        System.out.println("Список читателей: ");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s%n"
                        ,i+1
                        ,readers.get(i).toString()
                );
                setNumbersReaders.add(i+1);
            }
        }
        return setNumbersReaders;
    }
}
