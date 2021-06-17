package com.zekerijah.doggraphqlapi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.zekerijah.doggraphqlapi.exception.BreedNotFoundException;
import com.zekerijah.doggraphqlapi.exception.DogNotFoundException;
import com.zekerijah.doggraphqlapi.model.Dog;
import com.zekerijah.doggraphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;

        Iterable<Dog> dogs = dogRepository.findAll();
        for(Dog dog:dogs) {
            if (dog.getBreed().equals(breed)){
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        if (!deleted) {
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return deleted;
    }

    public Dog updateDogName (String name, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(name);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
