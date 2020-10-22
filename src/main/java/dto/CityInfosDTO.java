
package dto;

import entities.CityInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dane
 */
public class CityInfosDTO {
    
    private List<CityInfoDTO> all = new ArrayList<>();
    
    public CityInfosDTO(List<CityInfo> cityInfoEntities){
        cityInfoEntities.forEach((c) -> {
            all.add(new CityInfoDTO(c));
        });
    }
    
    public List<CityInfoDTO> getAll(){
        return all;
    }
}
