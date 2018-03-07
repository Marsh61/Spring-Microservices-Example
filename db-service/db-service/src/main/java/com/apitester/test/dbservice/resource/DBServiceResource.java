package com.apitester.test.dbservice.resource;

import com.apitester.test.dbservice.model.Quote;
import com.apitester.test.dbservice.model.Quotes;
import com.apitester.test.dbservice.repository.QuotesRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/db")
public class DBServiceResource {

    private QuotesRepository quotesRepository;

    public DBServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }
    @GetMapping("/getQuotes/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {
        return getQuotesByUserName(username);
    }

    public List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    /*
    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes, Principal principal){
                quotes.getQuotes()
                        .stream()
                        .map(quote -> new Quote(principal.getName(), quote))
                        .forEach(quote -> quotesRepository.save(quote));
                return null;
    }*/


    @PostMapping(value = "/add", consumes = MediaType.ALL_VALUE) //@RequestBody final Quotes quotes
    public List<String> add(@RequestBody String string, Principal principal){
        System.out.println(string);
        JsonObject obj = new JsonParser().parse(string).getAsJsonObject();
        JsonArray quotesArray = obj.getAsJsonObject().getAsJsonArray("quotes");
        for (int i = 0 ; i < quotesArray.size(); i++) {
            Quote tmpQuote = new Quote(principal.getName(), quotesArray.get(i).getAsString());
            quotesRepository.save(tmpQuote);
        }
        System.out.println(principal.getName());
        return null;
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {
        List<Quote> quotes= quotesRepository.findByUserName(username);

        quotesRepository.delete(quotes);

        return getQuotesByUserName(username);
    }
}
