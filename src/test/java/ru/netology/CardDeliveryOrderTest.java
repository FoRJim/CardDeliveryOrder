package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {


    @Test
    void shouldTestSubmitForm() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL,"a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue("15.04.2023");
        $("[data-test-id=name] input").setValue("Кузнецов Василий");
        $("[data-test-id=phone] input").setValue("+79194811222");
        $("[data-test-id=agreement]").click();
        $x("//*[contains(@class, 'button button_view_extra button_size_m button_theme_alfa-on-white')]").click();
        $x("//div[text()='Успешно!']").shouldBe(Condition.appear, Duration.ofSeconds(15));
    }
}