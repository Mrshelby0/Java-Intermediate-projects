import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    // Expense Class
    static class Expense {
        private int id;
        private String date;
        private double amount;
        private String category;
        private String description;

        public Expense(int id, String date, double amount, String category, String description) {
            this.id = id;
            this.date = date;
            this.amount = amount;
            this.category = category;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Date: " + date + ", Amount: $" + amount + 
                   ", Category: " + category + ", Description: " + description;
        }
    }

    // ExpenseManager Class
    static class ExpenseManager {
        private ArrayList<Expense> expenses;

        public ExpenseManager() {
            expenses = new ArrayList<>();
        }

        public void addExpense(Expense expense) {
            expenses.add(expense);
            System.out.println("Expense added: " + expense.getDescription());
        }

        public void removeExpense(int id) {
            expenses.removeIf(expense -> expense.getId() == id);
            System.out.println("Expense removed.");
        }

        public void updateExpense(int id, Scanner scanner) {
            for (Expense expense : expenses) {
                if (expense.getId() == id) {
                    System.out.println("Updating details for expense ID: " + id);

                    System.out.print("Enter new date (or press Enter to skip): ");
                    String newDate = scanner.nextLine();
                    if (!newDate.isEmpty()) expense.setDate(newDate);

                    System.out.print("Enter new amount (or press Enter to skip): ");
                    String newAmount = scanner.nextLine();
                    if (!newAmount.isEmpty()) expense.setAmount(Double.parseDouble(newAmount));

                    System.out.print("Enter new category (or press Enter to skip): ");
                    String newCategory = scanner.nextLine();
                    if (!newCategory.isEmpty()) expense.setCategory(newCategory);

                    System.out.print("Enter new description (or press Enter to skip): ");
                    String newDescription = scanner.nextLine();
                    if (!newDescription.isEmpty()) expense.setDescription(newDescription);

                    System.out.println("Expense updated.");
                    return;
                }
            }
            System.out.println("Expense not found.");
        }

        public void viewExpenses() {
            System.out.println("All Expenses:");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }

        public void searchExpense(String query) {
            System.out.println("Search results for: " + query);
            for (Expense expense : expenses) {
                if (expense.getCategory().toLowerCase().contains(query.toLowerCase()) || 
                    expense.getDate().contains(query)) {
                    System.out.println(expense);
                }
            }
        }

        public void calculateTotal() {
            double total = 0;
            for (Expense expense : expenses) {
                total += expense.getAmount();
            }
            System.out.println("Total Expenses: $" + total);
        }
    }

    // Main Method
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nExpense Tracker:");
            System.out.println("1. Add Expense");
            System.out.println("2. Remove Expense");
            System.out.println("3. Update Expense");
            System.out.println("4. View Expenses");
            System.out.println("5. Search Expense");
            System.out.println("6. Calculate Total Expenses");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Expense ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Date (yyyy-mm-dd): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    manager.addExpense(new Expense(id, date, amount, category, description));
                }
                case 2 -> {
                    System.out.print("Enter Expense ID to remove: ");
                    int id = scanner.nextInt();
                    manager.removeExpense(id);
                }
                case 3 -> {
                    System.out.print("Enter Expense ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    manager.updateExpense(id, scanner);
                }
                case 4 -> manager.viewExpenses();
                case 5 -> {
                    System.out.print("Enter date or category to search: ");
                    String query = scanner.nextLine();
                    manager.searchExpense(query);
                }
                case 6 -> manager.calculateTotal();
                case 7 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
