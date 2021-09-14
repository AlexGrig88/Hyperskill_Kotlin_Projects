package machine

class CoffeeMachine(
    var water: Int = 0,
    var milk: Int = 0,
    var beans: Int = 0,
    var money: Int = 0,
    var disposableCups: Int = 0) {

    private val waterFor1Cup = 200
    private val milkFor1Cup = 50
    private val beansFor1Cup = 15
    var costEspresso = 4
    var costLatte = 7
    var costCappuccino = 6

    fun showState() {
        println()
        println("""
            The coffee machine has:
            $water of water
            $milk of milk
            $beans of coffee beans
            $disposableCups of disposable cups
            ${'$'}$money of money
        """.trimIndent())
    }

    fun sellCoffee(variety: VarietiesOfCoffee?) {
        if (variety == null) {
            println("No such variety!")
            return
        }

        if (water >= variety.waterST && milk >= variety.milkST && beans >= variety.beansST && disposableCups > 0) {
            println("I have enough resources, making you a coffee!")
        } else if (water <= variety.waterST) {
            println("Sorry, not enough water!")
            return
        } else if (milk < variety.milkST) {
            println("Sorry, not enough milk!")
            return
        } else if (beans < variety.beansST) {
            println("Sorry, not enough beans!")
            return
        } else {
            println("Sorry, not enough disposable cups!")
            return
        }
        water -= variety.waterST
        milk -= variety.milkST
        beans -= variety.beansST
        --disposableCups
        money += when (variety) {
            VarietiesOfCoffee.ESPRESSO -> costEspresso
            VarietiesOfCoffee.LATTE -> costLatte
            VarietiesOfCoffee.CAPPUCCINO -> costCappuccino
        }
    }

    fun giveMoney(): Int {
        val allMoney = money
        println("I gave you ${'$'}$money")
        money = 0
        return allMoney
    }

    fun addResources(w: Int, m: Int, b: Int, dC: Int) {
        water += w
        milk += m
        beans += b
        disposableCups += dC
    }

    fun printAvailabilityForCoffee(cups: Int) {
        var amount = if (water < waterFor1Cup || milk < milkFor1Cup || beans < beansFor1Cup) {
            0
        } else {
            val temp1 = water / waterFor1Cup
            val temp2 = milk / milkFor1Cup
            val temp3 = beans / beansFor1Cup
            minOf(temp1, temp2, temp3)
        }
        println(when {
            amount > cups  -> "Yes, I can make that amount of coffee (and even ${amount - cups} more than that)"
            amount == cups -> "Yes, I can make that amount of coffee"
            else -> "No, I can make only $amount cups of coffee"
        })
    }

    fun calculateIngredients(cups: Int) = Triple(
        cups * waterFor1Cup,
        cups * milkFor1Cup,
        cups * beansFor1Cup
    )

    fun showInstructionForMakingCoffee() {
        println("""
        Starting to make a coffee
        Grinding coffee beans
        Boiling water
        Mixing boiled water with crushed coffee beans
        Pouring coffee into the cup
        Pouring some milk into the cup
        Coffee is ready!
    """.trimIndent())
    }
}