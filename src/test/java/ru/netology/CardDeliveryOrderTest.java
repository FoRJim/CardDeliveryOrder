package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldTestSubmitForm() {
        String planningDate = generateDate(4);

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Кузнецов Василий");
        $("[data-test-id=phone] input").setValue("+79194811222");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $x("//div[text()='Успешно!']").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}