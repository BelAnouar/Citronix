package org.example.soutnance.service;


import org.example.soutnance.repository.RecolteRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RecolteServiceTest {
    @Mock
    private RecolteRepository repo;
    @InjectMocks
    private RecolteService service;
}
