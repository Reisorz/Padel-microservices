package com.mls.service.padel_match.specifications;

import com.mls.service.padel_match.model.PadelMatchEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PadelMatchEntitySpecification {

    public static Specification<PadelMatchEntity> datesIn(List<LocalDateTime> dates) {
        return (Root<PadelMatchEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (dates == null || dates.isEmpty()) {
                return cb.conjunction(); // No filter
            }

            List<Predicate> dayPredicates = new ArrayList<>();
            for (LocalDateTime dateTime : dates) {
                LocalDateTime startOfDay = dateTime.toLocalDate().atStartOfDay();
                LocalDateTime endOfDay = dateTime.toLocalDate().atTime(LocalTime.MAX);

                dayPredicates.add(cb.between(root.get("matchDateStart"), startOfDay, endOfDay));
            }

            return cb.or(dayPredicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<PadelMatchEntity> userPadelLevelBetweenMatchRange(Double userPadelLevel) {
        return (Root<PadelMatchEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (userPadelLevel == null) {
                return cb.conjunction(); // No filters
            }

            Predicate padelLevelMin = cb.lessThanOrEqualTo(root.get("matchLevelStart"), userPadelLevel);
            Predicate padelLevelMax = cb.greaterThanOrEqualTo(root.get("matchLevelEnd"), userPadelLevel);
            return cb.and(padelLevelMin, padelLevelMax);
        };
    }
}
