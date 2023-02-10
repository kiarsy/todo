package com.kiarsy.todo.hexagonal.core.domain.entities;

public enum TodoStatus {
    Not_Completed(0), Completed(1);

    private long status_code;

    public long getStatus_code() {
        return this.status_code;
    }

    private TodoStatus(long action) {
        this.status_code = action;
    }

    public static TodoStatus getByValue(long status_code) {
        for (TodoStatus dt : TodoStatus.values()) {
            if (dt.status_code == status_code) {
                return dt;
            }
        }
        throw new IllegalArgumentException("no datatype with " + status_code + " exists");
    }
}
