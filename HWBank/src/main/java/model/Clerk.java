package model;

public class Clerk extends User{
  private int id;
  private Boss boss;

    public Clerk(int id, int role, String firstName, String lastName, String nationalCode, String phoneNumber, String address, String userName, String passWord, int clerkId) {
        super(id, role, firstName, lastName, nationalCode, phoneNumber, address, userName, passWord);
        this.id = clerkId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "id= " + getId() +
                        ", firstName= '" + getFirstName() + '\'' +
                        ", lastName= '" + getLastName() + '\'' +
                        ", nationalCode= '" + getNationalCode() + '\'' +
                        '}';
    }

}
