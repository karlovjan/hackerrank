package thirtycodingdays;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * Consider a database table, Emails, which has the attributes First Name and Email ID.
 * Given N rows of data simulating the Emails table, print an alphabetically-ordered list of people
 * whose email address ends in @gmail.com.
 */
public class RegExSortingTest {

    static List<String> alphabeticallyOrderedPeoples2(List<Email> emails) {
        final Predicate<String> emailPredicator = Pattern.compile("^[a-z]{1,20}@gmail\\.com$").asMatchPredicate(); //since java 11
        return emails.stream().filter(e -> emailPredicator.test(e.getEmailId())).map(Email::getFirstName).sorted(Comparator.naturalOrder()).collect(Collectors.toUnmodifiableList());
    }

    static List<String> alphabeticallyOrderedPeoples(List<Email> emails) {
        final Predicate<String> emailPredicator = Pattern.compile("^[a-z]{1,20}@gmail\\.com$").asPredicate(); //since java 1.8
        return emails.stream().filter(e -> emailPredicator.test(e.getEmailId())).map(Email::getFirstName).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    static List<String> alphabeticallyOrderedPeoples3(List<Email> emails) {
        return emails.stream().filter(e -> e.getEmailId().matches("^[a-z]{1,20}@gmail\\.com$")).map(Email::getFirstName).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    static List<String> alphabeticallyOrderedPeoples4(List<Email> emails) {
        return emails.stream().filter(e -> e.getEmailId().endsWith("@gmail.com")).map(Email::getFirstName).sorted().collect(Collectors.toList());
    }

    @Test
    void alphabeticallyOrderedPeoplesTest() {
        final List<Email> emails = new ArrayList<>();
        emails.add(new Email("riya", "riya@gmail.com"));
        emails.add(new Email("julia", "julia@julia.me"));
        emails.add(new Email("julia", "sjulia@gmail.com"));
        emails.add(new Email("julia", "julia@gmail.com"));
        emails.add(new Email("samantha", "samantha@gmail.com"));
        emails.add(new Email("tanya", "tanya@gmail.com"));


        assertIterableEquals(List.of("julia", "julia", "riya", "samantha", "tanya"), alphabeticallyOrderedPeoples(emails));
        assertIterableEquals(List.of("julia", "julia", "riya", "samantha", "tanya"), alphabeticallyOrderedPeoples2(emails));
        assertIterableEquals(List.of("julia", "julia", "riya", "samantha", "tanya"), alphabeticallyOrderedPeoples3(emails));
        assertIterableEquals(List.of("julia", "julia", "riya", "samantha", "tanya"), alphabeticallyOrderedPeoples4(emails));
    }

    @Test
    void alphabeticallyOrderedPeoplesTest2() {
        final List<Email> emails = new ArrayList<>();
        emails.add(new Email("riya", "riya@gmail.com"));
        emails.add(new Email("julia", "julia@julia.me"));
        emails.add(new Email("julia", "sjulia@gmail....com"));
        emails.add(new Email("julia", "julia@gmail.com"));
//        emails.add(new Email("samantha", "sam antha@gmail.com")); neni v zadani ze muze byt v emailu mezera, alphabeticallyOrderedPeoples4 ho odvaliduje jako spravny email
        emails.add(new Email("tanya", "tanya@gmail.comsd"));
        emails.add(new Email("tanya2", "tanya2gmail.com"));


        assertIterableEquals(List.of("julia", "riya"), alphabeticallyOrderedPeoples(emails));
        assertIterableEquals(List.of("julia", "riya"), alphabeticallyOrderedPeoples2(emails));
        assertIterableEquals(List.of("julia", "riya"), alphabeticallyOrderedPeoples3(emails));
        assertIterableEquals(List.of("julia", "riya"), alphabeticallyOrderedPeoples4(emails));
    }

    @Test
    void alphabeticallyOrderedPeoplesTest4() {
        final List<Email> emails = new ArrayList<>();
        emails.add(new Email("riya", "riya@gmail.com"));
        emails.add(new Email("julia", "julia@julia.me"));
        emails.add(new Email("julia", "sjulia@gmail....com"));
        emails.add(new Email("julia", "julia@gmail.com"));
        emails.add(new Email("samantha", "samntha@gmail.com test"));
        emails.add(new Email("tanya", "tanya@gmail.comsd"));
        emails.add(new Email("tanya2", "tanya2gmail.com"));


        assertIterableEquals(List.of("julia", "riya"), alphabeticallyOrderedPeoples4(emails));
    }

    static class Email {
        private final String firstName;
        private final String emailId;

        public Email(String firstName, String emailId) {
            this.firstName = firstName;
            this.emailId = emailId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getEmailId() {
            return emailId;
        }
    }
}
