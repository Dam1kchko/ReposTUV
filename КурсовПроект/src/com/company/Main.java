package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.*;

public class Main {

    public static Library lib1 = new Library();
    public static UsersData Users = new UsersData();
    public static boolean open_flag = false;
    public static String openedFileName;
    public static String filePath;
    public static boolean login_flag = false;
    public static boolean admin_flag = false;
    public static Map.Entry<User, Boolean> currentUser;
    public static void main(String[] args){
        String userSelected;
        do {
            userSelected = MenuData();
            String[] commands = userSelected.split(" ");
            switch (commands[0]) {
                case "books": {
                    switch(commands[1]) {
                        case "all": {
                            lib1.displayAllBooks();
                            break;
                        }
                        case "info": {
                            int isbn;
                            if( commands.length == 2) {
                                out.println("Empty search parameter");
                                Scanner scan = new Scanner(in);
                                out.println("Enter isbn:");
                                isbn = Integer.parseInt(scan.next());
                                out.println( lib1.findByIsbnValue(isbn) );
                            } else {
                                isbn = Integer.parseInt(commands[2]);
                                out.println( lib1.findByIsbnValue(isbn) );
                            }
                            break;
                        }
                        case "find": {
                            lib1.findBy(commands);
                            break;
                        }
                        case "sort": {
                            switch(commands[2]){
                                case "author": {
                                    if( commands.length == 3){
                                        Collections.sort(lib1.getBooksList() , new AuthorComparator() );

                                    } else if( commands[3].equals("asc")){
                                        Collections.sort(lib1.getBooksList() , new AuthorComparator() );

                                    } else if ( commands[3].equals("desc") ){
                                        Collections.sort(lib1.getBooksList() , Collections.reverseOrder(new AuthorComparator() ));

                                    }
                                    break;
                                }
                                case "title": {
                                    if( commands.length == 3){
                                        Collections.sort(lib1.getBooksList() , new TitleComparator() );
                                    } else if( commands[3].equals("asc")){
                                        Collections.sort(lib1.getBooksList() , new TitleComparator() );
                                    } else if ( commands[3].equals("desc") ){
                                        Collections.sort(lib1.getBooksList() , Collections.reverseOrder(new TitleComparator() ));
                                    }
                                    break;
                                }
                                case "year": {
                                    if( commands.length == 3){
                                        Collections.sort(lib1.getBooksList() , new YearComparator() );
                                    } else if( commands[3].equals("asc")){
                                        Collections.sort(lib1.getBooksList() , new YearComparator() );
                                    } else if ( commands[3].equals("desc") ){
                                        Collections.sort(lib1.getBooksList() , Collections.reverseOrder(new YearComparator() ));
                                    }
                                    break;
                                }
                                case "rating": {
                                    if( commands.length == 3){
                                        Collections.sort(lib1.getBooksList() , new RatingComparator() );
                                    } else if( commands[3].equals("asc")){
                                        Collections.sort(lib1.getBooksList() , new RatingComparator() );
                                    } else if ( commands[3].equals("desc") ){
                                        Collections.sort(lib1.getBooksList() , Collections.reverseOrder(new RatingComparator() ));
                                    }
                                    break;
                                }
                            }
                            lib1.displayAllFullBooks();
                            break;
                        }
                        case "view": {
                            break;
                        }
                        case "add": {
                            if( admin_flag ){
                                addBook();
                            }else {
                                out.println("Currently not supported command.");
                            }
                            break;
                        }
                        case "remove": {
                            if( admin_flag) {
                                lib1.displayIsbnNameBooks();
                                Scanner scan = new Scanner(in);
                                out.println("Insert isbn of a book you want to remove");
                                int isbn = scan.nextInt();
                                lib1.deleteBookByIsbn(isbn);
                            } else {
                                out.println("Currently not supported command.");
                            }
                            break;
                        }

                    }
                    break;
                }
                //                    File Functions
                case "open": {
                    open_flag = openFile(commands[1]);
                    if( open_flag ){
                        out.println("The file '" + openedFileName + "' was opened. ");
                    }
                    break;
                }
                case "close": {
                    lib1.emptyTheLibrary();
                    out.println("Successfully closed file " + openedFileName + "\nThe library books were deleted successfully.");
                    open_flag = false;
                    break;
                }
                case "save": {
                    if ( open_flag ){
                        saveInFile(filePath,"not-as");
                    } else {
                        out.println("Currently not supported command.\nOpen a file and try again");
                    }
                    break;
                }
                case "saveas": {
                    if (open_flag) {
                        saveInFile(commands[1],"as");
                    }  else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "help": {
                    if (open_flag) {
                        displayMenu();
                        break;
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "exit": {
                    if (open_flag) {
                        out.println("exiting...");
                        System.exit(0);
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                                   // User interface Functions
                case "login": {
                    if( open_flag && !login_flag) {
                        login();
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "logout": {
                    if( login_flag ) {
                        if( currentUser.getValue() ){
                            admin_flag = false;
                        }
                        login_flag = false;
                        String currUsername = currentUser.getKey().getUsername();
                        out.println("The user '" + currUsername + "' was logged out." );
                        currentUser = null;
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "user": {
                    switch (commands[1]){
                        case "add": {
                            if( admin_flag ){
                                addUser();
                            }else {
                                out.println("Currently not supported command.");
                            }
                            break;
                        }
                        case "remove": {
                            if( admin_flag ){
                                Scanner scan = new Scanner(in);
                                String username;
                                out.println("Enter username for deleting.");
                                username = scan.next();
                                Users.removeUser(username);
                            } else {
                                out.println("Currently not supported command.");
                            }
                            break;
                        }
                    }
                }
            }
        } while (userSelected != "exit") ;
    }

    public static String MenuData(){
        String selection;
        Scanner sc = new Scanner(in);
        displayMenu();
        out.println("Your selected option is: ");
        selection = sc.nextLine();
        return selection;
    }

    public static void displayMenu(){

        out.println("\nType command from available options: ");
        if( !open_flag ){
            out.println("open <file_path> ===> opens <file>");
        }
        if( open_flag ){
            out.println("close ===> closes currently opened file");
            out.println("save ===> saves the currently open file");
            out.println("saveas <file_path> ===> saves the currently open file in <file>");

            out.println("help ===> prints this information");
            out.println("exit ===> exists the program");
            if( login_flag ) {
                out.println("logout ===> logging out of the system.");
            } else {
                out.println("login ===> logging in in the system");
            }
            out.println("books all ===> display all books");
            out.println("books info <_id> ===> find book by id");
            out.println("books find <option> <option_string> ===> find book by 'title,author,keyWords' ");
            out.println("books sort <option_search> <asc,desc> ===> sort the books [asc,desc]");
            if( admin_flag ){
                out.println("\n  ---------Admin Interface---------\n");
                out.println("user add ===> add an user");
                out.println("user remove ===> removes an user");
                out.println("books add ===> add a book in the library");
                out.println("books remove ( by ISBN_value ) ===> remove a book in the library \n");
            }

        }
    }
    public static void login(){

        String username,password;
        Scanner logging_In = new Scanner(in);

        out.println("Please enter your username:");
        username = logging_In.next();

        out.println("Please enter your password:");
        password = logging_In.next();
        Map.Entry<User, Boolean> currTry = Users.loginUser(username,password);
        User loggingUser = new User(username,password);

        if( currTry != null){
            currentUser = currTry;
            if( currTry.getValue() == true){
                admin_flag = true;
                login_flag = true;
                out.println("The user '" + username + "' was successfully logged in as admin.");
            } else {
                login_flag = true;
                out.println("The user '" + username + "' was successfully logged in as user.");
            }
        }
    }

    public static void addUser() {
        String username, password;
        Scanner scanner = new Scanner(in);

        do {
            out.println("Please enter your username:");
            username = scanner.next();
            if( Users.is_taken(username) ) {
                out.println("Username is already taken, type new one.");
            }
        } while ( Users.is_taken(username) );
        out.println("Please enter your password:");
        password = scanner.next();

        Users.addUser(username,password);


    }

    public static void addBook(){
        // initialise the variables and the scanner;
        Scanner scanner = new Scanner(in);
        String title,author,genre,desc,keys;
        int year,isbn;
        double rating;
        String year1;
            //●  заглавие
        out.println("Please enter book's title:");
        title = scanner.nextLine();
            //●  автор
        out.println("Please enter book's author:");
        author = scanner.nextLine();
            //●  жанр
        out.println("Please enter book's genre:");
        genre = scanner.nextLine();
            //●  кратко описание
        out.println("Please enter book's desc:");
        desc = scanner.nextLine();
            //●  година на издаване
        out.println("Please enter book's pubilishing year:");
        year = scanner.nextInt();
            //●  ключови думи
        // Currently not working keywords
        out.println("Please enter book's keywords (type each of them in format ( word1,word2 ) :");
        keys = scanner.nextLine();
            //●  рейтинг
        out.println("Please enter book's rating:");
        rating = scanner.nextDouble();
            //●  уникален номер за библиотеката
        out.println("Please enter book's isnb_value( 0 - 10000) ");
        isbn = scanner.nextInt();

        lib1.addBook(new Book(title,author,genre,desc,year,keys,rating, isbn));
    }

    public static boolean openFile(String oFilePath) {
        boolean result = false;
        try {
            File xmlDoc = new File(oFilePath);
            DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuild = dbFact.newDocumentBuilder();
            Document doc = dBuild.parse(xmlDoc);
            NodeList nList = doc.getElementsByTagName("book");
            for( int i=0; i< nList.getLength(); i++){
                Node nNode = nList.item(i);
                if( nNode.getNodeType() == Node.ELEMENT_NODE ) {
                    Element nElement = (Element) nNode;

                    String title = nElement.getElementsByTagName("title").item(0).getTextContent();
                    out.println("The book '" + title + "' was added to the library");
                    String author = nElement.getElementsByTagName("author").item(0).getTextContent();
                    String genre = nElement.getElementsByTagName("genre").item(0).getTextContent();
                    String description = nElement.getElementsByTagName("description").item(0).getTextContent();
                    int yearOfPublishing = Integer.parseInt(nElement.getElementsByTagName("yearOfPublishing").item(0).getTextContent());
                    String keyWords = nElement.getElementsByTagName("keyWords").item(0).getTextContent();
                    double rating = Double.parseDouble(nElement.getElementsByTagName("rating").item(0).getTextContent());
                    int isbn_value = Integer.parseInt(nElement.getElementsByTagName("isbn_value").item(0).getTextContent());
                    Book newBook = new Book(title,author,genre,description,yearOfPublishing,keyWords,rating,isbn_value);
                    lib1.addBook(newBook);
                    result = true;
                }
            }
            String[] currFilePath =  oFilePath.split("/");
            openedFileName = currFilePath[currFilePath.length -1];
            filePath = oFilePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void saveInFile(String path, String type){
        try {
            File f = new File(path);
            DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuild = dbFact.newDocumentBuilder();
            Document doc = dBuild.newDocument();
            Element root = doc.createElement("booksList");
            List<Book> listSize = lib1.getBooksList();
            for( int i = 0 ; i< listSize.size(); i++){
                Book currBook = listSize.get(i);
                Element book = doc.createElement("book");

                // adding еаch element that way
                Element title = doc.createElement("title");
                Text titleValue = doc.createTextNode(currBook.getTitle());
                title.appendChild(titleValue);

                // adding author
                Element author = doc.createElement("author");
                Text authorValue = doc.createTextNode(currBook.getAuthor());
                author.appendChild(authorValue);

                // adding genre
                Element genre = doc.createElement("genre");
                Text genreValue = doc.createTextNode(currBook.getGenre());
                genre.appendChild(genreValue);

                // adding description
                Element description = doc.createElement("description");
                Text descriptionValue = doc.createTextNode(currBook.getDescription());
                description.appendChild(descriptionValue);

                // adding year
                Element year = doc.createElement("yearOfPublishing");
                Text yearValue = doc.createTextNode(currBook.yearForFile());
                year.appendChild(yearValue);

                // adding rating
                Element rating = doc.createElement("rating");
                Text ratingValue = doc.createTextNode(currBook.ratingForFile());
                rating.appendChild(ratingValue);

                // adding isbn
                Element isbn = doc.createElement("isbn_value");
                Text isbnValue = doc.createTextNode(currBook.isbnForFile());
                isbn.appendChild(isbnValue);

                // adding isbn
                Element keyWords = doc.createElement("keyWords");
                Text keyWordsValue = doc.createTextNode(currBook.keyWordsForFile());
                keyWords.appendChild(keyWordsValue);


                book.appendChild(title); // <book> <title> </title> </book>
                book.appendChild(author); // <book> <title> <author> </book>
                book.appendChild(genre); // <book> ... </book>
                book.appendChild(description); // <book> ... </book>
                book.appendChild(year); // <book> ... </book>
                book.appendChild(keyWords); // <book> ... </book>
                book.appendChild(rating); // <book> ... </book>
                book.appendChild(isbn); // <book> ... </book>

                root.appendChild(book); // <bookslist> <book> </book> </booksList>
            }
            doc.appendChild(root); // adding <booksList> to the document
            DOMSource source = new DOMSource(doc);
            String fillerPath = "booking.xml";
            File file = new File(path);
            Result result = new StreamResult(file);
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source,result);
            String[] currFilePath =  path.split("/");
            switch (type){
                case "not-as": {
                    out.println("Successfully saved " + currFilePath[currFilePath.length-1] + " with path: " + path);
                    break;
                }
                case "as": {
                    out.println("Successfully saved as " + currFilePath[currFilePath.length-1] + " with path: " + path);
                    break;
                }
            }

        } catch (Exception e) {
            out.println("Saving your file caused error.");
        }
    }
}

