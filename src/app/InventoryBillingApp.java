package app;
import model.Inventory;
import model.InventoryItem;
import model.Bill;
import java.util.Scanner;

public class InventoryBillingApp{
    private static Inventory inventory=new Inventory();
    private static Bill currentBill=new Bill();
    private static Scanner sc=new Scanner(System.in);

    public static void main(String[] args){
        inventory.loadFromFile("inventory.csv");
        boolean exit=false;
        while(!exit){
            System.out.println("===== Inventory Billing System =====");
            System.out.println("1. Add item to inventory");
            System.out.println("2. Update item in inventory");
            System.out.println("3. Remove item from inventory");
            System.out.println("4. Display all inventory items");
            System.out.println("5. Create new bill");
            System.out.println("6. Display current bill");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    addItemToInventory();
                    break;
                case 2:
                    updateItemInInventory();
                    break;
                case 3:
                    removeItemFromInventory();
                    break;
                case 4:
                    inventory.displayItems();
                    break;
                case 5:
                    createNewBill();
                    break;
                case 6:
                    System.out.println(currentBill.generateBill());
                    break;
                case 7:
                    inventory.saveToFile("inventory.csv");
                    exit=true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
    }

    private static void addItemToInventory(){
        System.out.print("Enter item ID: ");
        String id=sc.nextLine();
        System.out.print("Enter name: ");
        String name=sc.nextLine();
        System.out.print("Enter quantity: ");
        int quantity=sc.nextInt();
        System.out.print("Enter price: ");
        double price=sc.nextDouble();
        sc.nextLine();

        InventoryItem item=new InventoryItem(id,name,quantity,price);
        inventory.addItem(item);
        System.out.println("Item added.");
    }

    private static void updateItemInInventory(){
        System.out.print("Enter item ID to update: ");
        String id=sc.nextLine();
        System.out.print("Enter new quantity: ");
        int quantity=sc.nextInt();
        System.out.print("Enter new price: ");
        double price=sc.nextDouble();
        sc.nextLine();

        if(inventory.updateItem(id,quantity,price)){
            System.out.println("Item updated.");
        } else{
            System.out.println("Item not found.");
        }
    }

    private static void removeItemFromInventory(){
        System.out.print("Enter item ID to remove: ");
        String id=sc.nextLine();

        if(inventory.removeItem(id)){
            System.out.println("Item removed.");
        } else{
            System.out.println("Item not found.");
        }
    }

    private static void createNewBill(){
        currentBill=new Bill();
        System.out.println("Creating new bill. Enter items (type 'done' when finished):");

        while(true) {
            System.out.print("Enter item ID: ");
            String id=sc.nextLine();
            if (id.equalsIgnoreCase("done")) {
                break;
            }
            InventoryItem item=inventory.getItem(id);
            if (item == null) {
                System.out.println("Item not found in inventory.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int qty=sc.nextInt();
            sc.nextLine();

            if (qty>item.getQuantity()) {
                System.out.println("Insufficient stock.");
                continue;
            }

            currentBill.addItem(item,qty);
            item.setQuantity(item.getQuantity() - qty);
            System.out.println("Item added to bill.");
        }
        System.out.println("Bill created.");
    }
}
