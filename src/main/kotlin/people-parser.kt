class PeopleRegister(val people: List<Person>) {

    fun count(): Int = people.size

    fun oldestPerson(): Person? = people.sortedBy { it.age }.last()

    fun commonestName(): String? = people.groupBy { it.name }.map { it.key to it.value.size }.sortedBy { it.second }.last().first

    fun youngestCalled(sought: String): Int? = people.filter { it.name == sought }.sortedBy { it.age }.first().age

    fun countOfChildren(): Int = people.filter { it.age < 18 }.size

    fun adultToChildRatio(): Double = (count() - countOfChildren()) / countOfChildren().toDouble()

    fun averageAge(): Double = people.map { it.age }.reduce { acc, i -> acc + i } / count().toDouble()

    fun medianAge(): Double {
        val toTrim = (count() / 2) - 1
        return people.sortedBy { it.age }.map { it.age }.drop(toTrim).dropLast(toTrim).reduce { acc, i -> acc + i } / 2.toDouble()
    }
}

data class Person(val name: String, val age: Int)

object PersonParser {
    fun parse(data: String): Person {
        val params = data.trim().split(" ")
        return Person(params[0], params[1].toInt())
    }
}

object PeopleRegisterParser {
    fun parse(personData: String): PeopleRegister =
            PeopleRegister(
                    personData.trim()
                            .split("\n")
                            .map { PersonParser.parse(it) }
            )
}