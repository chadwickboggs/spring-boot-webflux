package com.tiffanytimbric;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @NonNull
    @RequestMapping(
            value = "stock",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> addStock(
            @NonNull @RequestBody final Stock stock
    ) {
        return stockService.addStock( stock );
    }

    @NonNull
    @RequestMapping(
            value = "stock",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> updateStock(
            @NonNull @RequestBody final Stock stock
    ) {
        return stockService.updateStock( stock );
    }

/*
    @NonNull
    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public Flux<String> containsByName(
            @NonNull @PathVariable( "name" ) final String name
    ) {
        return Flux.just( String.valueOf( stockService.containsStock( name ) ) );
    }
*/

    @NonNull
    @RequestMapping(
            value = "stock",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> removeAllStocks() {
        return stockService.removeAllStocks();
    }

    @NonNull
    @RequestMapping(
            value = "stock/{name}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> removeStock(
            @NonNull @PathVariable final String name
    ) {
        return stockService.removeStock( name );
    }

    @NonNull
    @RequestMapping(
            value = "stock",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> findAll() {
        return stockService.findAll();
    }

    @NonNull
    @RequestMapping(
            value = "stock/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Stock> findStock(
            @PathVariable @Nullable final String name
    ) {
        if ( StringUtils.isBlank( name ) ) {
            return stockService.findAll();
        }

        return stockService.findByName( name );
    }

}
