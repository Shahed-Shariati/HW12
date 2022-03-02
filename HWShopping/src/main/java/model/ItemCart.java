package model;

import java.util.Objects;

public class ItemCart {
    private Integer id;
    private Product product;
    private ShoppingCart shoppingCart;
    private Integer quantity;
    private Double sum;

    public ItemCart(Integer id, Product product, ShoppingCart shoppingCart, Integer quantity, Double sum) {
        this.id = id;
        this.product = product;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
        this.sum = sum;
    }

    public ItemCart(Integer id,  Integer quantity, Double sum,Product product) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.sum = sum;
    }

    public ItemCart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCart itemCart = (ItemCart) o;
        return Objects.equals(product, itemCart.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return "ItemCart{" +
                "id=" + id +
                ", product=" + product.getName() +
                ",price=" + product.getPrice() +
                ", quantity=" + quantity +
                ", sum=" + sum +
                '}';
    }
}
