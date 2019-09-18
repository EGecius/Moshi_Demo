package moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class PersonJsonAdapterTest {

    private var personJson = ("{"
            + "\"age\":21,"
            + "\"gender\":\"female\","
            + "\"name\":\"Kate\""
            + "}")

    private var person = Person("Kate", 21, Gender.female)

    private lateinit var moshi: Moshi
    private lateinit var adapterPerson: JsonAdapter<Person>

    @Before
    fun setUp() {
        moshi = Moshi.Builder().build()
        adapterPerson = moshi.adapter(Person::class.java)
    }

    @Test
    fun fromJson() {
        val person = adapterPerson.fromJson(personJson)

        assertThat(person!!.name).isEqualTo("Kate")
        assertThat(person.age).isEqualTo(21)
        assertThat(person.gender as Gender).isEqualTo(Gender.female)
    }

    @Test
    fun toJson() {
        val result = adapterPerson.toJson(person)

        assertThat(result).isEqualTo(personJson)
    }

}