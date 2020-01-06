package com.ascending.repository;

import com.ascending.model.Role;

public interface RoleDao {
    Role getRoleByName(String name);
}
