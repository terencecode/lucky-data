package com.isep.lucky_data.model.converter;

import com.isep.lucky_data.model.RoleName;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class RoleNameConverter implements AttributeConverter<RoleName, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RoleName roleName) {
        Objects.requireNonNull(roleName, "role name cannot be null");
        return roleName.getRole();
    }

    @Override
    public RoleName convertToEntityAttribute(Integer role) {
        Objects.requireNonNull(role, "role cannot be null");
        return RoleName.fromStatus(role);
    }
}
