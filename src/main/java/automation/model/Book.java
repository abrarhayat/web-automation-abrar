package automation.model;

/**
 * @author abrar
 * since 9/9/20
 */

public class Book {
    private String title;
    private String imageLocation;
    private String price;
    private String description;

    public Book(String title, String imageLocation, String price, String description) {
        this.title = title;
        this.imageLocation = imageLocation;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", imageURL='" + imageLocation + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
