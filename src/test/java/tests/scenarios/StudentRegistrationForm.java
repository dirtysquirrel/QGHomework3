package tests.scenarios;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import com.github.javafaker.Faker;

import java.io.File;


public class StudentRegistrationForm {
    Faker faker = new Faker();


    String name = faker.name().firstName(),
            lastName = faker.name().lastName(),
            mail = faker.internet().emailAddress(),
            gender = "Female",
            phoneNumber = faker.number().digits(10),
            month = "April",
            year = "1904",
            day = "26",
    //            dayOfWeekOfBirth = "Tuesday",
    subject = "",
            hobby = "Music",
            picture = ".gitignore",
            address = faker.address().fullAddress(),
            state = "Haryana",
            city = "Karnal";

    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    }

    public void fillForm() {
        $("#firstName").setValue(name);

        $("#lastName").setValue(lastName);

        $("#userEmail").setValue(mail);

        $("label[for='gender-radio-2']").click();

        $("#userNumber").setValue(phoneNumber);

        // set date
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day).click();

        $("#subjectsInput").setValue("Ma");
        $("#react-select-2-option-0").click();
        subject = $(".subjects-auto-complete__multi-value__label").getText();


        $("#hobbiesWrapper").$(byText(hobby)).click();

        File file = new File(picture);
        $("#uploadPicture").uploadFile(file);

        $("#currentAddress").setValue(address);

        $("#state").scrollTo().click();
        $(byText(state)).click();

        $(byText("Select City")).scrollTo().click();
        $(byText(city)).click();

        $("#submit").click();
    }

    public void checkData() {
        $(".modal-body").shouldHave(
                text(name),
                text(lastName),
                text(mail),
                text(phoneNumber),
                text(gender),
                text(day + " " + month + "," + year),
                text(subject),
                text(hobby),
                text(picture),
                text(address),
                text(state + " " + city)
        );
    }
}

