package com.sagaorchestrator.service;

import com.sagaorchestrator.model.SagaDetail;
import com.sagaorchestrator.orchestrator.SagaOrchestrator;
import org.springframework.stereotype.Service;

@Service
public class SagaService {
    private final SagaOrchestrator sagaOrchestrator;
    public SagaService(SagaOrchestrator sagaOrchestrator) {
        this.sagaOrchestrator = sagaOrchestrator;
    }

    public SagaDetail startSaga(SagaDetail sagaDetail) {
        sagaOrchestrator.startSaga(sagaDetail);
        return sagaDetail;
    }
}
