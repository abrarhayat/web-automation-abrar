package automation.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import automation.poms.shopApp.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author abrar
 * since 9/9/20
 */

public class ParseBooksFromJSON {
    public static List<Book> getBooks(String jsonDir) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        List<Book> books = new ArrayList<>();
        JSONArray array = (JSONArray) parser.parse(
                new FileReader(jsonDir));
        String[] keys = {"title", "imageLocation", "price", "description"};

        for (Object o : array) {
            JSONObject jsonObject = (JSONObject) o;
            String title = (String) jsonObject.get("title");
            String imageURL = (String) jsonObject.get("imageLocation");
            String price = (String) jsonObject.get("price");
            String description = (String) jsonObject.get("description");
            Book book = new Book(title, imageURL, price, description);
            books.add(book);
        }
        return books;
    }
}
