package com.example.myins.seeder;

import com.example.myins.model.entity.Brand;
import com.example.myins.model.enums.BrandName;
import com.example.myins.repository.BrandRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BrandSeeder implements CommandLineRunner {

    private final BrandRepo brandRepo;


    public BrandSeeder(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    @Override
    public void run(String... args) {

        if (brandRepo.count() == 0) {
            Arrays.stream(BrandName.values())
                    .forEach(b -> {
                        Brand brand = new Brand();
                        brand.setBrandName(b);
                        this.brandRepo.save(brand);
                    });

        }

    }
}
