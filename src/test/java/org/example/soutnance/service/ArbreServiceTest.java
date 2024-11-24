package org.example.soutnance.service;



import org.example.soutnance.repository.ArbareRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ArbreServiceTest {

    @Mock
    private ArbareRepository arbareRepository;

    @InjectMocks
    private ArbreService arbreService;

    @Test
    public void testArbreService() {

    }
}
