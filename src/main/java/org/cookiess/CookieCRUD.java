package org.cookiess;

import com.mongodb.Block;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Updates.set;


public class CookieCRUD {

    private MongoClient mongoClient;
    private final MongoCollection<Document> cookies;

    /**
     *
     * Exmple of a simple CRUD module based on cookies!
     *
     * @param cookies MongoDB Collection
     */
    public CookieCRUD(MongoCollection<Document> cookies) {
        this.cookies = cookies;
    }



    /**
     *
     * Create a new Document based on the provided variables
     *
     * @param cookieId custom ID of a Cookie
     * @param name name of the cookie
     * @param quantity quantity of a Cookie
     * @param ingredients array of different ingredients
     */
    public void create(String cookieId, String name, String quantity,
                       List<String> ingredients) {
        try {
            cookies.insertOne(new Document()
                            .append("cookieId", cookieId).append("name", name)
                            .append("quantity", quantity).append("ingredients", ingredients));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Done

    /**
     *
     * Read all of the documents stored in the collection of Cookies
     *
     */
    public void readAll() {
        try {
            FindIterable<Document> iter = cookies.find();

            iter.forEach(new Block<Document>() {
                @Override
                public void apply(Document doc) {
                    System.out.println(doc);
                }
            });

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     *
     * Updates the Cookie fields: CookieID, Name, Quantity
     *
     * @param cookieId ID of an COOKIE
     * @param updateCat Category which you want to update
     * @param update New entry to be placed
     */
    public void update(String cookieId, String updateCat, String update) {

        try {
            cookies.findOneAndUpdate(
                    new Document("cookieId", cookieId),
                    set(updateCat, update)
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * Updates the ingredient
     *
     * @param cookieId ID of an COOKIE
     * @param ingredients Selected ingredient
     */
    public void updateIng(String cookieId, List<String> ingredients) {

        try {
            cookies.findOneAndUpdate(
                    new Document("cookieId", cookieId),
                    set("ingredients", ingredients)
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *  Deletes the document from collection of cookies
     *
     * @param cookieId ID of the Document in MongoDB
     */
    public void delete(String cookieId) {

        try {
            cookies.deleteOne(new Document("cookieId", cookieId));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}