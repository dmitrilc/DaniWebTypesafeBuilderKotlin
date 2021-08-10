fun main(){
    val customBurger = burger {
        toppings {
            meat { "Bacon" }
            meat { "Beef Patty" }
            veggie { "Mushroom" }
            veggie { "Lettuce" }
        }
        condiment { "Mayonnaise" }
        cheese { "Pepper Jack" }
    }

    println(customBurger)
}

class Burger(){

    private lateinit var toppings: Toppings
    private lateinit var condiment: Condiment
    private lateinit var cheese: Cheese

    private constructor(toppings: Toppings, condiment: Condiment, cheese: Cheese) : this(){
        this.toppings = toppings
        this.condiment = condiment
        this.cheese = cheese
    }

    fun build(): Burger = Burger(toppings, condiment, cheese)

    fun toppings(init: Toppings.()->Unit) {
        toppings = Toppings()
        toppings.init()
    }

    fun condiment(init:()->String) {
        condiment = Condiment(init())
    }

    fun cheese(init:()->String) {
        cheese = Cheese(init())
    }

    override fun toString(): String {
        return "Burger(toppings=$toppings, condiment=$condiment, cheese=$cheese)"
    }

}

class Toppings(){

    private val meats = mutableSetOf<Meat>()
    private val veggies = mutableSetOf<Veggie>()

    fun meat(init:()->String) {
        meats.add(Meat(init()))
    }

    fun veggie(init:()->String) {
        veggies.add(Veggie(init()))
    }

    override fun toString(): String {
        return "Toppings(meats=$meats, veggies=$veggies)"
    }

}

data class Meat(val name: String)
data class Veggie(val name: String)
data class Condiment(val name: String)
data class Cheese(val name: String)

fun burger(init: Burger.()->Unit): Burger {
    val builder = Burger()
    builder.init()
    return builder.build()
}