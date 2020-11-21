package iot.lviv.ua.view;


import iot.lviv.ua.controller.AbstractController;
import iot.lviv.ua.controller.impl.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class View{
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);
    private final ActorController actorController = new ActorController();
    private final FilmController filmController = new FilmController();
    private final ReviewController reviewController = new ReviewController();
    private final DirectorController directorController = new DirectorController();
    private final CountryController countryController = new CountryController();

    public void mainLoop(){
        String input;

        do {
            try {
                this.showBase();
                input = SCANNER.next();
                System.out.println(input);
                this.react(input.toCharArray());
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        } while (SCANNER.hasNext());
    }

    public void showBase(){
        System.out.println("=======================================================================");
        System.out.println("=======================================================================");
        System.out.println("                            CLASSES                                    ");
        System.out.println("=======================================================================");
        System.out.println("  - film                    ");
        System.out.println("  - actor                   ");
        System.out.println("  - country                 ");
        System.out.println("  - director                ");
        System.out.println("  - review                  ");
        System.out.println("=======================================================================");
        System.out.println("                            METHODS                                    ");
        System.out.println("=======================================================================");
        System.out.println("  - GETALL                    ");
        System.out.println("  - GET {index}                  ");
        System.out.println("  - CREATE                  ");
        System.out.println("  - UPDATE {index}                ");
        System.out.println("  - DELETE {index}                  ");
        System.out.println("=======================================================================");
        System.out.println("In order to implement any method type {class} {method} {id} (if needed)");
        System.out.println("EXAMPLE: film GETALL");
        System.out.println("EXAMPLE: director GET 5");
        System.out.println("=======================================================================");
        System.out.println("          PLEASE, FOLLOW THE INSTRUCTIONS IN THE TERMINAL              ");
        System.out.println("=======================================================================");
        System.out.println("                           GOOD LUCK                                   ");
        System.out.println("=======================================================================");
        System.out.println(" ");
        System.out.print("ENTER YOUR COMMAND: ");
    }

    public void react(char[] commands) throws SQLException {
        int previous = 0;
        StringBuilder command = new StringBuilder();
        ArrayList<String> parsed = new ArrayList<String>();
        for(int i = 0; i < commands.length; i++){
                System.out.print(commands[i]);
                if (commands[i] == ' ') {
                    for (int oneWordIndex = previous; oneWordIndex < i; oneWordIndex++) {
                        command.append(commands[oneWordIndex]);
                    }
                    previous = i;
                    parsed.add(command.toString());
                }
        }
        System.out.println(
        this.countryController.findOne(0).toString());
//        AbstractController<?> commandClass = this.getClassFromString("parsed.get(0)");
//        commandClass.findAll();
    }

    private AbstractController<?> getClassFromString(String a){
        if (a.equals("country")) {
            return this.countryController;
        }
        else {
            return this.filmController;
        }
    }


//    private class MethodImplementer<T>{
//
//        public ArrayList<T> findAll(AbstractController<T> controller) throws SQLException {
//            return controller.findAll();
//        }
//
//        public T findOne(AbstractController<T> controller, Integer id) throws SQLException {
//            return controller.findOne(id);
//        }
//
//        public void create(AbstractController<T> controller, T entity) throws SQLException {
//            controller.create(entity);
//        }
//
//        public void update(AbstractController<T> controller, Integer id, T entity) throws SQLException {
//            controller.update(id, entity);
//        }
//
//        public void delete(AbstractController<T> controller, Integer id) throws SQLException {
//            controller.delete(id);
//        }
//
//
//    }


}

