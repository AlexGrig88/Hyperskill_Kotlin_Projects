package machine

fun main() {

    val coffeeMachine = CoffeeMachine(400, 540, 120, 550, 9)

    //coffeeMachine.showState()
    do {
        print("\nWrite action (buy, fill, take, remaining, exit): ")
        var isExit = false
        when (readLine()!!) {
            "buy" -> buyCoffee(coffeeMachine)
            "fill" -> fillCoffeeMachine(coffeeMachine)
            "take" -> coffeeMachine.giveMoney()
            "remaining" -> coffeeMachine.showState()
            "exit" -> isExit = true
            else -> println("No such operation!")
        }
    } while (!isExit)

}

fun buyCoffee(coffeeMachine: CoffeeMachine) {
    print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu : ")
    val answer = readLine()!!
    if (answer == "back") return
    val variety = when (answer) {
        "1" -> VarietiesOfCoffee.ESPRESSO
        "2" -> VarietiesOfCoffee.LATTE
        "3" -> VarietiesOfCoffee.CAPPUCCINO
        else -> null
    }
    coffeeMachine.sellCoffee(variety)
}

fun fillCoffeeMachine(machine: CoffeeMachine) {
    print("Write how many ml of water do you want to add: ")
    val water = readLine()!!.toInt()
    print("Write how many ml of milk do you want to add: ")
    val milk = readLine()!!.toInt()
    print("Write how many grams of coffee beans do you want to add: ")
    val beans = readLine()!!.toInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    val disCups = readLine()!!.toInt()
    machine.addResources(water, milk, beans, disCups)
}

