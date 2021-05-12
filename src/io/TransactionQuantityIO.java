package io;

import trader.Trader;

public class TransactionQuantityIO extends IO {

	public TransactionQuantityIO(Trader player) {
		super(player);
		this.addCommand("Cancel transaction");
	}

public void processPlayerInput(int playerChoice) {
	if (playerChoice > 0) {
		String quantityArgument = String.valueOf(playerChoice);
		addCommandArgument(quantityArgument);
	} else {
		String keyWord = "cancel";
		addCommandArgument(keyWord);
	}
	System.out.println("Transaction keyword " + getCommandArguments().get(0));
	System.out.println("Transaction argument1 " + getCommandArguments().get(1));
	System.out.println("Transaction argument2 " + getCommandArguments().get(2));
}

}
