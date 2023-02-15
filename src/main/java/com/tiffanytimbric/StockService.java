package com.tiffanytimbric;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    private final Map<String, Stock> stocks = Collections.synchronizedMap( new HashMap<>() );


    public Mono<Stock> add(@NonNull final Stock stock ) {
        validate( stock );

        stocks.put( stock.getName(), stock );

        return Mono.just( stock );
    }

    public Mono<Stock> update(@NonNull final Stock stock ) {
        if ( !stocks.containsKey( stock.getName() ) ) {
            return add( stock );
        }

        validate( stock );

        stocks.put( stock.getName(), stock );

        return Mono.just( stock );
    }

    public boolean contains(@NonNull final Stock stock ) {
        return contains( stock.getName() );
    }

    public boolean contains(@NonNull final String stockName ) {
        return stocks.containsKey( stockName );
    }

    public Mono<Stock> remove(@NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( !stocks.containsKey( name ) ) {
            return Mono.empty();
        }

        stocks.remove( name );

        return Mono.just( stock );
    }

    @org.springframework.lang.NonNull
    public Flux<Stock> removeAll() {
        final Stock[] removedStocks = stocks.values().toArray( new Stock[0] ).clone();

        stocks.clear();

        return Flux.just( removedStocks );
    }

    @org.springframework.lang.NonNull
    public Mono<Stock> remove(@NonNull final String name ) {
        if ( !stocks.containsKey( name ) ) {
            return Mono.empty();
        }

        final Stock stock = stocks.get( name );
        stocks.remove( name );

        return Mono.just( stock );
    }

    @NonNull
    public Mono<Stock> findByName( @NonNull final String name ) {
        if ( !stocks.containsKey( name ) ) {
            return Mono.empty();
        }

        return Mono.just( stocks.get( name ) );
    }

    @NonNull
    public Flux<Stock> findAll() {
        if ( stocks.isEmpty() ) {
            return Flux.empty();
        }

        return Flux.just( stocks.values().toArray( new Stock[0] ) );
    }

    private void validate( @NonNull final Stock stock ) {
        final String name = stock.getName();
        if ( StringUtils.isBlank( name ) ) {
            throw new IllegalArgumentException( "Parameter \"name\" must be non-null and non-empty." );
        }

        double price = stock.getPrice();
        if ( price <= 0.0D ) {
            throw new IllegalArgumentException( String.format(
                    "Parameter \"price\" must have a positive value.  Value: %g", price
            ) );
        }
    }
}
