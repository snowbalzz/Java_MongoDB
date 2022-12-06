package org.cookiess;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cookie factory CRUD!
 */
public class App {


    public static void main(String[] args) {

        ConsoleWriter writer = new ConsoleWriter();
        ConsoleReader reader = new ConsoleReader();
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        //URI to connect to the database!
        // Provided your own!!
        String connectionUri = "";

        //Connecting ot the DB
        try (MongoClient mongoClient = MongoClients.create(connectionUri)) {


            MongoCollection<Document> collection = mongoClient.getDatabase("xmas").getCollection("cookies");

            //Creation of the CRUD Class
            CookieCRUD cookies = new CookieCRUD(collection);


            //Everything bellow is not relevant for the operations with MONGODB
            List<String> normal =  List.of("Flour", "Eggs", "Butte", "Sugar");
            List<String> vanilla =  List.of("Flour", "Eggs", "Butte", "Sugar", "Vanilla");
            List<String> chocolat =  List.of("Flour", "Eggs", "Butte", "Sugar", "Chocolat");
            List<String> raspberry =  List.of("Flour", "Eggs", "Butte", "Sugar", "Raspberry");

            //This part of the code was used to DEMONSTRATE CRUD MODULE in action with MONGODB
            while (true) {
                writer.write("Please type: 'c' for create a cookie, 'r' to the read all cookie collection, 'u' to update a cookie, 'd' to delete cookie");
                String action = reader.readLine();

                if (action.equals("c")) {

                    writer.write("Write Cookie ID!");
                    String cId = reader.readLine();

                    writer.write("Write the name of the Cookie!");
                    String cName = reader.readLine();

                    writer.write("Write how much cookies you want!");
                    String cQuantity = reader.readLine();

                    writer.write("Write which ingredients you would like to use: Normal(1), Vanilla(2), Chocolat(3), Raspberry(4)!");
                    String ing = reader.readLine();

                    List<String> ingriedent = List.of("Flour", "Eggs", "Butte", "Sugar");

                    if(ing.equals("1")){
                        ingriedent = normal;
                    }else if(ing.equals("2")){
                        ingriedent = vanilla;
                    }else if (ing.equals("3")){
                        ingriedent = chocolat;
                    }else if (ing.equals("4")){
                        ingriedent = raspberry;
                    }

                    //Calling CREATE function
                    cookies.create(
                            cId,
                            cName,
                            cQuantity,
                            ingriedent
                    );

                    writer.write("Cookie ADDED!");

                } else if (action.equals("r")) {

                    //Calling ReadAll function
                    cookies.readAll();
                    writer.write("Collection of Cookies read!");

                } else if (action.equals("u")) {

                    writer.write("Which cookie do you want to update?");
                    String cId = reader.readLine();

                    writer.write("Which value do you want to update: cookieId(1), name(2), quantity(3), ingredients(4)");
                    String answer = reader.readLine();

                    if(answer.equals("1"))
                    {
                        writer.write("Insert new value!");
                        String update = reader.readLine();

                        cookies.update(cId, answer, update);
                    }
                    else if(answer.equals("2"))
                    {
                        writer.write("Insert new value!");
                        String update = reader.readLine();
                        cookies.update(cId, answer, update);
                    }
                    else if(answer.equals("3"))
                    {
                        writer.write("Insert new value!");
                        String update = reader.readLine();
                        cookies.update(cId, answer, update);
                    }
                    else if(answer.equals("4"))
                    {

                        writer.write("Write which ingredients you would like to use: Normal(1), Vanilla(2), Chocolat(3), Raspberry(4)!");
                        String ing = reader.readLine();

                        List<String> ingriedent = List.of("Flour", "Eggs", "Butte", "Sugar");

                        if(ing.equals("1")){
                            ingriedent = normal;
                        }else if(ing.equals("2")){
                            ingriedent = vanilla;
                        }else if (ing.equals("3")){
                            ingriedent = chocolat;
                        }else if (ing.equals("4")){
                            ingriedent = raspberry;
                        }

                        cookies.updateIng(cId, ingriedent);
                    }

                    writer.write("Cookie UPDATED!");
                } else if (action.equals("d")) {

                    writer.write("Provide cookieID to a cookie you want to delete!");
                    String cookie = reader.readLine();

                    //Calling DELETE function
                    cookies.delete(cookie);

                    writer.write("Cookie DELETED!");
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
