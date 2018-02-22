package com.apitester.test.stock.stockservice.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    RestTemplate restTemplate;

    public StockResource() {
        restTemplate = new RestTemplate();
    }

    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") final String userName) {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + userName, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
                });
        List<String> quotes = quoteResponse.getBody();

        return quotes
                .stream()
                .map(quote -> {
                        Stock stock = getStockPrice(quote);
                        return new Quote(quote, stock.getPrice());
                })
                .collect(Collectors.toList());
    }

    public Stock getStockPrice(String quote) {
        try {
            return new Stock(quote, new BigDecimal(3.9));
        } catch (Exception e) {
            return new Stock(quote, new BigDecimal(4.5));
        }
    }

    private class Stock {
        private String quote;
        private BigDecimal price;

        public Stock(String quote, BigDecimal price) {
            this.quote = quote;
            this.price = price;
        }

        public String getQuote() {return quote;}
        public BigDecimal getPrice() {return price;}
    }

    private class Quote {
        private String quote;
        private BigDecimal price;

        public Quote(String quote, BigDecimal price) {
            this.quote = quote;
            this.price = price;
        }

        public String getQuote() {
            return quote;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }

}
