package com.rng.theofficeapi.entities.enums;

public enum Profiles {

    ROLE_USER(1, "ROLE_USER"),
    ROLE_ADMIN(2, "ROLE_ADMIN");

    private Integer code;
    private String title;

    private Profiles(Integer code, String title) {
        this.code = code;
        this.title = title;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Profiles toEnum(Integer code){
        if(code == null) return null;

        for(Profiles profile: Profiles.values()) {
            if(code == profile.getCode()) {
                return profile;
            }
        }

        throw new IllegalArgumentException();
    }
}