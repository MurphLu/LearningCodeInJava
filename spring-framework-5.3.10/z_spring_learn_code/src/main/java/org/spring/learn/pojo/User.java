package org.spring.learn.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    private boolean admin;
	private String name;
}
