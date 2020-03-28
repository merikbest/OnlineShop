package com.gmail.merikbest2015.ecommerce;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller //В HTTP-запросы обрабатываются контроллером
public class GreetingController {
    @Autowired
    private PerfumeRepository perfumeRepository;// Это значит получить бин с именем userRepository
    // Который автоматически генерируется Spring, мы будем использовать его для обработки данных

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
    public String main(Map<String, Object> model) { //Возвращает список парфюмов
        Iterable<Perfume> perfumes = perfumeRepository.findAll();
        model.put("perfumes", perfumes);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam(name = "perfumeTitle") String perfumeTitle,
                      @RequestParam(name = "perfumer")  String perfumer,
                      @RequestParam(name = "year")  Integer year,
                      @RequestParam(name = "country")  String country,
                      @RequestParam(name = "perfumeGender")  String perfumeGender,
                      @RequestParam(name = "fragranceTopNotes")  String fragranceTopNotes,
                      @RequestParam(name = "fragranceMiddleNotes")  String fragranceMiddleNotes,
                      @RequestParam(name = "fragranceBaseNotes")  String fragranceBaseNotes,
                      @RequestParam(name = "description")  String description,
                      Map<String, Object> model)
    {
        Perfume perfume = new Perfume(perfumeTitle, perfumer, year, country, perfumeGender, fragranceTopNotes,
                fragranceMiddleNotes, fragranceBaseNotes, description);
        perfumeRepository.save(perfume); //Сохранили в БД

//        Iterable<Perfume> perfumes = perfumeRepository.findAll();
//        model.put("perfumes", perfumes);
        return "main";
    }

    @GetMapping("/list") //Список товаров
    public String get(Map<String, Object> model) {
        Iterable<Perfume> perfumes = perfumeRepository.findAll(); //Взяли из репозитория
        model.put("perfumes", perfumes); //Положили в модель
        return "list"; //И отдали юсеру
    }

    @PostMapping("/filter") //Поиск
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Perfume> perfumes = perfumeRepository.findByPerfumer(filter);
        model.put("perfumes", perfumes);
        return "filter";
    }

    @GetMapping("/add")
    public String addToBD(@RequestParam(name = "perfumeTitle") String perfumeTitle,
                          @RequestParam(name = "perfumer")  String perfumer,
                          @RequestParam(name = "year")  Integer year,
                          @RequestParam(name = "country")  String country,
                          @RequestParam(name = "perfumeGender")  String perfumeGender,
                          @RequestParam(name = "fragranceTopNotes")  String fragranceTopNotes,
                          @RequestParam(name = "fragranceMiddleNotes")  String fragranceMiddleNotes,
                          @RequestParam(name = "fragranceBaseNotes")  String fragranceBaseNotes,
                          @RequestParam(name = "description")  String description,
            Map<String, Object> model)
    {
        return "addToBD";
    }
}