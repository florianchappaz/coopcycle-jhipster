package com.coopcycle.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.coopcycle.domain.DeliverMan} entity.
 */
public class DeliverManDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String name;

    @NotNull
    @Min(value = 18)
    private Integer age;

    @Lob
    private byte[] profilePicture;

    private String profilePictureContentType;
    private CooperativeDTO cooperative;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePictureContentType() {
        return profilePictureContentType;
    }

    public void setProfilePictureContentType(String profilePictureContentType) {
        this.profilePictureContentType = profilePictureContentType;
    }

    public CooperativeDTO getCooperative() {
        return cooperative;
    }

    public void setCooperative(CooperativeDTO cooperative) {
        this.cooperative = cooperative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliverManDTO)) {
            return false;
        }

        DeliverManDTO deliverManDTO = (DeliverManDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, deliverManDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliverManDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", age=" + getAge() +
            ", profilePicture='" + getProfilePicture() + "'" +
            ", cooperative=" + getCooperative() +
            "}";
    }
}
