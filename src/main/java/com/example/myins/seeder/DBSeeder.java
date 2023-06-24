package com.example.myins.seeder;

import com.example.myins.model.entity.Brand;
import com.example.myins.repository.BrandRepo;
import com.example.myins.repository.ModelRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DBSeeder implements CommandLineRunner {

    private final BrandRepo brandRepo;

    private final ModelRepo modelRepo;


    public DBSeeder(BrandRepo brandRepo, ModelRepo modelRepo) {
        this.brandRepo = brandRepo;
        this.modelRepo = modelRepo;
    }

    @Override
    public void run(String... args) {

        if (brandRepo.count() == 0) {
            Set<String> brands = Set.of("BMW", "AUDI", "CITROEN", "FIAT",
                            "FORD", "HONDA", "HYUNDAI", "KIA",
                            "MAZDA", "MERCEDES_BENZ", "MITSUBISHI",
                            "NISSAN", "OPEL", "PEUGEOT",
                            "RENAULT", "SEAT", "SKODA", "TOYOTA", "VW");
                   brands.forEach(b -> {
                        Brand brand = new Brand();
                        brand.setBrandName(b);
                        this.brandRepo.save(brand);
                    });

        }

    }
}
