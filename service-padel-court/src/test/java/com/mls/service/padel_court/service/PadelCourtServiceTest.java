package com.mls.service.padel_court.service;

import com.mls.service.padel_court.DataProvider;
import com.mls.service.padel_court.dto.request.PadelCourtSaveRequest;
import com.mls.service.padel_court.model.PadelCourtEntity;
import com.mls.service.padel_court.repository.PadelCourtRepository;
import com.mls.service.padel_court.service.impl.PadelCourtServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PadelCourtServiceTest {

    @Mock
    private PadelCourtRepository padelCourtRepository;

    @InjectMocks
    private PadelCourtServiceImpl padelCourtService;

    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        // Injecting mapper manually in the service
        ReflectionTestUtils.setField(padelCourtService, "mapper", mapper);
    }

    @Test
    public void testSavePadelCourt_ShouldSavePadelCourt() {
        PadelCourtSaveRequest request = DataProvider.newPadelCourtEntity();
        PadelCourtEntity padelCourt = mapper.map(request, PadelCourtEntity.class);
        given(padelCourtRepository.save(any(PadelCourtEntity.class))).willReturn(padelCourt);

        PadelCourtEntity result = padelCourtService.savePadelCourt(request);

        assertNotNull(result);
        assertEquals("Padel Place",result.getName());

    }

    @Test
    public void testGetAllPadelCourts_ShouldReturnAllPadelCourts() {
        List<PadelCourtEntity> courts = DataProvider.listPadelCourtEntity();
        given(padelCourtRepository.findAll()).willReturn(courts);

        List<PadelCourtEntity> result = padelCourtService.getAllPadelCourts();

        assertNotNull(result);
        assertThat(result.size()).isEqualTo(courts.size());
        assertThat(result.get(0)).isEqualTo(courts.get(0));
        assertEquals("Calle olivo 23",result.get(1).getAddress());
    }

    @Test
    public void testGetCourtById_ShouldReturnPadelCourt() {
        Long id = 1L;
        PadelCourtEntity court = DataProvider.getPadelCourtEntity();
        given(padelCourtRepository.findById(id)).willReturn(Optional.ofNullable(court));

        PadelCourtEntity result = padelCourtService.getCourtById(id);

        assertNotNull(result);
        assertEquals("Padel World",result.getName());

    }

    @Test
    public void testGetCourtById_ShouldReturnException_WhenPadelCourtNotFound() {
        Long id = 1L;
        given(padelCourtRepository.findById(anyLong())).willReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            padelCourtService.getCourtById(id);
        });
        assertTrue(exception.getMessage().contains("Padel court with id " + id + " not found"));

        verify(padelCourtRepository).findById(id);
    }
}
