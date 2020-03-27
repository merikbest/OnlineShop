package com.gmail.merikbest2015.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller //В HTTP-запросы обрабатываются контроллером
public class GreetingController {

    //В следующем примере GreetingController обрабатывает запросы GET для / приветствия,
    // возвращая имя представления (в данном случае приветствие)

    @GetMapping("/greeting")
    // @GetMapping аннотация гарантирует, что HTTP-запросы GET к /greeting отображаются в методе greeting().
    public String greeting(
            //@RequestParam связывает значение имени параметра строки запроса с параметром name метода greeting().
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model) { // Model model Модель - то куда мы будем складывать данный который вернем пользывателю

        // Значение параметра name добавляется в объект Model, что в конечном итоге делает его доступным для шаблона представления.
        // model.addAttribute("name", name); ЗАМЕНИЛИ
        model.put("name", name);
        return "greeting"; //возвращает файл-шаблон
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("shop", "online shop)");
        return "main";
    }
}