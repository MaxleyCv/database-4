package iot.lviv.ua.view;


import iot.lviv.ua.controller.AbstractController;
import iot.lviv.ua.controller.impl.*;
import iot.lviv.ua.model.entity.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
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
                input = SCANNER.nextLine();
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

        ArrayList<String> parsed = new ArrayList<String>();
        for(int i = 0; i < commands.length; i++){

                if (commands[i] == ' ' || i == commands.length - 1) {
                    String command = "";
                    for (int oneWordIndex = previous; oneWordIndex < i; oneWordIndex++) {
                        command = command + (commands[oneWordIndex]);
                    }
                    previous = i + 1;
                    if (i == commands.length - 1){
                        command = command + commands[i];
                    }
                    parsed.add(command);

                }
        }
        System.out.println(parsed);
        System.out.println(this.filmController.findAll());
        implementQuery(parsed);
    }

    private AbstractController<?> getClassFromString(String a){
        switch (a){
            case "film": return this.filmController;
            case "country": return this.countryController;
            case "review": return this.reviewController;
            case "director": return this.directorController;
            case "actor": return this.actorController;
            default: return null;
        }
    }

    private Object getEntity(String a) throws SQLException {
        switch (a){
            case "film": return this.inputFilm();
            case "country": return this.inputCountry();
            case "review": return this.inputReview();
            case "director": return this.inputDirector();
            case "actor": return this.inputActor();
            default: return null;
        }
    }



    private void implementQuery(ArrayList<String> command) throws SQLException {

        switch (command.get(1)){
            case "GETALL": {
                switch (command.get(0)){
                    case "film": System.out.println(this.filmController.findAll()); break;
                    case "country": System.out.println(this.countryController.findAll()); break;
                    case "review": System.out.println(this.reviewController.findAll()); break;
                    case "director": System.out.println(this.directorController.findAll()); break;
                    case "actor": System.out.println(this.actorController.findAll()); break;
                    default: System.out.println("INVALID CLASS"); break;
                }
                break;
            }
            case "GET": {
                Integer id = (Integer.parseInt(command.get(2)));
                switch (command.get(0)){
                    case "film": System.out.println(this.filmController.findOne(id)); break;
                    case "country": System.out.println(this.countryController.findOne(id)); break;
                    case "review": System.out.println(this.reviewController.findOne(id)); break;
                    case "director": System.out.println(this.directorController.findOne(id)); break;
                    case "actor": System.out.println(this.actorController.findOne(id)); break;
                    default: System.out.println("INVALID CLASS");
                    break;
                }
                break;
            }
            case "UPDATE":{
                Integer id = (Integer.parseInt(command.get(2)));
                switch (command.get(0)){
                    case "film": this.filmController.update(id, (Film) this.getEntity("film")); break;
                    case "country": this.countryController.update(id, (Country) this.getEntity("country")); break;
                    case "review": this.reviewController.update(id, (Review) this.getEntity("review")); break;
                    case "director": this.directorController.update(id, (Director) this.getEntity("director")); break;
                    case "actor": this.actorController.update(id, (Actor) this.getEntity("actor")); break;
                    default: System.out.println("INVALID CLASS");
                    break;
                }
                break;
            }
            case "CREATE":{
                switch (command.get(0)){
                    case "film": this.filmController.create((Film) this.getEntity("film")); break;
                    case "country": this.countryController.create((Country) this.getEntity("country")); break;
                    case "review": this.reviewController.create((Review) this.getEntity("review")); break;
                    case "director": this.directorController.create((Director) this.getEntity("director")); break;
                    case "actor": this.actorController.create((Actor) this.getEntity("actor")); break;
                    default: System.out.println("INVALID CLASS"); break;
                }
                break;
            }
            case "DELETE":{
                Integer id = (Integer.parseInt(command.get(2)));
                Objects.requireNonNull(this.getClassFromString(command.get(0))).delete(id);
                System.out.println("DELETED");
                break;
            }
            default:{
                System.out.println(command.get(1));
                break;
            }
        }
        return;
    }

    public Film inputFilm() throws SQLException {
        Film actor = new Film(null, null, null, null, null, null);
        System.out.print("TITLE: ");
        actor.setTitle(SCANNER.nextLine());
        System.out.print("DESCRIPTION: ");
        actor.setDescription(SCANNER.nextLine());
        System.out.print("YEAR: ");
        actor.setPublishYear(Integer.parseInt(SCANNER.nextLine()));
        System.out.print("COUNTRY ID: ");
        Integer newCountryId = Integer.parseInt(SCANNER.nextLine());
        actor.setCountryOfOrigin(countryController.findOne(newCountryId));
        System.out.print("DIRECTOR ID: ");
        Integer newDirectorId = Integer.parseInt(SCANNER.nextLine());
        actor.setDirector(directorController.findOne(newDirectorId));
        return actor;
    }

    public Country inputCountry(){
        Country actor = new Country(null, null, null);
        System.out.print("NAME: ");
        actor.setName(SCANNER.nextLine());
        System.out.print("PRESIDENT: ");
        actor.setPresident(SCANNER.nextLine());
        return actor;
    }

    public Director inputDirector(){
        Director actor = new Director(null, null, null);
        System.out.print("NAME: ");
        actor.setName(SCANNER.nextLine());
        System.out.print("SURNAME: ");
        actor.setSurname(SCANNER.nextLine());
        return actor;
    }
    public Review inputReview() throws SQLException {
        Review actor = new Review(null, null, null, null, null);
        System.out.print("POINTS: ");
        actor.setPoints(Integer.parseInt(SCANNER.nextLine()));
        System.out.print("TEXT: ");
        actor.setTextOfReview(SCANNER.nextLine());
        System.out.print("FILM ID: ");
        Integer newId = (Integer.parseInt(SCANNER.nextLine()));
        actor.setFilm(filmController.findOne(newId));
        System.out.print("USER ID: ");
        actor.setUserId((Integer.parseInt(SCANNER.nextLine())));
        return actor;
    }

    public Actor inputActor(){
        Actor actor = new Actor(null, null, null, null);
        System.out.print("NAME: ");
        actor.setName(SCANNER.nextLine());
        System.out.print("SURNAME: ");
        actor.setSurname(SCANNER.nextLine());
        System.out.print("APPENDIX: ");
        actor.setAppendix(SCANNER.nextLine());
        return actor;
    }

}

