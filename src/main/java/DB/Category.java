package DB;

import java.util.Objects;

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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Category category = (Category) obj;
        return Objects.equals(Const.CATEGORY_NAME, category.getCategoryName());
    }

    private Object getCategoryName() {
        return Const.CATEGORY_NAME;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Const.CATEGORY_NAME);
    }


}
