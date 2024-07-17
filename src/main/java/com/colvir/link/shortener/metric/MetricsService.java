package com.colvir.link.shortener.metric;

import com.colvir.link.shortener.repository.UserRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class MetricsService {

    private static final String USER_COUNT = "user_count";
    private final UserRepository userRepository;
    private final AtomicLong userCount;

    public MetricsService(
            MeterRegistry meterRegistry,
            UserRepository userRepository
    ) {
        this.userCount = meterRegistry.gauge(USER_COUNT, new AtomicLong(0));
        this.userRepository = userRepository;
    }

    @Scheduled(fixedDelay = 5000L)
    public void collectingMetrics() {
        final long count = userRepository.count();
        userCount.set(count);
    }
}
