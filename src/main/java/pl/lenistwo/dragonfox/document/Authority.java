package pl.lenistwo.dragonfox.document;

public enum Authority {
    READ_POST("read:post"),
    EDIT_POST("edit:post"),
    CREATE_POST("create:post"),
    DELETE_POST("delete:post"),
    EDIT_USER("edit:user"),
    CREATE_USER("create:user"),
    DELETE_USER("delete:user");

    Authority(String value) {

    }
}
