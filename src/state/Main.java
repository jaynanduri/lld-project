package state;

import state.card.CardDetails;
import state.card.CardManager;
import state.card.CardManagerFactory;
import state.card.CardType;
import state.db.DBAccessor;

public class Main {
  public static void main(String[] args) {
    ATM atmMachine = new ATM("1234");
    int txnId = atmMachine.init();
    System.out.println(atmMachine.getState().toString());
    DBAccessor.cancelTransaction(txnId);
    System.out.println(atmMachine.getState().toString());
    CardManager creditCard = CardManagerFactory.getCardManager(CardType.CREDIT);
    CardDetails card = new CardDetails(CardType.CREDIT, 123, 123, "same");
    atmMachine.readCard(card);


  }
}
