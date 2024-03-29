package com.example.myins.seeder;

import com.example.myins.model.entity.Brand;
import com.example.myins.model.entity.Company;
import com.example.myins.repository.BrandRepo;
import com.example.myins.repository.CompanyRepo;
import com.example.myins.repository.ModelRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DBSeeder implements CommandLineRunner {

    private final BrandRepo brandRepo;

    private final ModelRepo modelRepo;
    private final CompanyRepo companyRepo;


    public DBSeeder(BrandRepo brandRepo, ModelRepo modelRepo, CompanyRepo companyRepo) {
        this.brandRepo = brandRepo;
        this.modelRepo = modelRepo;
        this.companyRepo = companyRepo;
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

        if (companyRepo.count() == 0) {
            Set<String> companies = Set.of("ALLIANZ", "UNIQA", "DZI", "ARMEEC");
            companies.forEach(b -> {
                Company company = new Company();
                company.setName(b);
                this.companyRepo.save(company);
            });

        }

    }
}
