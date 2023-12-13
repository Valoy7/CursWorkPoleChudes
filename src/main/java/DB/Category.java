package DB;

public class Category {
    private String category_name;

    @Override
    public String toString() {
        return category_name;
    }

    public Category(String category_name) {
        this.category_name = category_name;
       // this.password = password;
    }

    public Category() {
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }


}
