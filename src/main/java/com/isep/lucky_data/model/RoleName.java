package com.isep.lucky_data.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum RoleName {
    ROLE_USER(0, "ROLE_USER"),
    ROLE_DATA_EXPERT(1, "ROLE_DATA_EXPERT"),
    ROLE_ADMIN(2, "ROLE_ADMIN");

    private static Map<String, RoleName> valuesByName;
    private final int role;
    private final String name;

    RoleName(int role, String name) {
        this.role = role;
        this.name = name;
    }

    public static RoleName fromName(String name) {
        if (valuesByName == null) {
            RoleName[] values = values();
            Map<String, RoleName> m = new HashMap<>(2 * values.length);
            for (RoleName value : values) {
                m.put(value.getName(), value);
            }
            valuesByName = m;
        }

        RoleName value = valuesByName.get(name);
        if (value == null) {
            value = valuesByName.get("ROLE_" + name.toUpperCase());
            if (value == null) {
                throw new IllegalArgumentException("The role name " + name + " is invalid");
            }
        }
        return value;
    }

    public static RoleName fromStatus(int role) {
        RoleName[] statuses = values();
        for (RoleName eStatus : statuses) {
            if (eStatus.getRole() == role) {
                return eStatus;
            }
        }
        throw new IllegalArgumentException("The role " + role + " is invalid");
    }

    public int getRole() {
        return role;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}