# Bugs
This file is the list of bugs that we are currently aware of.

## ~~Insufficient fund transactions still publish~~ Fixed!
The problem: There is no way that any SummerCash API can tell the difference between a valid published transaction and an invalid published transaction. Transactions that have insufficient fund errors still publish. This is not necessarily an issue. However, the mc-summercash Minecraft players should be alerted upon trying to complete a transaction where the sender has insufficient funds.

Possible solution: Append some sort of error message to the RPC GeneralResponse when a transaction with insufficient funds has occured.

# Ideas
Not bugs with the code, but future ideas

## A transaction history
This would be good so that users can do something like `/history` and see a list of the 5 most recent transactions that they were involved in.

## A registration / binding command
If someone who already has a SummerCash account would like to link it to their Minecraft account, create a `/register <address>` command that adds the user to the database.