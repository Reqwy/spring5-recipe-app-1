package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    @Mock
    private RecipeService recipeService;
    @Mock
    private RecipeRepository recipeRepository;

    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
        recipe = new Recipe();
        recipe.setId(111L);
        recipe.setDescription("AAAAAAAA");
    }

    @Test
    public void getRecipes() {

        Recipe recipe = new Recipe();
        Set<Recipe> data = new HashSet<>();
        data.add(recipe);
        when(recipeRepository.findAll()).thenReturn(data);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void findById() {
        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));
        Recipe recipe = recipeService.findById(111L);
        assertEquals(111L, (long)recipe.getId());

        verify(recipeRepository, times(1)).findById(any());

    }
}