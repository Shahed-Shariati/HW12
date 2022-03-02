package utility;


import database.DatabaseConnection;
import model.*;
import service.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utility.Menu.*;

public class Application {
    private Scanner input = new Scanner(System.in);
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private UserService userService = new UserService(connection);
    private CustomerService customerService = new CustomerService(connection);
    private AdministratorService administratorService = new AdministratorService(connection);
    private CategoryService categoryService = new CategoryService(connection);
    private ProductService productService = new ProductService(connection);
    private ShoppingService shoppingService = new ShoppingService(connection);
    private ItemCartService itemCartService = new ItemCartService(connection);
    private OrderService orderService = new OrderService(connection);
    private ItemOrderService itemOrderService = new ItemOrderService(connection);
    public Application() {

        runApplication();
    }

    public void runApplication(){
        while (true){
            loginMenu();
            System.out.println("Choice");
            String input = getUserInput();
           switch (input){
               case "1":
                  login();
                   break;
               case "2":
                   singUp();
                   break;
               case "3":
                   return;
               default:
                   System.out.println();
           }
        }
    }

    private void login() {
        System.out.println("Enter user name and password (admin admin)");
        String[] input = getUserInput().trim().split(" +");
        try{
            if(input[0].equalsIgnoreCase("back")){
                System.out.println();
            }else if(input.length == 2){
               User user = userService.login(input[0],input[1]);
                if(user.getRole() == 1){
                   administratorMenu();
                }else if(user.getRole() == 2){
                    try{
                        Customer customer = customerService.findByUserId(user.getId());
                        customerMenu(customer);
                    }catch (UserNotFoundException e){
                        System.out.println(e.getMessage());
                    }

                }

            }else {
                System.out.println();
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("your input is wrong");
        }catch (UserNotFoundException e){
            System.out.println("there is no user with this username");
        }catch (NullPointerException e){
            System.out.println("nullpoint ");
        }catch (ValidationLengthPhoneNumber e){
            System.out.println("phone number length is wrong");
        }catch (ValidationDigitPhoneNumber e){
            System.out.println("phone number is wrong");
        }

    }


    private void singUp(){
        System.out.println();
        System.out.println("Enter your name");
        String firstName = getUserInput();
        System.out.println("Enter your Last Name");
        String lastName = getUserInput();
        System.out.println("enter your phone number");
        String phoneNumber = getUserInput();
        System.out.println("enter your address");
        String address = getUserInput();
        System.out.println("Enter your username: ");
        String username = getUserInput();
        System.out.println("Enter your password: ");
        String password = getUserInput();
        System.out.println("Enter your balance: ");
        String balance = getUserInput();
        try {
          int userId = userService.save("2",firstName,lastName,phoneNumber,address,username,password);
          if(userId != 0){
           customerService.add(userId,balance);
              System.out.println("User Create Success");
          }else {
              System.out.println("Can t create user");
          }

        }catch (ValidationDigitPhoneNumber e){
            System.out.println("---------phone number is not digit------------");
        }catch (ValidationLengthPhoneNumber e)
        {
            System.out.println("---------phone number length is not valid------");
        }catch (NumberFormatException e){
            System.out.println("-------------your role or balance is wrong----------------");
        }

    }

    private void administratorMenu(){
        while (true){
           adminMenu();
            String input = getUserInput();
            switch (input){
                case "1":
                   addProduct();
                    break;
                case "2":
                    showParentCategory();
                    editProduct();
                    break;
                case "3":
                    addCategory();
                    break;
                case "4":
                   addSubCategory();
                    break;
                case "5":
                   showParentCategory();
                   showProductsByParentCategory();
                   break;
                case "6":
                    return;
            }
        }
    }

   private void customerMenu(Customer customer){
        while (true){
            Menu.customerMenu();
            String input = getUserInput();
            switch (input){
                case "1":
                   showParentCategory();
                    showProductsByParentCategory();
                    break;
                case "2":
                    addProductToShoppingCart(customer);
                    break;
                case "3":
                    findShoppingCartBtCustomerId(customer);
                    editRemoveFromShoppingCart();
                    break;
                case "4":
                   confirmShoppingCart(customer);
                    break;
                case "5":
                    return;
            }
        }
   }
    private void addCategory(){
        System.out.println("Enter your category name:");
        String categoryName = getUserInput();
        if(categoryName.equalsIgnoreCase("Back")){
            System.out.println();
        }else {
            try{
                administratorService.saveCategoryParent("1", categoryName);
            }catch (NumberFormatException e){
                System.out.println("Id is wrong");
            }
        }
    }
    private void addSubCategory(){
        showParentCategory();
        System.out.println("Choice your parent category");
        String parentId = getUserInput();
        System.out.println("Enter your category name");
        String categoryName = getUserInput();
        try{
             administratorService.saveSubCategory("1",categoryName,parentId);
        }catch (NumberFormatException e){
            System.out.println("id is wrong");
        }catch (CategoryParentNotFound e){
            System.out.println(" Parent Category is not found");
        }


    }
    private void showParentCategory(){
        try {
            List<Category> categories = categoryService.findAll();
            for (Category item:categories) {
                System.out.println(item);

            }
        }catch (CategoryListNotFound e){
            System.out.println("List Not Found");
        }


    }
    private void addProduct(){
        System.out.println("Enter product name");
        String name = getUserInput();
        System.out.println("Enter price");
        String price = getUserInput();
        System.out.println("Enter stock");
        String stock = getUserInput();
        showSubCategory();
        System.out.println("Choice category");
        String categoryId = getUserInput();
        try {
         productService.save(name,price,stock,categoryId);
        }catch (NumberFormatException e){
            System.out.println("input is wrong");
        }catch (CategoryNotFound e){
            System.out.println("Category not found");
        }


    }
    private void showSubCategory(){
        try {
            List<Category> categories = categoryService.findSubCategory();
            for (Category item:categories) {
                System.out.println(item);

            }
        }catch (CategoryListNotFound e){
            System.out.println("List Not Found");
        }
    }

    private void editProduct(){
      showProductsByParentCategory();
      try {
          System.out.println("choice id product to edit");
          int productId = Integer.parseInt(getUserInput());
          Product product = productService.find(productId);
          System.out.println("Enter stock");
           int stock =Integer.parseInt(getUserInput());
          System.out.println("Enter new Price");
          double price = Double.parseDouble(getUserInput());
          product.setPrice(price);
             product.setStock(product.getStock()+stock);
             productService.upDate(product);
      }catch (NumberFormatException e){
          System.out.println("Id or stock is wrong");
      }catch (ProductNotFoundException e){
          System.out.println("product not found");
      }
    }
    private void showProductsByParentCategory(){
        try{
            System.out.println("choice id category");
            String categoryId = getUserInput();
            int intId = Integer.parseInt(categoryId);
            List<Product> products =  productService.findAllByCategoryId(intId);
            for (Product item:products) {
                System.out.println(item);

            }
        }catch (NumberFormatException e){
            System.out.println("category id is wrong");
            return;
        }catch (ProductListNotFoundException e){
            System.out.println("List is empty");
            return;
        }
    }
    private void showProducts(){
      List<Product> products =  productService.findAll();
      if(products != null)
      {
          for (Product item:products) {
              System.out.println(item);

          }
      }else {
          System.out.println("list is empty");
      }

    }
     private void addProductToShoppingCart(Customer customer){


          ShoppingCart shoppingCart = new ShoppingCart(null, customer, 0,1);
          customer.getShoppingCarts().add(shoppingCart);
          int shoppingCartId = saveShoppingCart(shoppingCart);
          shoppingCart.setId(shoppingCartId);
          while (true) {
              try {
              showShoppingCart(shoppingCart);
              showParentCategory();
              showProductsByParentCategory();
              System.out.println("Choice product id to add shopping cart");
              Integer productId = Integer.parseInt(getUserInput());
              Product product = productService.find(productId);
              System.out.println("Enter product quantity");
              Integer quantity = Integer.parseInt(getUserInput());
              if (quantity > product.getStock()) {
                  System.out.println("out of range");
              }else{
                  product.setStock(product.getStock() - quantity);
                  Double sumItem = product.getPrice() * quantity;
                  ItemCart itemCart = new ItemCart(null, product, shoppingCart, quantity, sumItem);
                  shoppingCart.setSum(shoppingCart.getSum() + sumItem);
                  if (shoppingCart.getItemCarts().contains(itemCart)) {
                      int indexOfItemCart=shoppingCart.getItemCarts().indexOf(itemCart);
                      int newQuantity = shoppingCart.getItemCarts().get(indexOfItemCart).getQuantity() + quantity;
                      shoppingCart.getItemCarts().get(indexOfItemCart).setQuantity(newQuantity);
                      double newSum = shoppingCart.getItemCarts().get(indexOfItemCart).getSum() + sumItem;
                      shoppingCart.getItemCarts().get(indexOfItemCart).setSum(newSum);
                      itemCartUpdate(shoppingCart.getItemCarts().get(indexOfItemCart));
                     // System.out.println(shoppingCart.getItemCarts().get(indexOfItemCart).getQuantity());

                  } else {
                      shoppingCart.getItemCarts().add(itemCart);
                      saveItemCart(itemCart);
                  }

                  productUpdate(product);
                  shoppingCartUpdate(shoppingCart);
                  System.out.println("you want continue and add product to shopping list Y/n");
                  String input = getUserInput();
                  if (input.equalsIgnoreCase("n")) {
                      showShoppingCart(shoppingCart);
                      return;
                  }
              }

          }catch (NumberFormatException e){
              System.out.println("Input is wrong");
              continue;
          }catch (ProductNotFoundException e){
              System.out.println(e.getMessage());
          }catch (ProductListNotFoundException e){
                  System.out.println(e.getMessage());
                  continue;
          }catch (ProductStockException e){
                  System.out.println(e.getMessage());
              }
        }

     }



     private int saveItemCart(ItemCart itemCart){
       return itemCartService.save(itemCart);

     }
     private int saveShoppingCart(ShoppingCart shoppingCart){
         return shoppingService.save(shoppingCart);
     }
     private void productUpdate(Product product){
        productService.upDate(product);
     }
     private void shoppingCartUpdate(ShoppingCart shoppingCart)
     {
         shoppingService.upDate(shoppingCart);
     }
     private void itemCartUpdate(ItemCart itemCart){
        itemCartService.upDate(itemCart);
     }

     private void showShoppingCart(ShoppingCart shoppingCart){
         System.out.println("--------------------shopping cart---------------------");
         for (ItemCart item:shoppingCart.getItemCarts()) {
             System.out.println(item);
         }
         System.out.println("------------------------------------------------------------------------------");
         System.out.println("Total                                                " + shoppingCart.getSum());
         System.out.println("------------------------------------------------------------------------------");
         System.out.println();
     }
     private void findShoppingCartBtCustomerId(Customer customer){

            List<ShoppingCart> shoppingCarts  =  shoppingService.findByCustomerId(customer.getId());
            for (ShoppingCart item:shoppingCarts ) {
                System.out.println(item);
            }


     }
     private void editRemoveFromShoppingCart(){
         System.out.println("Enter id shopping cart");
         try {
             int shoppingCartId = Integer.parseInt(getUserInput());
             ShoppingCart shoppingCart = shoppingService.find(shoppingCartId);
             while (true){
                 editRemove();
                 String input = getUserInput();
                 switch (input){
                     case "1":
                         editShoppingCartItem(shoppingCart);
                         break;
                     case "2":
                         removeShoppingCartItem(shoppingCart);
                         break;
                     case "3":
                         return;

                 }
             }

         }catch (NumberFormatException e){
             System.out.println("shopping cart is wrong");
         }catch (ItemCartListNotFoundException e){
             System.out.println(e.getMessage());
         }

     }

     private void editShoppingCartItem(ShoppingCart shoppingCart){
         showShoppingCart(shoppingCart);
         try {
             Product product;
             double total = 0;
             System.out.println("enter id item to edit");
             int itemId = Integer.parseInt(getUserInput());
             increaseDecreaseItem();
             String input = getUserInput();
             System.out.println("enter quantity to decrease");
             int quantity = Integer.parseInt(getUserInput());
             if(input.equalsIgnoreCase("1")){
                 for (ItemCart item:shoppingCart.getItemCarts()) {
                     if(item.getId() == itemId){
                         item.setQuantity(item.getQuantity() + quantity);
                         item.setSum(item.getProduct().getPrice() * item.getQuantity());
                         product = item.getProduct();
                         product.setStock(product.getStock() - quantity);
                         item.setShoppingCart(shoppingCart);
                         itemCartUpdate(item);
                         productUpdate(product);
                     }
                     total = total + item.getSum();
                 }
             }else if(input.equalsIgnoreCase("2")){
                 for (ItemCart item:shoppingCart.getItemCarts()) {
                     if(item.getId() == itemId){
                         item.setQuantity(item.getQuantity() - quantity);
                         item.setSum(item.getProduct().getPrice() * item.getQuantity());
                         product = item.getProduct();
                         product.setStock(product.getStock() + quantity);
                         item.setShoppingCart(shoppingCart);
                         itemCartUpdate(item);
                         productUpdate(product);
                     }
                     total = total + item.getSum();
                 }
             }


             shoppingCart.setSum(total);
             shoppingCartUpdate(shoppingCart);


         }catch (NumberFormatException e){
             System.out.println("Id is wrong");
         }catch (ProductStockException e){
             System.out.println(e.getMessage());
         }

     }
     private void removeShoppingCartItem(ShoppingCart shoppingCart){
        showShoppingCart(shoppingCart);
        try {
            double total = 0;
            ItemCart itemCartRemove = new ItemCart();
            Product product;
            System.out.println("choice id to remove");
            int itemId = Integer.parseInt(getUserInput());
            for (ItemCart item:shoppingCart.getItemCarts()) {
                if(item.getId() == itemId){
                  itemCartRemove = item;
                  product = item.getProduct();
                  product.setStock(product.getStock() + item.getQuantity());
                  productUpdate(product);
                  removeItem(item);
                }else {
                    total = total + item.getSum();
                }
            }
            shoppingCart.setSum(total);
            shoppingCart.getItemCarts().remove(itemCartRemove);
            shoppingCartUpdate(shoppingCart);

        }catch (NumberFormatException e){
            System.out.println(" input Wrong");
        }
     }
     private void removeItem(ItemCart itemCart){
        itemCartService.delete(itemCart.getId());
     }
     private void confirmShoppingCart(Customer customer){

         try {
             findShoppingCartBtCustomerId(customer);
             System.out.println("choice shopping cart");
             int shoppingCartId = Integer.parseInt(getUserInput());
             ShoppingCart shoppingCart = shoppingService.find(shoppingCartId);
             showShoppingCart(shoppingCart);
             System.out.println("your balance is                 " + customer.getBalance());
             System.out.println("Do you want confirm y/n");
             String input = getUserInput();
             if(input.equalsIgnoreCase("y")){
                 if(shoppingCart.getSum() < customer.getBalance()) {
                     confirm(shoppingCart,customer);
                     double newBalance = customer.getBalance() - shoppingCart.getSum();
                     customer.setBalance(newBalance);
                     customerUpDate(customer);
                 }else{
                     System.out.println("your balance is not enough");
                 }
             }else if(input.equalsIgnoreCase("n")){
                 System.out.println();
             }else {
                 System.out.println("input is wrong");
             }
         }catch (NumberFormatException e){
             System.out.println(" input is wrong");
         }catch (ShoppingCartNotFound e){
             System.out.println(e.getMessage());
         }catch (ItemCartListNotFoundException e){
             System.out.println(e.getMessage());
         }catch (ShoppingCartListNotFound e){
             System.out.println(e.getMessage());
         }catch (Exception e){
             System.out.println("Cant up date customer");
         }
    }
    private void confirm(ShoppingCart shoppingCart,Customer customer){
        Order order  = new Order(null,customer,shoppingCart.getSum(),"Pending");
        int orderId = saveOrder(order);
        order.setId(orderId);
        ArrayList<ItemOrder> itemOrders = new ArrayList<>();
        for (ItemCart item:shoppingCart.getItemCarts()) {
            itemOrders.add(new ItemOrder(null,item.getProduct(),order,item.getQuantity(),item.getSum()));
        }
        order.setItemOrders(itemOrders);
        saveOrderItem(order.getItemOrders());
        shoppingCart.setStatus(0);
        shoppingCartUpdate(shoppingCart);
    }
    private int saveOrder(Order order){
       return orderService.save(order);
    }
    private void saveOrderItem(List<ItemOrder> itemOrders){
        for (ItemOrder item:itemOrders) {
         itemOrderService.save(item);
        }
    }
    private void customerUpDate(Customer customer){
      customerService.upDate(customer);
    }
    private String getUserInput() {
        return input.nextLine().trim();
    }
}
