package utility;

public class Menu {
    private static String dotted = "---------------------------------------------------";
    public static void loginMenu(){
        System.out.println("1:Login");
        System.out.println("2:Sing up");
        System.out.println("3:Exit");
        System.out.println(dotted);

    }
    public static void bossMenu(){
        System.out.println();
        System.out.println("1:Add clerk");
        System.out.println("2:Delete clerk");
        System.out.println("3:Exit");

    }
    public static void clerkMenu(){
        System.out.println();
        System.out.println("1:Add Customer");
        System.out.println("2:Add Account Customer");
        System.out.println("3:show date transaction account id");
        System.out.println("4:Show customers");
        System.out.println("5:Exit");
        System.out.println(dotted);
    }
    public static void customerMenu()
    {
        System.out.println();
        System.out.println("1:Show accounts");
        System.out.println("2:Add transaction");
        System.out.println("3:Edit password");
        System.out.println("4:show date transaction account id");
        System.out.println("5:Exit");

    }


}
