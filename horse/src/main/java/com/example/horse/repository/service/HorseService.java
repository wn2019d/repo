package com.example.horse.repository.service;

import com.example.horse.model.Horse;
import com.example.horse.repository.HorsesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorseService {
    private final HorsesRepository horsesRepository;

    public List<Horse> getHorsesBySalesrepId(long repId){
        return horsesRepository.findHorsesBySalesrepId(repId);
    }

    public List<Horse> getHorsesByHeightAndAge(long repId, int height, int age){
        return horsesRepository.findHorsesByHeightAgeSalesrepId(repId, height, age);
    }

    public Optional<Horse> getHorseById(long horseId){return horsesRepository.findById(horseId);}

    public Horse saveHorse(Horse horse){return horsesRepository.save(horse);}

    public List<Horse> getAllHorses(){
        Iterable<Horse> all = horsesRepository.findAll();
        List<Horse> res = new ArrayList<>();
        all.forEach(res :: add);
        return res;
    }
}
