/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;


/**
 *
 * @author angry
 */

public class PhoneDTO {
    private Long id;
    private String number;

    public PhoneDTO(String number) {
        this.number = number;
    }

    public PhoneDTO(Phone phone){
        this.number = phone.getNumber();
        
    }
    
    public PhoneDTO() {
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
}
