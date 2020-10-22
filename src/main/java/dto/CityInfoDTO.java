package dto;

import entities.CityInfo;

/**
 *
 * @author Dane
 */
public class CityInfoDTO {

    private String zipCode;


    public CityInfoDTO(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public CityInfoDTO(CityInfo cityInfo) {
        this.zipCode = cityInfo.getZipCode();
    }
}
