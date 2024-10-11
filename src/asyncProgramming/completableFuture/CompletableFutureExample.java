package asyncProgramming.completableFuture;

import asyncProgramming.PokemonAbilityFetch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    public void run() throws ExecutionException, InterruptedException {

        // Código bloqueante, por causa do get()
        CompletableFuture.runAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("pikachu");
            pokemonAbilityFetch.fetch();
        }).get();

        /* Código não bloqueante
        * CompletableFuture.runAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("pikachu");
            pokemonAbilityFetch.fetch();
          });
        * */

        // ----------------------------------------------------------

        // Callback quando o retorno foi bem sucedido
        CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("pikachu");
            String response = pokemonAbilityFetch.fetch();
            return response;
        }).thenAccept((result) -> {
            System.out.println("Task result: " + result);
        }).get();

        // Callback quando eu preciso realizar algo após a execução do supplyAsync independente do resultado
        CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("pikachu");
            String response = pokemonAbilityFetch.fetch();
            return response;
        }).thenRun(() -> {
            System.out.println("Busca no banco completada.");
        }).get();

        // Combina duas CompletableFuture
        CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("pikachu");
            String response = pokemonAbilityFetch.fetch();
            return response;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("charmander");
            String response = pokemonAbilityFetch.fetch();
            return response;
        }), (a, b) -> {
            System.out.println("Pikachu response: " + a);
            System.out.println("Charmander response: " + b);
            return a + b;
        }).get();

        // Combina duas ou mais CompletableFuture
        CompletableFuture pikachu = CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("pikachu");
            String response = pokemonAbilityFetch.fetch();
            return response;
        });

        CompletableFuture charmander = CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("charmander");
            String response = pokemonAbilityFetch.fetch();
            return response;
        });

        CompletableFuture bulbasaur = CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("bulbasaur");
            String response = pokemonAbilityFetch.fetch();
            return response;
        });

        CompletableFuture ditto = CompletableFuture.supplyAsync(() -> {
            PokemonAbilityFetch pokemonAbilityFetch = new PokemonAbilityFetch("ditto");
            String response = pokemonAbilityFetch.fetch();
            return response;
        });

        CompletableFuture allPokemons = CompletableFuture.allOf(pikachu, charmander, bulbasaur, ditto);
        allPokemons.thenRun(() -> {
            try{
                System.out.println("Pikachu: " + pikachu.get());
                System.out.println("Charmander: " + charmander.get());
                System.out.println("Bulbasaur: " + bulbasaur.get());
                System.out.println("Ditto: " + ditto.get());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).get();
    }
}
