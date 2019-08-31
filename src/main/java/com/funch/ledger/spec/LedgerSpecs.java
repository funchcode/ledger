package com.funch.ledger.spec;

import com.funch.ledger.domain.Ledger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LedgerSpecs {
    private static Map<String, String> validationFilter(Map<String, String> filter) {
        Map<String, String> validFilter = new HashMap<>();
        filter.keySet().forEach((value) -> {
            String target = filter.get(value);
            if (target != null && target.trim().length() != 0) {
                validFilter.put(value, target);
            }
        });
        return validFilter;
    }

    public static Specification<Ledger> searchLedger(Map<String, String> filter) {
        return (Root<Ledger> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Map<String, String> validFilter = validationFilter(filter);
            validFilter.forEach( (key, value)-> {
                String likeValue;
                switch (key) {
                    case "cardFk":
                        predicates.add(cb.equal(root.get(key), value));
                        break;
                    case "detail":
                        likeValue = "%" + value + "%";
                        predicates.add(cb.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "memo":
                        likeValue = "%" + value + "%";
                        predicates.add(cb.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "info":
                        predicates.add(cb.equal(root.get(key), value));
                        break;
                    case "category":
                        predicates.add(cb.equal(root.get(key), value));
                        break;
                    case "flow":
                        predicates.add(cb.equal(root.get(key), value));
                        break;
                    case "options":
                        predicates.add(cb.equal(root.get(key), value));
                        break;
                    case "money":
                        predicates.add(cb.equal(root.get(key), value));
                        break;
                    case "days":
                        predicates.add(cb.equal(root.get(key), value));
                        break;
                }
            });

            Predicate moneyBetween = moneyBetween(validFilter, root, cb);
            if (moneyBetween != null) {
                predicates.add(moneyBetween);
            }
            Predicate daysBetween = daysBetween(validFilter, root, cb);
            if (daysBetween != null) {
                predicates.add(daysBetween);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Predicate moneyBetween(Map<String, String> filter, Root<Ledger> root, CriteriaBuilder cb) {
        boolean hasToMoney = filter.containsKey("toMoney");
        boolean hasFromMoney = filter.containsKey("fromMoney");
        if (hasToMoney && hasFromMoney) {
            Predicate startingFrom = cb.greaterThan(root.get("money"), filter.get("fromMoney"));
            Predicate endingAt = cb.lessThan(root.get("money"), filter.get("toMoney"));
            return cb.and(startingFrom, endingAt);
        } else if(hasToMoney) {
            return cb.lessThan(root.get("money"), filter.get("toMoney"));
        } else if(hasFromMoney) {
            return cb.greaterThan(root.get("money"), filter.get("fromMoney"));
        } else {
            return null;
        }
    }
    public static Predicate daysBetween(Map<String, String> filter, Root<Ledger> root, CriteriaBuilder cb) {
        boolean hasToDays = filter.containsKey("toDays");
        boolean hasFromDays = filter.containsKey("fromDays");
        if (hasToDays && hasFromDays) {
            Predicate startingFrom = cb.greaterThan(root.get("days"), filter.get("fromMoney"));
            Predicate endingAt = cb.lessThan(root.get("days"), filter.get("toMoney"));
            return cb.and(startingFrom, endingAt);
        } else if (hasToDays) {
            return cb.lessThan(root.get("days"), filter.get("toDays"));
        } else if (hasFromDays) {
            return cb.greaterThan(root.get("days"), filter.get("fromDays"));
        } else {
            return null;
        }
    }
}
