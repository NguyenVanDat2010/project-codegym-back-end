package com.airbnb.clone.dto;

public class ImageDto {
    private Long id;
    private String ref;
    private Long houseId;

    public ImageDto() {
    }

    public ImageDto(Long id, String ref, Long houseId) {
        this.id = id;
        this.ref = ref;
        this.houseId = houseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
}
