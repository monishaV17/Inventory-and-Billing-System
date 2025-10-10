**Overview**

The Inventory Billing System is a Java-based console application designed to manage inventory items and create customer bills efficiently. It provides functionalities to add, update, display, and remove inventory items, as well as generate and display bills for purchases. All inventory data is persisted in a CSV file for easy management and retrieval.

**Features**
1) Add new items to inventory
2) Update existing inventory items (quantity and price)
3) Remove items from inventory
4) Display all inventory items
5) Create customer bills and add purchased items
6) Display bill summaries, including total amount
7) Data persistence with CSV file (inventory.csv)

**Project Structure** 🗂️

src/app/InventoryBillingApp.java

src/model/Inventory.java

src/model/InventoryItem.java

src/model/Bill.java

inventory.csv

.gitignore

inventory.iml
   

**Installation**
1. Clone the repository
   git clone <repository-url>
   cd InventoryBillingSystem
2. Ensure you have Java installed (Java 8 or above).
  Build Instructions
  In your project root directory, compile with:
Command:
  javac src/app/InventoryBillingApp.java src/model/*.java

**Run Instructions**

Start the application from the project root with:
command:
  java -cp src app.InventoryBillingApp
  
**CSV Format**

The inventory data in inventory.csv uses:
ID,Name,Quantity,Price

Example:
command:
  101,Apple,50,25.0
  102,Banana,30,15.0

**.gitignore**

Your repository should have a .gitignore file containing:
.idea/
out/
to exclude IDE and build folders from Git.

**Notes**

All program input and output works via the console.
Building and running in IntelliJ IDEA generates output files in the out folder.
All essential source files are inside the src folder.

**License**
MIT License
   


