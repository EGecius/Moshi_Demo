package moshi;

import static org.assertj.core.api.Assertions.assertThat;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PersonJsonAdapterTest {

    String personJson = "{"
            + "\"age\":21,"
            + "\"gender\":\"female\","
            + "\"name\":\"Kate\""
            + "}";

    Person person = new Person("Kate", 21, Gender.female);

    private Moshi moshi;
    private JsonAdapter<Person> adapterPerson;

    @Before
    public void setUp() {
        moshi = new Moshi.Builder().build();
        adapterPerson = moshi.adapter(Person.class);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void fromJson() throws IOException {
        Person person = adapterPerson.fromJson(personJson);

        assertThat(person.getName()).isEqualTo("Kate");
        assertThat(person.getAge()).isEqualTo(21);
        assertThat(person.getGender()).isEqualTo(Gender.female);
    }

    @Test
    public void toJson() {
        String result = adapterPerson.toJson(person);

        assertThat(result).isEqualTo(personJson);
    }

}