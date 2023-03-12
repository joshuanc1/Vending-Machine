package com.techelevator;

import com.techelevator.utility.Console;
import com.techelevator.utility.Log;
import com.techelevator.utility.VendingMachine;
import java.text.DecimalFormat;


import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};


	private VendingMachine vendingMachine = new VendingMachine();

	private Log currentVendingLog = new Log();

	private DecimalFormat decimalFormat = new DecimalFormat();

	private Scanner userInput;

	public VendingMachineCLI() { userInput = new Scanner(System.in);}


	/**
	 * This is the main execution loop for the VendingMachineCLI Orchestrator Class.
	 */
	public void run() {


		boolean runMenu = true;
		String[] currentMenu = MAIN_MENU_OPTIONS;


		while (runMenu) {

			displayMenu(currentMenu);

			System.out.print("\nPlease make a selection: ");
			String selection = userInput.nextLine();


			try {
				int selectionIndex = Integer.parseInt(selection) - 1;

				String menuOption = currentMenu[selectionIndex];


				if (menuOption.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					System.out.println("");
					System.out.println("Key | Item | Price | Snack | Stock");
					System.out.println("");
					vendingMachine.printItemMenu(); //Prints Item Menu

				} else if (menuOption.equals(MAIN_MENU_OPTION_PURCHASE)) {
					// do purchase

					currentMenu = PURCHASE_MENU_OPTIONS;
					System.out.println(" ");
					System.out.println(" ");
					System.out.printf("******** Purchase Menu *********");
					System.out.println(" ");

				} else if (menuOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY)){

					//Customer Fed Money Exceptions
					try {
						System.out.println("");
						System.out.println("");
						System.out.println("How much money would you like to insert? ");
						String moneyInserted = userInput.nextLine();
						if (Double.valueOf(moneyInserted) >= 1000.00 && !moneyInserted.isEmpty()){
							throw new Exception();
						}
						if (Double.valueOf(moneyInserted) < 0){
							throw new NegativeArraySizeException();
						}

							vendingMachine.feedMoneyUpdateTotal(moneyInserted);

							currentVendingLog.logFeedMoney(vendingMachine.getFeedMoneyForLog(), vendingMachine.getCustomersTotalMoney()); //Logs Fed Money

					} catch (NumberFormatException nfe) {
						System.out.println("Sorry, That Is Not a Valid Option");
					} catch (NegativeArraySizeException nase){
						System.out.println("Please enter a positive amount of money.");
					} catch (Exception e){
						System.out.println("Sorry, cannot exceed $999.99. Please Try Again");
					}

				} else if (menuOption.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
					try{
						vendingMachine.printItemMenu();
						System.out.println("Please use on of the keys above to make your selection: ");
						String keySelected = userInput.nextLine().toUpperCase();
						System.out.println("");
						System.out.println("");
						vendingMachine.dispenseItem(keySelected);
						currentVendingLog.logItemSold(vendingMachine.getItemNameAndKeyForLog(),vendingMachine.getItemPriceForLog(),vendingMachine.getCustomersTotalMoney());

					}catch (Exception e){
						System.out.println("Invalid selection, please make a valid selection");
					}
				}else if (menuOption.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
					currentMenu = MAIN_MENU_OPTIONS; //Return to Main Menu execution loop
					vendingMachine.getCoinBox().returnChange(vendingMachine.getCustomersTotalMoney()); //Give customer back their change

					currentVendingLog.logGiveChange(vendingMachine.getFormattedTotalMoney());

					vendingMachine.resetCustomerMoney();	//Reset customer money to zero

				} else if (menuOption.equals(MAIN_MENU_OPTION_EXIT)) {
					runMenu = false; //Terminate While Loop
					System.out.println("Thank you for using Team 4's amazing vending machine");
				}

			} catch (Exception ex) {

				System.out.println(Console.ANSI_RED);

				System.out.println(Console.fillText("-", 24 + selection.length()));
				System.out.printf("'%s' Is Not a Valid Option%n", selection);
				System.out.println(Console.fillText("-", 24 + selection.length()));

				System.out.println(Console.ANSI_RESET);
			}

		}
	}



	private void displayMenu(String[] menu) {


		if (menu.equals(PURCHASE_MENU_OPTIONS)) {
			System.out.println(" ");
			System.out.println("Current Total Money: " + "$" + Double.valueOf(decimalFormat.format(vendingMachine.getCustomersTotalMoney())));
			System.out.println("\n********************************");
			for (int i = 0; i < menu.length; i++) {
				if (!menu[i].startsWith("*")) {
					System.out.printf("%1$s) %2$s\n", i + 1, menu[i]);
				}
			}
			System.out.println("********************************");
		} else {
			System.out.println("\n********************************");
			for (int i = 0; i < menu.length; i++) {
				if (!menu[i].startsWith("*")) {
					System.out.printf("%1$s) %2$s\n", i + 1, menu[i]);
				}
			}
			System.out.println("********************************");
		}
	}

	/**
	 * the public static void main is the core method of the program
	 * allowing it to be executable and calls all other methods. In VendingMachineCLI
	 * it is used to create an instance of the class so that the public void run()
	 * method can be called and CLI instance variables can be used in a natural
	 * OOP way.
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
