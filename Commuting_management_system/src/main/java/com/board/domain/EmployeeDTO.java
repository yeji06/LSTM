package com.board.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EmployeeDTO extends AndroidEmployeeDTO {
	private LocalDateTime insertTime;
	private String auth;

	public void assignAdminRoleToUser() {
        // 사용자에게 "ROLE_ADMIN" 역할 부여 로직
        if (auth == null) {
            auth = "";
        }
        if (!auth.contains("ROLE_ADMIN")) {
            auth += ",ROLE_ADMIN";
        }
    }

    public void removeAdminRoleFromUser() {
        // 사용자로부터 "ROLE_ADMIN" 역할 제거 로직
        if (auth != null) {
            auth = auth.replace(",ROLE_ADMIN", "");
        }
    }
} 