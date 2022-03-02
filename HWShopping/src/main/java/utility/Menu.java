package utility;

public class Menu {
    private static String dotted = "---------------------------------------------------";
    public static void loginMenu(){
        System.out.println();
        System.out.println("1:Login");
        System.out.println("2:Sing up");
        System.out.println("3:Exit");
        System.out.println(dotted);

    }
    public static void adminMenu(){
        System.out.println();
        System.out.println("1:Add Product");
        System.out.println("2:Add stock Product");
        System.out.println("3:Add Category");
        System.out.println("4:Add sub Category");
        System.out.println("5:Show products list");
        System.out.println("6:Exit");
        System.out.println(dotted);
    }
    public static void customerMenu(){
        System.out.println();
        System.out.println("1:Show products list");
        System.out.println("2:Add product to shopping list");
        System.out.println("3:Edit shopping cart");
        System.out.println("4:confirm");
        System.out.println("5:Exit");
    }

    public static void editRemove(){
        System.out.println();
        System.out.println("1:edit item");
        System.out.println("2:Delete Item");
        System.out.println("3:Exit");
        System.out.println("Choice");
    }
    public static void increaseDecreaseItem(){
        System.out.println();
        System.out.println("1:Increase item");
        System.out.println("2:Decrease Item");
        System.out.println("Choice");
    }
}
