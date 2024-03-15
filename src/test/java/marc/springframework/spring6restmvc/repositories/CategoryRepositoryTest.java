package marc.springframework.spring6restmvc.repositories;

import marc.springframework.spring6restmvc.entities.Beer;
import marc.springframework.spring6restmvc.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CategoryRepositoryTest
{
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BeerRepository beerRepository;
    Beer testBeer;

    @BeforeEach
    void setUp()
    {
        testBeer = beerRepository.findAll().getFirst();
    }

    @Transactional
    @Test
    void testAddCategory()
    {
        Category savedCat = categoryRepository.save(Category.builder()
                .description("Ales")
                .build());

        testBeer.addCategory(savedCat);
        Beer savedBeer = beerRepository.save(testBeer);

        System.out.println(savedBeer.getBeerName());
    }
}