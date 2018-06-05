import org.junit.*;
import org.junit.Assert.assertEquals

class PeopleRegisterTest {

    val people = """Adam 18
Bob 15
Charlie 8
Dave 49
Elizabeth 12
Francis 42
George 85
Harriet 62
Janet 13
Karl 8
Laura 6
Mary 19
Nick 16
Oliver 54
Peter 27
Peter 34
Robert 36
Sally 26
Tom 58
Tom 13
Tom 58
Tom 45
Vicky 60
Wally 28"""


    val register = PeopleRegisterParser.parse(people)

    @Test fun should_report_twenty_four_people() {
        assertEquals(24, register.count());
    }

    @Test fun should_say_that_Dave_is_the_oldest() {
        assertEquals(Person("George", 85), register.oldestPerson()!!)
    }

    @Ignore
    @Test fun should_say_that_Tom_is_the_commonest_name() {
        assertEquals("Tom", register.commonestName())
    }

    @Ignore
    @Test fun should_say_that_the_youngest_Peter_is_27_years_old() {
        assertEquals(27, register.youngestCalled("Peter"))
    }

    @Ignore
    @Test fun should_report_eight_children() {
        assertEquals(8, register.countOfChildren())
    }

    @Ignore
    @Test fun should_report_an_adult_to_child_ratio_of_2() {
        assertEquals(2.0, register.adultToChildRatio(), 0.001)
    }

    @Ignore
    @Test fun should_calculate_the_average_age() {
        assertEquals(33.0, register.averageAge(), 0.001)
    }

    @Ignore
    @Test fun should_calculate_the_median_age() {
        assertEquals(27.5, register.medianAge(), 0.001)
    }

}



class PeopleRegister(val people: List<Person>) {

    fun count(): Int = people.size

    fun oldestPerson(): Person? = people.sortedBy { it.age }.last()

    fun commonestName(): String? = TODO()

    fun youngestCalled(sought: String): Int? = TODO()

    fun countOfChildren(): Int = TODO()

    fun adultToChildRatio(): Double {
        TODO()
    }
    fun averageAge(): Double = TODO()

    fun medianAge(): Double {
        TODO()
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
                              .map{ PersonParser.parse(it) }
            )
}