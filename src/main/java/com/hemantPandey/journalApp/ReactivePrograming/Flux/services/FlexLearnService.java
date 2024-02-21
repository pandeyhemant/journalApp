package com.hemantPandey.journalApp.ReactivePrograming.Flux.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


@Service
public class FlexLearnService {

//    public void fluxTestingService(){
//        System.out.printf("Flux Testing services");
//    };

//    Ceating flux
    public Flux<String> getFlux (){
        return Flux.just("hemant", "pandey", "ram", "shyam","nilesh");
    }


    public Flux<String> fruitsFlex (){
        List<String> fruitsNames = Arrays.asList("Mongo", "banana");
        return Flux.fromIterable(fruitsNames);
    };


    public Flux<Void> getBlackFlux(){
      return Flux.empty();
    };

//    Map
    public Flux<String> mapExample(){
        Flux<String> map = getFlux().map(String::toUpperCase).log();
        return map;
    }

//    Filter
    public Flux<String> filterExampleFlux(){
      return getFlux().filter(data->{
          return data.length() > 4;
      }).log();
    };

//    FlatMap
    public Flux<String> flatMapExample(){
        return getFlux().flatMap(data->Flux.just(data.split("")));
    }

//Transform

    public Flux<String> transformExample(){
        Function<Flux<String>,Flux<String>> Function = (data) -> data.map(String::toUpperCase);
        return getFlux().transform(Function);
    };

    //    concat / concatWith (instance) use for object -> working in synchronous (1st comlplet than 2nd)
    public Flux<String> concatExample(){
        return Flux.concat(getFlux(), fruitsFlex());
    }

    //    concat / concatWith (instance) use for object -> working in asynchronous (1st  and 2nd both working together)
    public Flux<String> mergeExample(){
        return Flux.merge(getFlux(), fruitsFlex());
    }

//    Zip and ZipWith Example
    public Flux<String> zipExample(){
        return Flux.zip(getFlux(), Flux.just(32,4324,4324,24324,432), (a,b)-> a +" + "+ b);
    }

//    Side effect example -> call after next , subscribe

    public Flux<String> sideEffectExample(){
        return getFlux().doOnNext(data -> System.out.println("On Next "+data)).doOnSubscribe(value-> System.out.println("On Subscribe "+value));
    }

    public static void main(String[] args) {
        FlexLearnService ff = new FlexLearnService();
//
////        Transfor example for function
////        ff.transformExample().subscribe(data->{
////            System.out.println("@@@@@@@@@ " + data);
////        });
//
////        Concate example
//        ff.concatExample().log().subscribe(data->{
//            System.out.println("@@@@@@@@@ " + data);
//        });

        ////        Merge example
//        ff.mergeExample().log().subscribe(System.out::println);

        ////        zip example
//        ff.zipExample().log().subscribe(System.out::println);

        ////        DO on next
        ff.sideEffectExample().log().subscribe(System.out::println);
    }



}


