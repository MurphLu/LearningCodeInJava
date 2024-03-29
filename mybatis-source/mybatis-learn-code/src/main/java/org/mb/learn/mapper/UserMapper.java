package org.mb.learn.mapper;

import org.mb.learn.entity.User;

public interface UserMapper {
    User selectById(long id);
}
