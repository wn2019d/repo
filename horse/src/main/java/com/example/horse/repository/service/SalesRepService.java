package com.example.horse.repository.service;

import com.example.horse.model.SalesRepresentative;
import com.example.horse.repository.SalesRepresentativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesRepService {
    private final SalesRepresentativeRepository salesRepRepository;


    public List<SalesRepresentative> getAllRepresentatives(){
        Iterable<SalesRepresentative> all = salesRepRepository.findAll();
        List<SalesRepresentative> res = new ArrayList<>();
        all.forEach(res :: add);
        return res;
    }
}

